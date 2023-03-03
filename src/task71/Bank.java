package task.app.task71;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank extends TimerTask {

    private static final int HOW_MUCH_TO_TOP_UP_CASH_REGISTER = 10000;
    private static final Storage storage = new Storage();

    private static final ConcurrentHashMap<Integer, Score> mapClient = new ConcurrentHashMap<>();

    private static final BoxOffice boxOfficeOne = new BoxOffice(new AtomicInteger(10000));
    private static final BoxOffice boxOfficeTwo = new BoxOffice(new AtomicInteger(10000));
    private static final BoxOffice boxOfficeThree = new BoxOffice(new AtomicInteger(10000));

    public static void boxOfficeOne(int client, Action action, int money) {
        System.out.println("к  кассе подошел поток " + client);
        if (!mapClient.containsKey(client)) {
            mapClient.put(client, new Score(0));
        }
        if (action == Action.WITH_DRAW) {
            withDraw(client, money, boxOfficeOne);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, boxOfficeOne);
        } else if (action == Action.PAYMENT) {
            payment(client, money, boxOfficeOne);
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
            withDraw(client, money, boxOfficeTwo);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, boxOfficeTwo);
        } else if (action == Action.PAYMENT) {
            payment(client, money, boxOfficeTwo);
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
            withDraw(client, money, boxOfficeThree);
        } else if (action == Action.INSERT_MONEY) {
            insertMoney(client, money, boxOfficeThree);
        } else if (action == Action.PAYMENT) {
            payment(client, money, boxOfficeThree);
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

    private static void withDraw(int client, int money, BoxOffice boxOffice) {
        if (mapClient.get(client).getMoney() >= money) {
            if (boxOffice.getAmountOfMoneyInCashRegister() >= money) {
                mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                boxOffice.setAmountOfMoneyInCashRegister(boxOffice.getAmountOfMoneyInCashRegister() - money);
                System.out.println(client + " забрал деньги");
            } else {
                System.out.println("касса выдает все деньги, имеющиеся в наличии (" + boxOffice.getAmountOfMoneyInCashRegister() + ") клиенту " + client + " и делает запрос в хранилище на выдочу оставшихся средств");
                mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
                storage.setStorage(storage.getStorage() + (boxOffice.getAmountOfMoneyInCashRegister() - money));
                boxOffice.setAmountOfMoneyInCashRegister(0);
                System.out.println(client + " забрал деньги");
            }
        } else {
            System.out.println("на вашем счету не хватает сдредств. Вы хотите снять " + money + ", но у вас на счету " + mapClient.get(client).getMoney());
        }
    }

    private static void insertMoney(int client, int money, BoxOffice boxOffice) {
        System.out.println(client + " положил деньги на счет");
        mapClient.get(client).setMoney(mapClient.get(client).getMoney() + money);
        boxOffice.setAmountOfMoneyInCashRegister(boxOffice.getAmountOfMoneyInCashRegister() + money);

    }

    private static void payment(int client, int money, BoxOffice boxOffice) {
        if (mapClient.get(client).getMoney() > money) {
            System.out.println(client + " произвел оплату");
            mapClient.get(client).setMoney(mapClient.get(client).getMoney() - money);
            boxOffice.setAmountOfMoneyInCashRegister(boxOffice.getAmountOfMoneyInCashRegister() + money);
        } else {
            System.out.println("у клиента " + client + " нехватает средств на счету для оплаты чего-то");
        }
    }

    private static void currencyExchange(int client) {
        System.out.println(client + " купил доллар");
        mapClient.get(client).setMoney(mapClient.get(client).getMoney() / 10);
    }

    @Override
    public void run() {
        takeMoney(boxOfficeOne);
        takeMoney(boxOfficeTwo);
        takeMoney(boxOfficeThree);
        issueMoney(boxOfficeOne);
        issueMoney(boxOfficeTwo);
        issueMoney(boxOfficeThree);
        if (storage.getStorage() <= 0) {
            System.out.println("у банка закончались деньги");
        }
    }

    private void takeMoney(BoxOffice boxOffice) {
        if (boxOffice.getAmountOfMoneyInCashRegister() >= 20000) {
            storage.setStorage(storage.getStorage() + (boxOffice.getAmountOfMoneyInCashRegister() - HOW_MUCH_TO_TOP_UP_CASH_REGISTER));
            boxOffice.setAmountOfMoneyInCashRegister(HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
        }
    }

    private void issueMoney(BoxOffice boxOffice) {
        if (boxOffice.getAmountOfMoneyInCashRegister() <= 5000) {
            boxOffice.setAmountOfMoneyInCashRegister(boxOffice.getAmountOfMoneyInCashRegister() + HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
            storage.setStorage(storage.getStorage() - HOW_MUCH_TO_TOP_UP_CASH_REGISTER);
        }
    }
}
