package task.util;


import task.bean.Product;
import task.beans.Category;
import task.beans.Firm;
import task.beans.category_product.cat_app.microwave.Microwave;
import task.beans.category_product.cat_app.vacuum_cleaner.VacuumCleaner;
import task.beans.category_product.cat_phone.PushButtonPhone;
import task.beans.category_product.cat_phone.Smartphone;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    public static List<Product> createItemDatabase() {
        List<Product> products = new ArrayList<>();
        products.add(new PushButtonPhone("Кнопочный телефон", 1, 1, 1, Category.PUSH_BUTTON_PHONE, Firm.NOKIA));
        products.add(new Microwave("Микроволновка", 2, 4, 3, Category.MICROWAVE, Firm.XIAOMI));
        products.add(new VacuumCleaner("Пылесос", 3, 5, 4, Category.VACUUM_CLEANER, Firm.LG));
        products.add(new Smartphone("Айфон", 4, 1, 1, Category.SMARTPHONE, Firm.APPLE));
        return products;
    }
}
