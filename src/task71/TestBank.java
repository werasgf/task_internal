package task.app.task71;

import java.util.Timer;
import java.util.TimerTask;

public class TestBank {
    public static void main(String[] args) {
        System.out.println("банк начал работать");
        TimerTask myBank = new Bank();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(myBank, 0, 1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(1, Action.INSERT_MONEY, 20000);
                    Thread.sleep(1);
                    Bank.queue(1, Action.WITH_DRAW, 10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(2, Action.INSERT_MONEY, 20000);
                    Thread.sleep(1);
                    Bank.queue(2, Action.PAYMENT, 1000);
                    Thread.sleep(1);
                    Bank.queue(2, Action.WITH_DRAW, 10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(3, Action.INSERT_MONEY, 10000);
                    Thread.sleep(1);
                    Bank.queue(3, Action.MONEY_TRANSFER, 10000, 10);
                    Thread.sleep(1);
                    Bank.queue(3, Action.MONEY_TRANSFER, 1000, 2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(4, Action.INSERT_MONEY, 5000);
                    Thread.sleep(1);
                    Bank.queue(4, Action.CURRENCY_EXCHANGE, 5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(5, Action.INSERT_MONEY, 10000);
                    Thread.sleep(1);
                    Bank.queue(5, Action.MONEY_TRANSFER, 5000, 3);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bank.queue(6, Action.INSERT_MONEY, 20000);
                    Thread.sleep(1);
                    Bank.queue(6, Action.INSERT_MONEY, 10000);
                    Thread.sleep(1);
                    Bank.queue(6, Action.MONEY_TRANSFER, 3500, 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Bank.queue(7, Action.INSERT_MONEY, 5000);
                    Thread.sleep(1);
                    Bank.queue(7, Action.PAYMENT, 6000);
                    Thread.sleep(1);
                    Bank.queue(7, Action.WITH_DRAW, 6000);
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
