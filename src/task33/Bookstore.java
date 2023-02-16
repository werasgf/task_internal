package task.app.task33;

import task.app.task33.books.Book;
import task.app.task33.comparator.request.RequestBookNameComparator;
import task.app.task33.comparator.book.BookDateComparator;
import task.app.task33.comparator.book.BookInStonkComparator;
import task.app.task33.comparator.book.BookNameComparator;
import task.app.task33.comparator.book.BookPriceComparator;
import task.app.task33.comparator.order.OrderDateComparator;
import task.app.task33.comparator.order.OrderPriceComparator;
import task.app.task33.comparator.order.OrderStatusComparator;
import task.app.task33.comparator.request.RequestNumberComparator;
import task.app.task33.warehouse.Warehouse;

import java.util.*;

public class Bookstore implements Store {
    private final HashMap<Integer, Order> orders = new HashMap<>();

    private final Warehouse warehouse = new Warehouse();

    private final HashMap<Book, Request> requests = new HashMap<>();

    private final HashMap<Book, Date> dateReceipt = new HashMap<>();

    @Override
    public void writeOffProductFromWarehouse(Book book) {
        warehouse.writeOffBookFromWarehouse(book);
    }

    @Override
    public void createAnOrder(Integer orderNumber, String customer, Book book, int quantity, Date dataOrder, String commentsOfOrder) {
        if (warehouse.getQuantity(book) <= 0) {
            leaveRequestForProduct(book, quantity);
        } else {
            if (orders.isEmpty() || !(orders.containsKey(orderNumber))) {
                Order order = new Order(orderNumber, customer, book, quantity, book.getPrice(), dataOrder, StatusOrder.NEW, commentsOfOrder);
                orders.put(orderNumber, order);
                warehouse.takeBookFromWarehouse(book, quantity);
            } else System.out.println("заказ  с таким номером уже существует");
        }
    }

    @Override
    public void cancelAnOrder(Integer orderNumber) {
        if (orders.containsKey(orderNumber)) {
            Order order = orders.get(orderNumber);
            order.setStatusOrder(StatusOrder.CANCELLED);
            orders.put(orderNumber, order);
            warehouse.addBookFromWarehouse(order.getBook(), order.getQuantity());
        } else {
            System.out.println("такого номера не существует " + orderNumber);
        }
    }

    @Override
    public void changeTheOrderStatus(Integer orderNumber, StatusOrder statusOrder) {
        if (orders.containsKey(orderNumber)) {
            Order order = orders.get(orderNumber);
            order.setStatusOrder(statusOrder);
            orders.put(orderNumber, order);
        } else {
            System.out.println("такого номера не существует " + orderNumber);
        }
    }

    @Override
    public void addProductToTheWarehouse(Book book, int quantity, Date date) {
        warehouse.addBookFromWarehouse(book, quantity);
        dateReceipt.put(book, date);
    }

    @Override
    public void leaveRequestForProduct(Book book, int quantity) {
        if (requests.isEmpty() || !requests.containsKey(book)) {
            requests.put(book, new Request(book, quantity, 1));
        } else {
            requests.put(book, new Request(book, requests.get(book).getQuantity() + quantity, requests.get(book).getNumberOfRequest() + 1));
        }

        System.out.println("запрос на книгу добавлен");
    }

    @Override
    public void descriptionBook(Book book) {
        System.out.println(book.getDescription());
    }

    @Override
    public void descriptionOrder(Integer orderNumber) {
        String descriptionOrder = "\n\nв заказе " + orderNumber + ":\n" +
                "заказчик - " + orders.get(orderNumber).getCustomer() + "\n" +
                "книга в заказе - " + orders.get(orderNumber).getBook().getName() + "\n" +
                "комментарий к заказу - " + orders.get(orderNumber).getCommentsOfOrder() + "\n";
        System.out.println(descriptionOrder);
    }

