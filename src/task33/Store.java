package task.app.task33;

import task.app.task33.books.Book;

import java.util.Date;

public interface Store {
    void writeOffProductFromWarehouse(Book book);//Списать товар со склада

    void createAnOrder(Integer orderNumber, String customer, Book book, int quantity, Date dataOrder, String commentsOfOrder);//создать заказ

    void cancelAnOrder(Integer orderNumber);//отменить заказ

    void changeTheOrderStatus(Integer orderNumber, StatusOrder statusOrder);//изменить статус заказа

    void addProductToTheWarehouse(Book book, int quantity, Date dateReceipt);//Добавить товар на склад

    void leaveRequestForProduct(Book book, int quantity);//оставить запрос на товар

    void descriptionBook(Book book); //посмотреть описание книги

    void descriptionOrder(Integer orderNumber); //посмотреть детали заказа

    void listOfBook();//список книг

    void listOfOrders();//    список заказов

    void listOfBookRequests();//    список запросов на книгу

    void listOfCompletedOrdersOverPeriodOfTime(Date firstDate, Date secondDate);//    список выполненных заказов за период времени

    void amountOfMoneyEarnedOverPeriodOfTime(Date firstDate, Date secondDate);//    сумма заработанных средств за период времени

    void numberOfCompletedOrdersOverPeriodOfTime(Date firstDate, Date secondDate);//    количество выполненных заказов за период времени

    void listOfStaleBooks();//    список залежавшийся книг
}