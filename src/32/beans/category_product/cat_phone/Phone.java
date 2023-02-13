package task.beans.category_product.cat_phone;

import task.bean.Product;
import task.beans.Category;
import task.beans.Firm;

public abstract class Phone extends Product {
    public Phone(String name, int number, int volume, int weight, Category category, Firm firm) {
        super(name, number, volume, weight, category, firm);
    }
}
