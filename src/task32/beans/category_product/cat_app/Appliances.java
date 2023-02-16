package task.beans.category_product.cat_app;

import task.bean.Product;
import task.beans.Category;
import task.beans.Firm;

public abstract class Appliances extends Product {
    public Appliances(String name, int number, int volume, int weight, Category category, Firm firm) {
        super(name, number, volume, weight, category, firm);
    }
}
