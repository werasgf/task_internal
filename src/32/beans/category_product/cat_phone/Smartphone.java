package task.beans.category_product.cat_phone;

import task.beans.Category;
import task.beans.Firm;

public class Smartphone extends Phone {
    public Smartphone(String name, int number, int volume, int weight, Category category, Firm firm) {
        super(name, number, volume, weight, category, firm);
    }
}
