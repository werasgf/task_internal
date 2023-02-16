package task.app.task33.comparator.request;

import task.app.task33.Request;

import java.util.Comparator;

public class RequestBookNameComparator implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        return o1.getBook().getName().compareTo(o2.getBook().getName());
    }
}
