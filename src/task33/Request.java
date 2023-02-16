package task.app.task33;

import task.app.task33.books.Book;

import java.util.Date;

public class Request {
    private Book book;
    private int quantity;
    private int numberOfRequest;

    public Request(Book book, int quantity, int numberOfRequest) {
        this.book = book;
        this.quantity = quantity;
        this.numberOfRequest = numberOfRequest;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }


    public void setNumberOfRequest(int numberOfRequest) {
        this.numberOfRequest = numberOfRequest;
    }

    public int getNumberOfRequest() {
        return numberOfRequest;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
