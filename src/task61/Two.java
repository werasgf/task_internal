package task.app.task61;

import java.util.*;

/**
 * 2. Напишите generic метод для обмена позициями двух разных элементов в массиве.
 */
public class Two {
    public static void main(String[] args) {
        String[] strings = {"foo", "bee", "pop", "WTFPL"};

        Integer[] integers = {1, 2, 3, 4};

        genericMethod(strings, 1, 2);
        genericMethod(integers, 0, 3);

        System.out.println(Arrays.toString(strings));
        System.out.println(Arrays.toString(integers));
    }

    private static <T> void genericMethod(T[] t, int firstIndex, int secondIndex) {
        if (firstIndex < 0 && secondIndex < 0) {
            System.out.println("индексы не могут быть отрицательными");
        }
        if (firstIndex > Arrays.asList(t).size()) {
            System.out.println("первая позиция вышла за предел коллекции");
        } else if (secondIndex > Arrays.asList(t).size()) {
            System.out.println("вторая позиция вышла за предел коллекции");
        } else {
            Collections.swap(Arrays.asList(t), firstIndex, secondIndex);
            System.out.println("все поменялось");
        }
    }
}
