package task.app.task34;

public class Parking {
    private final boolean[] places = {false, true, false, true, false, false, true, true};

    public void findParkingPlace(int place) {
        for (int i = place-1; i < places.length; i++) {
            if (places[i]) {
                places[i] = false;
                System.out.println("машина припарковалась на месте " + (i + 1));
                break;
            }
        }
    }

    public void quitParkingPlace(int place) {
        if (places[place - 1]) {
            System.out.println("место уже было свободным");
        } else {
            places[place - 1] = true;
            System.out.println("место номер " + place + " освободилось");
        }

    }
}
