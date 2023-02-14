package task.app.task33;

import task.app.task33.books.Book;

public interface Store {
    boolean writeOffProductFromWarehouse(Book book);//Списать товар со склада

    boolean createAnOrder(Book book, Integer orderNumber, String nameBook, int quantity);//создать заказ

    boolean cancelAnOrder(Integer orderNumber);//отменить заказ

    boolean changeTheOrderStatus(Integer orderNumber, StatusOrder statusOrder);//изменить статус заказа

    boolean addProductToTheWarehouse(Book book, int quantity);//Добавить товар на склад

    boolean leaveRequestForProduct(Book book, Integer orderNumber, String nameBook, int quantity);//оставить запрос на товар
}