    @Override
    public void listOfBook() {
        List<Book> books = warehouse.getListBook();
        books.sort(new BookNameComparator());
        System.out.println("Сортировка по имени\n");
        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPrice() + " " + b.getDataOfPublication() + " " + b.getStatusBook());
        }

        books.sort(new BookPriceComparator().thenComparing(new BookNameComparator()));
        System.out.println("\n\nСортировка по цене\n");
        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPrice() + " " + b.getDataOfPublication() + " " + b.getStatusBook());
        }

        books.sort(new BookDateComparator().thenComparing(new BookNameComparator()));
        System.out.println("\n\nСортировка по дате издания\n");
        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPrice() + " " + b.getDataOfPublication() + " " + b.getStatusBook());
        }

        books.sort(new BookInStonkComparator().thenComparing(new BookNameComparator()));
        System.out.println("\n\nСортировка по наличию на складе\n");
        for (Book b : books) {
            System.out.println(b.getName() + " " + b.getPrice() + " " + b.getDataOfPublication() + " " + b.getStatusBook());
        }
    }

    @Override
    public void listOfOrders() {
        List<Order> listOrder = new ArrayList<>(orders.values());
        System.out.println("\n\nСортировка по дате выполнения\n");
        listOrder.sort(new OrderDateComparator().thenComparing(new OrderStatusComparator()));
        for (Order o : listOrder) {
            System.out.println(o.getNameOrder() + " " + o.getDataOrder() + " " + o.getPrice() + " " + o.getStatusOrder());
        }

        System.out.println("\n\nСортировка по цене заказа\n");
        listOrder.sort(new OrderPriceComparator().thenComparing(new OrderStatusComparator()));
        for (Order o : listOrder) {
            System.out.println(o.getNameOrder() + " " + o.getDataOrder() + " " + o.getPrice() + " " + o.getStatusOrder());
        }

        System.out.println("\n\nСортировка по статусу выполнения\n");
        listOrder.sort(new OrderStatusComparator().thenComparing(new OrderPriceComparator()));
        for (Order o : listOrder) {
            System.out.println(o.getNameOrder() + " " + o.getDataOrder() + " " + o.getPrice() + " " + o.getStatusOrder());
        }
    }

    @Override
    public void listOfBookRequests() {
        List<Request> listRequest = new ArrayList<>(requests.values());
        listRequest.sort(new RequestBookNameComparator());
        System.out.println("\n\nСортировка запросов по  имени книг\n");
        for (Request r : listRequest) {
            System.out.println(r.getBook().getName() + " , количество запросов: " + r.getNumberOfRequest());
        }
        listRequest.sort(new RequestNumberComparator());
        System.out.println("\n\nСортировка по количеству запросов на книгу\n");
        for (Request r : listRequest) {
            System.out.println(r.getBook().getName() + " , количество запросов: " + r.getNumberOfRequest());
        }
    }

    @Override
    public void listOfCompletedOrdersOverPeriodOfTime(Date firstDate, Date secondDate) {
        Set<Integer> setKeyOrder = orders.keySet();
        List<Integer> listKeyOrder = new ArrayList<>(setKeyOrder);
        List<Order> orderList = new ArrayList<>();
        for (Integer integer : listKeyOrder) {
            orderList.add(orders.get(integer));
        }
        orderList.stream()
                .filter(order ->
                        firstDate.before(order.getDataOrder()) && secondDate.after(order.getDataOrder())
                )
                .forEach(item ->
                        System.out.println(item.getNameOrder() + " " + item.getBook().getName() + " " + item.getPrice() + " " + item.getQuantity() + " " + item.getDataOrder())
                );
        for (Order order : orderList) {
            if (order.getStatusOrder() == StatusOrder.COMPLETED) {
                System.out.println(order.getNameOrder() + " " + order.getBook().getName() + " " + order.getPrice() + " " + order.getQuantity() + " " + order.getDataOrder());
            }
        }
    }

    @Override
    public void amountOfMoneyEarnedOverPeriodOfTime(Date firstDate, Date secondDate) {
        Set<Integer> setKeyOrder = orders.keySet();
        List<Integer> listKeyOrder = new ArrayList<>(setKeyOrder);
        List<Order> orderList = new ArrayList<>();
        for (Integer integer : listKeyOrder) {
            orderList.add(orders.get(integer));
        }
        int sum = orderList.stream()
                .filter(order ->
                        firstDate.before(order.getDataOrder()) && secondDate.after(order.getDataOrder())
                )
                .mapToInt(Order::getPrice)
                .sum();
        System.out.println(sum);
    }

    @Override
    public void numberOfCompletedOrdersOverPeriodOfTime(Date firstDate, Date secondDate) {
        Set<Integer> setKeyOrder = orders.keySet();
        List<Integer> listKeyOrder = new ArrayList<>(setKeyOrder);
        List<Order> orderList = new ArrayList<>();
        for (Integer integer : listKeyOrder) {
            orderList.add(orders.get(integer));
        }
        orderList.stream()
                .filter(order ->
                        firstDate.before(order.getDataOrder()) && secondDate.after(order.getDataOrder())
                )
                .forEach(item ->
                        System.out.println(item.getNameOrder() + " " + item.getBook().getName() + " " + item.getPrice() + " " + item.getQuantity() + " " + item.getDataOrder())
                );
    }

    @Override
    public void listOfStaleBooks() {
        Date date = new Date(2022, 7, 1);
        List<Order> oldOrder = new ArrayList<>();
        Set<Integer> setKeyOrder = orders.keySet();
        List<Integer> listKeyOrder = new ArrayList<>(setKeyOrder);
        for (int i = 0; i < listKeyOrder.size(); i++) {
            if (orders.get(listKeyOrder.get(i)).getDataOrder().before(new Date(date.getYear() - 1, date.getMonth() + 7, 1))) {
                System.out.println(orders.get(listKeyOrder.get(i)).getNameOrder());
                System.out.println(orders.get(listKeyOrder.get(i)).getDataOrder());
                System.out.println(orders.get(listKeyOrder.get(i)).getBook().getName());
                oldOrder.add(orders.get(listKeyOrder.get(i)));
            }
        }
        System.out.println("\n\nСортировка по дате поступления\n");
        List<Book> books = warehouse.getListBook();
        dateReceipt.entrySet().stream().sorted(Map.Entry.<Book, Date>comparingByValue().reversed());
        for (Order value : oldOrder) {
            if (dateReceipt.containsKey(value.getBook())) {
                System.out.println(dateReceipt.get(value.getBook()) + " " + value.getBook().getName());
            }
        }

        System.out.println("\n\nСортировка по цене\n");
        books.sort(new BookPriceComparator().thenComparing(new BookNameComparator()));
        for (Book b : books) {
        for (Order order : oldOrder) {
                if (order.getBook() == b) {
                    System.out.println(b.getName() + " " + b.getPrice());
                }
            }
        }
    }

    public void removeBook(Book book) {
        requests.remove(book);
    }
}