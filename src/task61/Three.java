package task.app.task61;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 3. Напишите generic метод для поиска максимального элемента в диапазоне [начало, конец] списка.
 */
public class Three {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("g");
        strings.add("x");
        strings.add("a");
        strings.add("e");
        strings.add("f");
        strings.add("x");

        genericMethod(strings, 2, 5);

        List<Integer> integers = new ArrayList<>();
        integers.add(7);
        integers.add(2);
        integers.add(8);
        integers.add(1);
        integers.add(9);
        integers.add(6);
        integers.add(7);

        genericMethod(integers, 3, 6);
    }

    private static <T> void genericMethod(List<T> list, int start, int end) {
        System.out.println(list.stream()
                .skip(start - 1)
                .limit(end - start + 1)
                .max(Comparator.comparing(String::valueOf)).get());
    }
}
