package task.app.task72;

import java.util.ArrayList;
import java.util.List;

import static task.app.task72.Library.bookInStock;
import static task.app.task72.Library.bookLibrary;

public class Test {
    public static void main(String[] args) {
        Book book1 = new Book("Book 1");
        Book book2 = new Book("Book 2");
        Book book3 = new Book("Book 3");
        Book book4 = new Book("Book 4");
        Book book5 = new Book("Book 5");

        bookLibrary.put(book1, WhereToReadBook.HOME);
        bookLibrary.put(book2, WhereToReadBook.HOME);
        bookLibrary.put(book3, WhereToReadBook.READING_ROOM);
        bookLibrary.put(book4, WhereToReadBook.READING_ROOM);
        bookLibrary.put(book5, WhereToReadBook.READING_ROOM);

        bookInStock.put(book1, Boolean.TRUE);
        bookInStock.put(book2, Boolean.TRUE);
        bookInStock.put(book3, Boolean.TRUE);
        bookInStock.put(book4, Boolean.TRUE);
        bookInStock.put(book5, Boolean.TRUE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток 1");
                List<Book> books = new ArrayList<>();
                books.add(book1);
                books.add(book5);
                books.add(book4);
                Library.comeToLibrary(ActionReader.READ, books, 1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток 2");
                List<Book> books = new ArrayList<>();
                books.add(book1);
                books.add(book2);
                books.add(book5);
                Library.comeToLibrary(ActionReader.READ, books, 2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток 3");
                List<Book> books = new ArrayList<>();
                books.add(book2);
                Library.comeToLibrary(ActionReader.READ, books, 3);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток 4");
                List<Book> books = new ArrayList<>();
                books.add(book5);
                books.add(book3);
                books.add(book4);
                Library.comeToLibrary(ActionReader.READ, books, 4);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток 5");
                List<Book> books = new ArrayList<>();
                books.add(book5);
                Library.comeToLibrary(ActionReader.READ, books, 5);
            }
        }).start();
    }
}
