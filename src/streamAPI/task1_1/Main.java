package streamAPI.task1_1;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);

        if (b == 0) {
            System.out.println("Нельзя делить на ноль!");
        } else {
            int c = calc.devide.apply(a, b); // не обработано деление на 0
            calc.println.accept(c);
        }
    }
}
