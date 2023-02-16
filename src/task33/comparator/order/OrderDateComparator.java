package task.app.task33.comparator.order;

import task.app.task33.Order;

import java.util.Comparator;

public class OrderDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDataOrder().compareTo(o2.getDataOrder());
    }
}
