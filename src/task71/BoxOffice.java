package task.app.task71;

import java.util.concurrent.atomic.AtomicInteger;

public class BoxOffice {
    private final AtomicInteger amountOfMoneyInCashRegister;

    public BoxOffice (AtomicInteger amountOfMoneyInCashRegister) {
        this.amountOfMoneyInCashRegister = amountOfMoneyInCashRegister;
    }

    public void setAmountOfMoneyInCashRegister(int amountOfMoneyInCashRegister) {
        this.amountOfMoneyInCashRegister.set(amountOfMoneyInCashRegister);
    }

    public int getAmountOfMoneyInCashRegister() {
        return amountOfMoneyInCashRegister.get();
    }
}
