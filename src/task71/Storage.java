package task.app.task71;

import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static AtomicInteger storage = new AtomicInteger(100000);

    public void setStorage(int storage) {
        Storage.storage.set(storage);
    }

    public int getStorage() {
        return storage.get();
    }
}
