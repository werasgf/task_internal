package task.app.task33.comparator.request;

import task.app.task33.Order;
import task.app.task33.Request;

import java.util.Comparator;

public class RequestNumberComparator implements Comparator<Request> {

    @Override
    public int compare(Request o1, Request o2) {
        if(o1.getNumberOfRequest()> o2.getNumberOfRequest())
            return 1;
        else if(o1.getNumberOfRequest()< o2.getNumberOfRequest())
            return -1;
        else
            return 0;
    }
}
