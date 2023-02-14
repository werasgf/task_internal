package task.app.task33;

import task.app.task33.books.Book;

public class Order {
    private Integer orderNumber;
    private StatusOrder statusOrder;
    private Book book;
    private String bookName;
    private int quantity;

    public Order(Integer orderNumber, StatusOrder statusOrder, Book book, String bookName, int quantity) {
        this.orderNumber = orderNumber;
        this.statusOrder = statusOrder;
        this.book = book;
        this.bookName = bookName;
        this.quantity = quantity;
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

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
