package task.app.task61;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 1. Напишите generic метод для подсчета количества элементов в коллекции,
 * обладающих определенным свойством (например, нечетные целые числа, простые числа, палиндромы).
 */
public class One {

    public static void main(String[] args) {
        List<Integer> listDishonestIntegers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        );
        System.out.println(genericMethod(listDishonestIntegers));

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);

        System.out.println(genericMethod(arrayList));
    }

    public static <T extends Number> int genericMethod(Collection<T> list) {
        return (int) list.stream()
                .filter(i -> i.intValue() % 2 != 0).count();
    }
}
