package task.beans.category_product.cat_app.microwave;

import task.beans.Category;
import task.beans.Firm;
import task.beans.category_product.cat_app.Appliances;

public class Microwave extends Appliances {
    public Microwave(String name, int number, int volume, int weight, Category category, Firm firm) {
        super(name, number, volume, weight, category, firm);
    }
}
