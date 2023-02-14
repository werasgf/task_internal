package task.app;

import task.app.task33.*;
import task.app.task33.books.BookOne;
import task.app.task33.books.BookTwo;
import task.app.task33.books.StatusBook;
import task.app.task33.warehouse.Warehouse;

public class App {
    public static void main(String[] args) {
//        Menu menu = new Menu();
//        menu.mainMenu();
        testBook();
    }

    public static void testBook() {
        Bookstore bookstore = new Bookstore();
        
        BookOne bookOne = new BookOne("A book for bushes", StatusBook.IN_STOCK, 5);
        BookTwo bookTwo = new BookTwo("Why am i smart?", StatusBook.IN_STOCK, 3);
        boolean writeOffBookFromWarehouse = bookstore.writeOffProductFromWarehouse(bookOne);  //Списать книгу со склада
        if (!writeOffBookFromWarehouse) {
            throw new AssertionError("Метод не работает");
        }
        boolean createAnOrder = bookstore.createAnOrder(bookTwo,1, bookTwo.getName(), 2);   //создать заказ
        if (!createAnOrder) {
            throw new AssertionError("Метод не работает");
        }

        boolean cancelAnOrder = bookstore.cancelAnOrder(1);   //отменить заказ
        if (!cancelAnOrder) {
            throw new AssertionError("Метод не работает");
        }

        bookstore.addProductToTheWarehouse(bookOne, 5);
        bookstore.createAnOrder(bookOne, 2, bookOne.getName(), 2);
        boolean changeTheOrderStatus = bookstore.changeTheOrderStatus(2, StatusOrder.COMPLETED);    //изменить статус заказа
        if (!changeTheOrderStatus) {
            throw new AssertionError("Метод не работает");
        }

        boolean addBookToTheWarehouse = bookstore.addProductToTheWarehouse(bookTwo, 5);   //Добавить книгу на склад
        if (!addBookToTheWarehouse) {
            throw new AssertionError("Метод не работает");
        }

        bookstore.writeOffProductFromWarehouse(bookOne);
        bookstore.createAnOrder(bookOne,3, bookOne.getName(), 2);
        bookstore.addProductToTheWarehouse(bookOne, 5);

    }
}
