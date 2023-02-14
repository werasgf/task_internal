package task.service;

import task.bean.Product;
import task.bean.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static task.util.ProductDatabase.createItemDatabase;

public class ProductService {
    private final Warehouse warehouse;
    private final List<Product> products;

    {
        warehouse = new Warehouse();
        products = createItemDatabase();
    }

    public void printItemList() {
        for (Product item : products) {
            System.out.println(item);
        }
    }

    public List<Product> sortByWeight() {
        return products.stream()
                .sorted((o1, o2) -> o2.getWeight() - o1.getWeight())
                .collect(Collectors.toList());
    }

    public void printSortedListByWeight(List<Product> sortedListByWeight) {
        sortedListByWeight.forEach(System.out::println);
    }

    public int findMinVolumeFromItemList() {
        return products.stream()
                .sorted(Comparator.comparingInt(Product::getVolume))
                .collect(Collectors.toList()).get(0).getVolume();
    }

    public List<Product> putItemInTheWarehouseWithMaxWeight(List<Product> sortedList, int volume) {
        List<Product> resultList = new ArrayList<>();
        warehouse.setVolume(volume);
        int resultWeight = 0;
        for (Product product : sortedList) {
            int sumVolume = product.getVolume() * product.getNumber();
            if (warehouse.getVolume() >= sumVolume) {
                resultWeight += product.getWeight();
                resultList.add(new Product(product.getName(), product.getNumber(),
                        product.getVolume(), product.getWeight(),
                        product.getCategory(), product.getFirm()));
                warehouse.setVolume(warehouse.getVolume() - sumVolume);
            } else {
                int newNumber = warehouse.getVolume() / product.getVolume();
                if (newNumber > 0) {
                    resultList.add(new Product(product.getName(), product.getNumber(),
                            product.getVolume(), product.getWeight(),
                            product.getCategory(), product.getFirm()));
                    break;
                }
            }
        }
        return resultList;
    }

    public void printItemInTheWarehouseWithMaxWeight(List<Product> resultList) {
        resultList.forEach(System.out::println);
    }
}
