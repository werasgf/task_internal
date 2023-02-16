package task.app.task33;

import task.app.task33.books.Book;

import java.util.Date;

public class Order {
    private Integer orderNumber;
    private String customer;
    private StatusOrder statusOrder;
    private Book book;
    private int quantity;
    private int price;
    private Date dataOrder;
    private String commentsOfOrder;
    public Order(Integer orderNumber, String customer, Book book, int quantity, int price, Date dataOrder, StatusOrder statusOrder, String commentsOfOrder) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.statusOrder = statusOrder;
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.dataOrder = dataOrder;
        this.commentsOfOrder = commentsOfOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setNameOrder(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getNameOrder() {
        return orderNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setCommentsOfOrder(String commentsOfOrder) {
        this.commentsOfOrder = commentsOfOrder;
    }

    public String getCommentsOfOrder() {
        return commentsOfOrder;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public Date getDataOrder() {
        return dataOrder;
    }

    public int getPrice() {
        return price;
    }
}
