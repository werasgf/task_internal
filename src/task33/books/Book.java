package task.app.task33.books;

public abstract class Book {
    private String name;
    private int price;
    private String dataOfPublication;
    private String description;
    private String statusBook;


    Book(String name, int price, String dataOfPublication, String description, String statusBook) {
        this.name = name;
        this.price = price;
        this.dataOfPublication = dataOfPublication;
        this.description = description;
        this.statusBook = statusBook;
    }

    Book() {

    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDataOfPublication() {
        return dataOfPublication;
    }

    public void setStatusBook(String statusBook) {
        this.statusBook = statusBook;
    }

    public String getStatusBook() {
        return statusBook;
    }
}
