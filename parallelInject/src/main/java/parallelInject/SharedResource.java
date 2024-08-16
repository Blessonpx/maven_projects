package parallelInject;

import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Component;

@Component
public class SharedResource {
    private int counter;
    //private Lock lock;
    private ReentrantLock lock = new ReentrantLock();

    public SharedResource() {
        counter = 0;
        lock = new ReentrantLock();
    }
    
    public void incrementCounter() {
        lock.lock();
        try {
            counter++;
            System.out.println("Debug: in incrementCounter(), Counter=" + counter);
        } finally {
            lock.unlock();
        }
    }
    
    public int getCounter() {
        return counter;
    }
}