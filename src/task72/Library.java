package task.app.task72;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1, true);
    public static final HashMap<Book, WhereToReadBook> bookLibrary = new HashMap<>();
    public static final HashMap<Book, Boolean> bookInStock = new HashMap<>();

    public static void comeToLibrary(ActionReader action, List<Book> books, int countName) {
        takeQueue(action, books, countName);
    }

    private static void takeQueue(ActionReader action, List<Book> books, int countName) {
        try {
            queue.put(countName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(countName + " встал в очередь");
        if (ActionReader.READ == action) {
            takeBook(books, countName);
        } else if (ActionReader.RETURN == action) {
            returnBook(books, countName);
        } else {
            System.out.println("Такого действия не предусмотрено");
        }

    }

    private static void takeBook(List<Book> books, int countName) {
        List<Book> listBookHome = new ArrayList<>();
        List<Book> listBookReadingRoom = new ArrayList<>();

        for (Book value : books) {
            if (bookLibrary.containsKey(value)) {
                if (bookLibrary.get(value) == WhereToReadBook.HOME) {
                    listBookHome.add(value);
                } else {
                    listBookReadingRoom.add(value);
                }
            } else {
                System.out.println("Такой книги нет в библиотеке");
            }
        }

        for (Book book : listBookHome) {
            if (bookInStock.get(book)) {
                bookInStock.put(book, Boolean.FALSE);
                System.out.println(book.nameBook + " забрал в домой " + countName);
            } else {
                System.out.println(book.nameBook + " книги нет в наличии");
            }
        }

        List<Book> receivedBooks = new ArrayList<>();
        for (Book book : listBookReadingRoom) {
            if (bookInStock.get(book)) {
                System.out.println(book.nameBook + " - эту книгу можно взять только в читальный зал");
                bookInStock.put(book, Boolean.FALSE);
                receivedBooks.add(book);
            } else {
                System.out.println(book.nameBook + " книги нет в наличии");
            }
        }
        System.out.println(countName + " освобождается очередь");
        try {
            queue.take();
            Thread.sleep(1);
            if (!(receivedBooks.isEmpty())) {
                returnBook(receivedBooks, countName);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void returnBook(List<Book> books, int countName) {
        for (Book value : books) {
            if (bookLibrary.containsKey(value)) {
                bookInStock.put(value, Boolean.TRUE);
                System.out.println(value.nameBook + " вернул в библиотеку " + countName);
            } else {
                System.out.println("Такой книги нет в библиотеке");
            }
        }
    }
}
