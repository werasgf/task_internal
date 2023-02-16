package task.app.task33.warehouse;

import task.app.task33.Bookstore;
import task.app.task33.books.Book;
import task.app.task33.books.StatusBook;

import java.util.*;

public class Warehouse {
    private final Hashtable<Book, Integer> warehouse = new Hashtable<>();

    public Warehouse() {
    }

    public void addBookFromWarehouse(Book book, int quantity) {
        Bookstore bookstore = new Bookstore();
        book.setStatusBook(StatusBook.IN_STOCK.getCode());
        if (warehouse.containsKey(book)) {
            int oldQuantity = warehouse.get(book);
            warehouse.put(book, oldQuantity + quantity);
        } else {
            warehouse.put(book, quantity);
        }
        bookstore.removeBook(book);
    }

    public void takeBookFromWarehouse(Book book, int quantity) {
        int oldQuantity = warehouse.get(book);
        if (oldQuantity - quantity < 0) {
            System.out.println("такого количество книг на складе нет. Их всего " + oldQuantity);
        } else if (oldQuantity - quantity == 0) {
            System.out.println("книги кончались");
            warehouse.put(book, 0);
        } else {
            warehouse.put(book, oldQuantity - quantity);
        }
    }

    public void writeOffBookFromWarehouse(Book book) {
        book.setStatusBook(StatusBook.ABSENT.getCode());
        warehouse.put(book, 0);
    }

    public int getQuantity(Book book) {
        return warehouse.get(book);
    }

    public int size() {
        return warehouse.size();
    }

    public List<Book> getListBook() {
        Enumeration key = warehouse.keys();
        List<Book> list = new ArrayList<>();
        while (key.hasMoreElements()) {
            Book temp = (Book) key.nextElement();
            list.add(temp);
        }
        return list;
    }
}
