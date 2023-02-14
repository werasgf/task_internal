package task.app.task33;

import task.app.task33.books.Book;
import task.app.task33.books.StatusBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bookstore implements Store {
    private HashMap<Integer, Order> orders = new HashMap<>();

    @Override
    public boolean writeOffProductFromWarehouse(Book book) {
        return writeDowns(book);
    }

    @Override
    public boolean createAnOrder(Book book, Integer orderNumber, String nameBook, int quantity) {
        return check(book, orderNumber, nameBook, quantity);
    }

    @Override
    public boolean cancelAnOrder(Integer orderNumber) {
        if (orders.containsKey(orderNumber)) {
            Order order = orders.get(orderNumber);
            order.setStatusOrder(StatusOrder.CANCELLED);
            orders.put(orderNumber, order);
        } else {
            System.out.println("такого номера не существует " + orderNumber);
        }
        return true;
    }

    @Override
    public boolean changeTheOrderStatus(Integer orderNumber, StatusOrder statusOrder) {
        if (orders.containsKey(orderNumber)) {
            Order order = orders.get(orderNumber);
            order.setStatusOrder(statusOrder);
            orders.put(orderNumber, order);
        } else {
            System.out.println("такого номера не существует " + orderNumber);
        }
        return true;
    }

    @Override
    public boolean addProductToTheWarehouse(Book book, int quantity) {
        return addProduct(book, quantity);
    }

    @Override
    public boolean leaveRequestForProduct(Book book, Integer orderNumber, String nameBook, int quantity) {
        if (book.getStatusBook().equals(StatusBook.IN_STOCK)) {
            System.out.println("книга не списана");
            return createAnOrder(book, orderNumber, nameBook, quantity);
        } else {
            if (orders.isEmpty() || !(orders.containsKey(orderNumber))) {
                Order order = new Order(orderNumber, StatusOrder.NEW, book, nameBook, book.getQuantity() - quantity);
                orders.put(orderNumber, order);
                book.setQuantity(book.getQuantity() - quantity);
                isThisTheLastBook(book, book.getQuantity());
                return true;
            } else return false;
        }
    }

    boolean writeDowns(Book book) {
        if (StatusBook.IN_STOCK.equals(book.getStatusBook())) {
            book.setStatusBook(StatusBook.ABSENT);
            book.setQuantity(0);
        }
        return true;
    }

    private boolean check(Book book, Integer orderNumber, String nameBook, int quantity) {
        if (book.getQuantity() > 0) {
            if (book.getQuantity() > quantity) {
                if (orders.isEmpty() || !(orders.containsKey(orderNumber))) {
                    Order order = new Order(orderNumber, StatusOrder.NEW, book, nameBook, quantity);
                    orders.put(orderNumber, order);
                    book.setQuantity(book.getQuantity() - quantity);
                    isThisTheLastBook(book, book.getQuantity());
                    return true;
                } else return false;
            } else return false;
        } else return leaveRequestForProduct(book, orderNumber, nameBook, quantity);
    }

    private void isThisTheLastBook(Book book, int quantity) {
        if (quantity <= 0) {
            book.setStatusBook(StatusBook.ABSENT);
        }
    }

    private boolean addProduct(Book book, int quantity) {
        if (StatusBook.ABSENT.equals(book.getStatusBook())) book.setStatusBook(StatusBook.IN_STOCK);
        book.setQuantity(quantity);
        List<Integer> keys = new ArrayList<Integer>(orders.keySet());
        for (Integer key : keys) {
            if (orders.get(key).getBook() == book && orders.get(key).getQuantity() < 0) {
                orders.get(key).setQuantity(orders.get(key).getQuantity() * -1);
                changeTheOrderStatus(orders.get(key).getNameOrder(), StatusOrder.COMPLETED);
            }
        }
        return true;
    }
}