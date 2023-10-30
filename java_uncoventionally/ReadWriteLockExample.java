import java.util.concurrent.locks.*;

public class ReadWriteLockExample {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    private int data;

    public int readData() {

        readLock.lock();

        try {
            // multiple readers can enter this section if not locked for writing,
            // and not writers waiting to lock for writing.
            return data; // Read the data

        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int newValue) {

        writeLock.lock();
        try {
            // only one writer can enter this section and
            // only if no threads are currently reading.
            data = newValue; // Modify the data

        } finally {
            writeLock.unlock();
        }
    }
}
