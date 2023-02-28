package task.app.task71;

import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Bank extends TimerTask implements Runnable {
    private static final BlockingQueue<Integer> queueOne = new ArrayBlockingQueue<>(1, true);
    private static final BlockingQueue<Integer> queueTwo = new ArrayBlockingQueue<>(1, true);
    private static final BlockingQueue<Integer> queueThree = new ArrayBlockingQueue<>(1, true);

    private static final int HOW_MUCH_TO_TOP_UP_CASH_REGISTER = 10000;
    private static final Storage storage = new Storage();

    private static ConcurrentHashMap<Integer, Score> mapClient = new ConcurrentHashMap<>();


    volatile static BoxOffice boxOfficeOne = new BoxOffice(10000);
    volatile static BoxOffice boxOfficeTwo = new BoxOffice(10000);
    volatile static BoxOffice boxOfficeThree = new BoxOffice(10000);

    public static void queue(int client, Action action, int money) {
        try {
            if (queueOne.offer(client)) {
                boxOfficeOne(client, action, money);
                queueOne.take();
            } else if (queueTwo.offer(client)) {
                boxOfficeTwo(client, action, money);
                queueTwo.take();
            } else if (queueThree.offer(client)) {
                boxOfficeThree(client, action, money);
                queueThree.take();
            } else {
                Thread.sleep(1);
                queue(client, action, money);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void queue(int clientOne, Action action, int money, int clientTwo) {
        try {
            if (queueOne.offer(clientOne)) {
                boxOfficeOne(clientOne, action, money, clientTwo);
                queueOne.take();
            } else if (queueTwo.offer(clientOne)) {
                boxOfficeTwo(clientOne, action, money, clientTwo);
                queueTwo.take();
            } else if (queueThree.offer(clientOne)) {
                boxOfficeThree(clientOne, action, money, clientTwo);
                queueThree.take();
            } else {
                Thread.sleep(1);
                queue(clientOne, action, money, clientTwo);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void boxOfficeOne(int client, Action action, int money) {
        System.out.println("к  кассе подошел поток " + client);
        if (!mapClient.containsKey(client)) {
            mapClient.put(client, new Score(0));
        }
        if (action == Action.WITH_DRAW) {
            withDraw(client, money, 1);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, 1);
        } else if (action == Action.PAYMENT) {
            payment(client, money, 1);
        } else if (action == Action.CURRENCY_EXCHANGE) {
            currencyExchange(client);
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + client);
    }

    public static void boxOfficeTwo(int client, Action action, int money) {
        System.out.println("к  кассе подошел поток " + client);
        if (!mapClient.containsKey(client)) {
            mapClient.put(client, new Score(0));
        }
        if (action == Action.WITH_DRAW) {
            withDraw(client, money, 2);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, 2);
        } else if (action == Action.PAYMENT) {
            payment(client, money, 2);
        } else if (action == Action.CURRENCY_EXCHANGE) {
            currencyExchange(client);
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + client);
    }

    public static void boxOfficeThree(int client, Action action, int money) {
        System.out.println("к  кассе подошел поток " + client);
        if (!mapClient.containsKey(client)) {
            mapClient.put(client, new Score(0));
        }
        if (action == Action.WITH_DRAW) {
            withDraw(client, money, 3);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, 3);
        } else if (action == Action.PAYMENT) {
            payment(client, money, 3);
        } else if (action == Action.CURRENCY_EXCHANGE) {
            currencyExchange(client);
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + client);
    }

    public static void boxOfficeOne(int clientOne, Action action, int money, int clientTwo) {
        System.out.println("к  кассе подошел поток " + clientOne);
        if (!mapClient.containsKey(clientOne)) {
            mapClient.put(clientOne, new Score(money));
        }
        if (action == Action.MONEY_TRANSFER) {
            if (mapClient.containsKey(clientTwo)) {
                mapClient.get(clientOne).setMoney(mapClient.get(clientOne).getMoney() - money);
                mapClient.get(clientTwo).setMoney(mapClient.get(clientTwo).getMoney() + money);
            } else {
                System.out.println("счета " + clientTwo + " нет в банке");
            }
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + clientOne);
    }

    public static void boxOfficeTwo(int clientOne, Action action, int money, int clientTwo) {
        System.out.println("к  кассе подошел поток " + clientOne);
        if (!mapClient.containsKey(clientOne)) {
            mapClient.put(clientOne, new Score(money));
        }
        if (action == Action.MONEY_TRANSFER) {
            if (mapClient.containsKey(clientTwo)) {
                mapClient.get(clientOne).setMoney(mapClient.get(clientOne).getMoney() - money);
                mapClient.get(clientTwo).setMoney(mapClient.get(clientTwo).getMoney() + money);
            } else {
                System.out.println("счета " + clientTwo + " нет в банке");
            }
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + clientOne);
    }

    public static void boxOfficeThree(int clientOne, Action action, int money, int clientTwo) {
        System.out.println("к  кассе подошел поток " + clientOne);
        if (!mapClient.containsKey(clientOne)) {
            mapClient.put(clientOne, new Score(money));
        }
        if (action == Action.MONEY_TRANSFER) {
            if (mapClient.containsKey(clientTwo)) {
                mapClient.get(clientOne).setMoney(mapClient.get(clientOne).getMoney() - money);
                mapClient.get(clientTwo).setMoney(mapClient.get(clientTwo).getMoney() + money);
            } else {
                System.out.println("счета " + clientTwo + " нет в банке");
            }
        } else {
            System.out.println("таких услуг банк не предоставляет");
        }
        System.out.println("из кассы ушел поток " + clientOne);
    }

    private static void withDraw(int client, int money, int numberBoxOffice) {
        if (numberBoxOffice == 1) {
            if (mapClient.get(client).getMoney() >= money) {
                if (boxOfficeOne.getAmountOfMoneyInCashRegister() <= money) {
                    mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                    boxOfficeOne.setAmountOfMoneyInCashRegister(boxOfficeOne.getAmountOfMoneyInCashRegister() - money);
                    System.out.println(client + " забрал деньги");
                } else {
                    System.out.println("касса не может выдать столько денег");
                }
            } else {
                System.out.println("на вашем счету не хватает сдредств. Вы хотите снять " + money + ", но у вас на счету " + mapClient.get(client).getMoney());
            }
        } else if (numberBoxOffice == 2) {
            if (mapClient.get(client).getMoney() >= money) {
                if (boxOfficeTwo.getAmountOfMoneyInCashRegister() <= money) {
                    mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                    boxOfficeTwo.setAmountOfMoneyInCashRegister(boxOfficeTwo.getAmountOfMoneyInCashRegister() - money);
                    System.out.println(client + " забрал деньги");
                } else {
                    System.out.println("касса не может выдать столько денег");
                }
            } else {
                System.out.println("на вашем счету не хватает сдредств. Вы хотите снять " + money + ", но у вас на счету " + mapClient.get(client).getMoney());
            }
        } else {
            if (mapClient.get(client).getMoney() >= money) {
                if (boxOfficeThree.getAmountOfMoneyInCashRegister() <= money) {
                    mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                    boxOfficeThree.setAmountOfMoneyInCashRegister(boxOfficeThree.getAmountOfMoneyInCashRegister() - money);
                    System.out.println(client + " забрал деньги");
                } else {
                    System.out.println("касса не может выдать столько денег");
                }
            } else {
                System.out.println("на вашем счету не хватает сдредств. Вы хотите снять " + money + ", но у вас на счету " + mapClient.get(client).getMoney());
            }
        }
    }

    private static void insertMoney(int client, int money, int numberBoxOffice) {
        if (numberBoxOffice == 1) {
            System.out.println(client + " положил деньги на счет");
            mapClient.get(client).setMoney(mapClient.get(client).getMoney() + money);
            boxOfficeOne.setAmountOfMoneyInCashRegister(boxOfficeOne.getAmountOfMoneyInCashRegister() + money);
        } else if (numberBoxOffice == 2) {
            System.out.println(client + " положил деньги на счет");
            mapClient.get(client).setMoney(mapClient.get(client).getMoney() + money);
            boxOfficeTwo.setAmountOfMoneyInCashRegister(boxOfficeTwo.getAmountOfMoneyInCashRegister() + money);
        } else {
            System.out.println(client + " положил деньги на счет");
            mapClient.get(client).setMoney(mapClient.get(client).getMoney() + money);
            boxOfficeThree.setAmountOfMoneyInCashRegister(boxOfficeThree.getAmountOfMoneyInCashRegister() + money);
        }
    }

    private static void payment(int client, int money, int numberBoxOffice) {
        if (numberBoxOffice == 1) {
            if (mapClient.get(client).getMoney() > money) {
                System.out.println(client + " произвел оплату");
                mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                boxOfficeOne.setAmountOfMoneyInCashRegister(boxOfficeOne.getAmountOfMoneyInCashRegister() + money);
            } else {
                System.out.println("у клиента " + client + " нехватает средств на счету для оплаты чего-то");
            }
        } else if (numberBoxOffice == 2) {
            if (mapClient.get(client).getMoney() > money) {
                System.out.println(client + " произвел оплату");
                mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                boxOfficeTwo.setAmountOfMoneyInCashRegister(boxOfficeTwo.getAmountOfMoneyInCashRegister() + money);
            } else {
                System.out.println("у клиента " + client + " нехватает средств на счету для оплаты чего-то");
            }
        } else {
            if (mapClient.get(client).getMoney() > money) {
                System.out.println(client + " произвел оплату");
                mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                boxOfficeThree.setAmountOfMoneyInCashRegister(boxOfficeThree.getAmountOfMoneyInCashRegister() + money);
            } else {
                System.out.println("у клиента " + client + " нехватает средств на счету для оплаты чего-то");
            }
        }
    }

    private static void currencyExchange(int client) {
        System.out.println(client + " купил доллар");
        mapClient.get(client).setMoney(mapClient.get(client).getMoney() / 10);
    }

    @Override
    public void run() {
        if (boxOfficeOne.getAmountOfMoneyInCashRegister() <= 5000) {
            boxOfficeOne.setAmountOfMoneyInCashRegister(boxOfficeOne.getAmountOfMoneyInCashRegister() + HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
            storage.setStorage(storage.getStorage() - HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
        }
        if (boxOfficeTwo.getAmountOfMoneyInCashRegister() <= 5000) {
            boxOfficeTwo.setAmountOfMoneyInCashRegister(boxOfficeTwo.getAmountOfMoneyInCashRegister() + HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
            storage.setStorage(storage.getStorage() - HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
        }
        if (boxOfficeThree.getAmountOfMoneyInCashRegister() <= 5000) {
            boxOfficeThree.setAmountOfMoneyInCashRegister(boxOfficeThree.getAmountOfMoneyInCashRegister() + HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
            storage.setStorage(storage.getStorage() - HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
        }
        if (storage.getStorage() <= 0) {
            System.out.println("у банка закончались деньги");
        }
    }
}
