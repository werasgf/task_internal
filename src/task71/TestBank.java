package task.app.task71;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestBank {
    public static void main(String[] args) {
        System.out.println("банк начал работать");
        TimerTask myBank = new Bank();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(myBank, 0, 1);


        BlockingQueue<Integer> queueOne = new ArrayBlockingQueue<>(1, true);
        BlockingQueue<Integer> queueTwo = new ArrayBlockingQueue<>(1, true);
        BlockingQueue<Integer> queueThree = new ArrayBlockingQueue<>(1, true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueOne.put(1);
                    Bank.boxOfficeOne(1, Action.INSERT_MONEY, 200000);
                    queueOne.take();
                    Thread.sleep(3);
                    queueTwo.put(1);
                    Bank.boxOfficeTwo(1, Action.WITH_DRAW, 200000);
                    queueTwo.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueOne.put(2);
                    Bank.boxOfficeOne(2, Action.INSERT_MONEY, 20000);
                    queueOne.take();
                    Thread.sleep(1);
                    queueOne.put(2);
                    Bank.boxOfficeOne(2, Action.PAYMENT, 1000);
                    queueOne.take();
                    Thread.sleep(1);
                    queueTwo.put(2);
                    Bank.boxOfficeTwo(2, Action.WITH_DRAW, 10000);
                    queueTwo.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueTwo.put(3);
                    Bank.boxOfficeTwo(3, Action.INSERT_MONEY, 10000);
                    queueTwo.take();
                    Thread.sleep(1);
                    queueOne.put(3);
                    Bank.boxOfficeOne(3, Action.MONEY_TRANSFER, 10000, 10);
                    queueOne.take();
                    Thread.sleep(1);
                    queueThree.put(3);
                    Bank.boxOfficeThree(3, Action.MONEY_TRANSFER, 1000, 2);
                    queueThree.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueOne.put(4);
                    Bank.boxOfficeOne(4, Action.INSERT_MONEY, 5000);
                    queueOne.take();
                    Thread.sleep(1);
                    queueThree.put(4);
                    Bank.boxOfficeThree(4, Action.CURRENCY_EXCHANGE, 5000);
                    queueThree.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueThree.put(5);
                    Bank.boxOfficeThree(5, Action.INSERT_MONEY, 10000);
                    queueThree.take();
                    Thread.sleep(1);
                    queueTwo.put(5);
                    Bank.boxOfficeTwo(5, Action.MONEY_TRANSFER, 5000, 3);
                    queueTwo.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueTwo.put(6);
                    Bank.boxOfficeTwo(6, Action.INSERT_MONEY, 20000);
                    queueTwo.take();
                    Thread.sleep(1);
                    queueOne.put(6);
                    Bank.boxOfficeOne(6, Action.INSERT_MONEY, 10000);
                    queueOne.take();
                    Thread.sleep(1);
                    queueThree.put(6);
                    Bank.boxOfficeThree(6, Action.MONEY_TRANSFER, 3500, 5);
                    queueThree.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    queueTwo.put(7);
                    Bank.boxOfficeTwo(7, Action.INSERT_MONEY, 5000);
                    queueTwo.take();
                    Thread.sleep(1);
                    queueThree.put(7);
                    Bank.boxOfficeThree(7, Action.PAYMENT, 6000);
                    queueThree.take();
                    Thread.sleep(1);
                    queueTwo.put(7);
                    Bank.boxOfficeTwo(7, Action.WITH_DRAW, 6000);
                    queueTwo.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        timer.cancel();
        System.out.println("у банка закончился рабочий день");
    }
}
