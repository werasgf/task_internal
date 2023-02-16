package task.app.task33.comparator.book;

import task.app.task33.books.Book;

import java.util.Comparator;

public class BookInStonkComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getStatusBook().compareTo(o2.getStatusBook());
    }
}
