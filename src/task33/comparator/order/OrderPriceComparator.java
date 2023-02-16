package task.app.task33.comparator.order;

import task.app.task33.Order;

import java.util.Comparator;

public class OrderPriceComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getPrice()> o2.getPrice())
            return 1;
        else if(o1.getPrice()< o2.getPrice())
            return -1;
        else
            return 0;
    }
}
