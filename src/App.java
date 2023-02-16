package task.app;

import task.app.task33.*;
import task.app.task33.books.Books;
import task.app.task33.books.StatusBook;

import java.util.Date;

public class App {
    public static void main(String[] args) {
//        Menu menu = new Menu();
//        menu.mainMenu();
        testBook();
    }

    public static void testBook() {
        Bookstore bookstore = new Bookstore();

        Books book_1 = new Books("A book for bushes", 1500, "2015", "shhh shhh shhh", StatusBook.ABSENT.getCode());
        Books book_2 = new Books("Why am i smart?", 10000, "2012", "Well, I'm not stupid", StatusBook.ABSENT.getCode());
        Books book_3 = new Books("How to become the number seven?", 777, "2007", "Have you ever thought about how the seven feels, what she feels? This book will help you understand what it means to be a seven...", StatusBook.ABSENT.getCode());
        Books book_4 = new Books("Why can't a tomato eat a pumpkin?", 1500, "2015", "I have been trying for many years to teach my tomatoes the art of eating pumpkins, and after a while I succeeded. In this book I will tell you how to do it.", StatusBook.ABSENT.getCode());
        Books book_5 = new Books("Pumpkin's Revenge", 1600, "2017", "I trained my tomatoes to eat pumpkins. Now the pumpkins are hunting me. In this book I will tell you about my adventure.", StatusBook.ABSENT.getCode());

        bookstore.addProductToTheWarehouse(book_1, 5, new Date(2021, 1, 1));   //Добавить книгу 1 на склад
        bookstore.addProductToTheWarehouse(book_2, 5, new Date(2021, 1, 1));   //Добавить книгу 2 на склад
        bookstore.addProductToTheWarehouse(book_3, 5, new Date(2020, 1, 1));   //Добавить книгу 3 на склад
        bookstore.addProductToTheWarehouse(book_4, 5, new Date(2019, 1, 1));   //Добавить книгу 4 на склад
        bookstore.addProductToTheWarehouse(book_5, 5, new Date(2021, 1, 1));   //Добавить книгу 5 на склад

        bookstore.descriptionBook(book_1);//посмотреть описание книги
        bookstore.writeOffProductFromWarehouse(book_5);  //Списать книгу 5 со склада
        bookstore.listOfBook();

        bookstore.createAnOrder(1, "Labyrinth", book_2, 2, new Date(2021, 9, 10), "need more books");   //создать заказ
        bookstore.createAnOrder(8, "Labyrinth", book_3, 2, new Date(2021, 9, 10), "need more books");   //создать заказ
        bookstore.createAnOrder(10, "Labyrinth", book_4, 2, new Date(2021, 9, 10), "need more books");   //создать заказ

        bookstore.createAnOrder(2, "The Globglogabgalab", book_1, 2, new Date(2022, 8, 7), "I'm Globglogabgalab. I love books");
        bookstore.createAnOrder(3, "Read the city", book_1, 2, new Date(2022, 10, 15), "i will sell more expensive");

        bookstore.cancelAnOrder(1);   //отменить заказ
        bookstore.changeTheOrderStatus(3, StatusOrder.COMPLETED);    //изменить статус заказа
        bookstore.listOfOrders();

        bookstore.descriptionOrder(2);//посмотреть детали заказа

        bookstore.writeOffProductFromWarehouse(book_4);//Списать книгу 4 со склада
        bookstore.createAnOrder(4, "Labyrinth", book_5, 2, new Date(2022, 11, 10), "need more books");   //создать заказ
        bookstore.createAnOrder(5, "The Globglogabgalab", book_5, 2, new Date(2022, 1, 10), "I'm Globglogabgalab. I love books");
        bookstore.createAnOrder(6, "Labyrinth", book_4, 2, new Date(2022, 1, 10), "need more books");   //создать заказ
        bookstore.listOfBookRequests();

        System.out.println();
        bookstore.numberOfCompletedOrdersOverPeriodOfTime(new Date(2022, 10, 1), new Date(2022, 11, 30));
        System.out.println();
        bookstore.amountOfMoneyEarnedOverPeriodOfTime(new Date(2022, 10, 1), new Date(2022, 11, 30));
        System.out.println();
        bookstore.listOfCompletedOrdersOverPeriodOfTime(new Date(2022, 10, 1), new Date(2022, 11, 30));
        System.out.println();
        bookstore.listOfStaleBooks();
    }
}
