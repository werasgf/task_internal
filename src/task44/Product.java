package task.app.task44;

public class Product {
    private String name;

    private String category;

    private Double price;

    Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Продукт{" +
                "название='" + name + '\'' +
                ", категория=" + category +
                ", цена=" + price +
                '}';
    }
}
