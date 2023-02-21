package task.app.task44;

import java.sql.Array;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private static final List<Product> listProduct = Arrays.asList(
            new Product("Apple", "Fruit", 59.99),
            new Product("Banana", "Fruit", 99.59),
            new Product("Pineapple", "Fruit", 159.79),
            new Product("Cucumber", "Vegetables", 60.0),
            new Product("Potato", "Vegetables", 79.99),
            new Product("Tomato", "Vegetables", 89.49),
            new Product("Tea", "Tea", 609.99)
    );
    private static final List<Order> listOrder = Arrays.asList(
            new Order(LocalDate.of(2023, 1, 27), "Fruit", LocalDate.of(2023, 1, 24), 5, "Completed"),
            new Order(LocalDate.of(2023, 2, 16), "Vegetables", LocalDate.of(2023, 2, 13), 6, "Cancelled"),
            new Order(LocalDate.of(2023, 1, 2), "Vegetables", LocalDate.of(2022, 1, 30), 4, "Open"),
            new Order(LocalDate.of(2023, 1, 19), "Fruit", LocalDate.of(2023, 1, 16), 6, "Cancelled"),
            new Order(LocalDate.of(2023, 2, 10), "Tea", LocalDate.of(2023, 2, 7), 3, "Cancelled"),
            new Order(LocalDate.of(2023, 1, 4), "Fruit", LocalDate.of(2023, 1, 1), 4, "Open"),
            new Order(LocalDate.of(2023, 2, 18), "Tea", LocalDate.of(2023, 2, 15), 9, "Completed")
    );

    public static void main(String[] args) {
        one();
        two();
        three();
        four();
        five();
        six();
        seven();
        eight();
        nine();
        dix();
        eleven();
        twelve();
        thirteen();
        fourteen();
        fifteen();
        sixteen();
        seventeen();
    }

    private static void one() {
        System.out.println("Test 1");
        listProduct.stream().
                forEach(System.out::println);
    }

    private static void two() {
        System.out.println("Test 2");
        listProduct.stream().
                forEach(item ->
                        System.out.println(item.getName() + " = " + item.getPrice()));
    }

    private static void three() {
        System.out.println("Test 3");
        listOrder.stream()
                .map(Order::getStatus)
                .distinct().forEach(System.out::println);

    }

    private static void four() {
        System.out.println("Test 4");
        listProduct.stream().
                forEach(item ->
                        System.out.println(item.getName()));
    }

    private static void five() {
        System.out.println("Test 5");
        listProduct.stream()
                .filter(product -> Objects.equals(product.getCategory(), "Fruit"))
                .forEach(System.out::println);
    }

    private static void six() {
        System.out.println("Test 6");
        listProduct.stream()
                .filter(product -> product.getPrice() < 600)
                .forEach(System.out::println);
    }

    private static void seven() {
        System.out.println("Test 7");
        listOrder.stream()
                .filter(order -> order.getOpenDate().isBefore(LocalDate.of(2023, 1, 25)))
                .forEach(System.out::println);
    }

    private static void eight() {
        System.out.println("Test 8");
        listOrder.stream()
                .filter(order -> Objects.equals(order.getStatus(), "Open"))
                .forEach(System.out::println);
    }

    private static void nine() {
        System.out.println("Test 9");
        listProduct.stream()
                .limit(3).
                forEach(System.out::println);
    }

    private static void dix() {
        System.out.println("Test 10");
        listProduct.stream()
                .skip(2)
                .forEach(System.out::println);
    }

    private static void eleven() {
        System.out.println("Test 11");
        System.out.println(listProduct.stream()
                .filter(product -> product.getPrice() > 100)
                .findFirst());
    }

    private static void twelve() {
        System.out.println("Test 12");
        listOrder.stream()
                .forEach(order ->
                        System.out.println(order.getDeliveryDate().getYear()));
    }

    private static void thirteen() {
        System.out.println("Test 13");
        System.out.println(listOrder.stream().count());
    }

    private static void fourteen() {
        System.out.println("Test 14");
        System.out.println(listOrder.stream()
                .mapToInt(Order::getProductCountInOrder)
                .min());
    }

    private static void fifteen() {
        System.out.println("Test 15");
        System.out.println("is delivery date after: " + listOrder.stream()
                .noneMatch(order -> order.getDeliveryDate().isAfter(LocalDate.of(2023, 2, 11))));
    }

    private static void sixteen() {
        System.out.println("Test 16");
        System.out.println("is price less 50: " + listProduct.stream()
                .anyMatch(product -> product.getPrice() < 50));
    }

    private static void seventeen() {
        System.out.println("Test 17");
        listOrder.stream()
                .sorted(Comparator.comparingInt(Order::getProductCountInOrder).reversed())
                .forEach(System.out::println);
    }
}