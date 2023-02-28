package task.app.task71;

public class Score {
    private int money;

    Score(int money) {
        this.money = money;
    }

    public int setMoney(int money) {
        this.money = money;
        return money;
    }

    public int getMoney() {
        return money;
    }
}
