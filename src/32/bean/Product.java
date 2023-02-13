package task.bean;

import task.beans.Category;
import task.beans.Firm;

public class Product {
    String name;
    int number;
    int volume;
    private final int weight;//вес
    private final Category category;
    private final Firm firm;
    public Product(String name, int number, int volume, int weight, Category category, Firm firm) {
        this.name = name;
        this.number = number;
        this.volume = volume;
        this.weight = weight;
        this.category = category;
        this.firm = firm;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Продукт{" +
                "название='" + name + '\'' +
                ", номер=" + number +
                ", объем=" + volume +
                ", вес=" + weight +
                ", категория - " + category +
                ", фирма - " + firm +
                '}';
    }

    public Category getCategory() {
        return category;
    }

    public Firm getFirm() {
        return firm;
    }
}