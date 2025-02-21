package streamAPI.task1_4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println(persons.stream().filter(age -> age.getAge() < 18)
                .count());

        List<String> armyNow = persons.stream().filter(age -> age.getAge() >= 18 &&  age.getAge() <= 27)
                .map(Person::getFamily)
                .toList();

        List<Person> smartAdult = persons.stream().filter(p -> p.getAge() >= 18 && (p.getSex().equals(Sex.MAN))? p.getAge() <= 65: p.getAge() <= 60 && p.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(20)
                .toList();
    }
}
