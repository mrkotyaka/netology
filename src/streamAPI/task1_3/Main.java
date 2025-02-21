package streamAPI.task1_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> newList = new ArrayList<>();
        for (Integer i : intList) {
            if (i % 2 == 0 && i > 0)
                newList.add(i);
        }
        newList.sort(Integer::compareTo);
        for (Integer i : newList) {
            System.out.println(i);
        }
    }
}
