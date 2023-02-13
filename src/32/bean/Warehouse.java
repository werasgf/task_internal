package task.bean;

public class Warehouse {

    String model;
    int volume;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Склад{" +
                "номер='" + model + '\'' +
                ", объем=" + volume +
                '}';
    }
}
