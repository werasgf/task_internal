package task.app.task44;

import java.time.LocalDate;

public class Order {
    private LocalDate deliveryDate;

    private String productCategory;

    private LocalDate openDate;

    private int productCountInOrder;

    private String status;

    Order(LocalDate deliveryDate, String productCategory, LocalDate openDate, int productCountInOrder, String status) {
        this.deliveryDate = deliveryDate;
        this.productCategory = productCategory;
        this.openDate = openDate;
        this.productCountInOrder = productCountInOrder;
        this.status = status;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setProductCountInOrder(int productCountInOrder) {
        this.productCountInOrder = productCountInOrder;
    }

    public int getProductCountInOrder() {
        return productCountInOrder;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Заказ{" +
                "дата доставки='" + deliveryDate + '\'' +
                ", категория продукта=" + productCategory +
                ", дата создания заказа=" + openDate +
                ", количество продуктов в заказе=" + productCountInOrder +
                ", статус - " + status +
                '}';
    }
}
