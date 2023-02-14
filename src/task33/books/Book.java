package task.app.task33.books;

public abstract class Book {
    private String name;
    private StatusBook statusBook;
    private int quantity;

    Book(String name, StatusBook statusBook, int quantity) {
        this.name = name;
        this.statusBook = statusBook;
        this.quantity = quantity;
    }

    Book() {

    }

    public String getName() {
        return name;
    }

    public StatusBook getStatusBook() {
        return statusBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStatusBook(StatusBook statusBook) {
        this.statusBook = statusBook;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
