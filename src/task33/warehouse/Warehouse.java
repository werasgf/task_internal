package task.app.task33.warehouse;

import task.app.task33.books.Book;

public class Warehouse {
    private int quantity;
    private Book book;
    private int idBook;
    public Warehouse(int idBook, Book book, int quantity) {
        this.idBook = idBook;
        this.book = book;
        this.quantity = quantity;
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

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public Integer getIdBook() {
        return idBook;
    }
}
