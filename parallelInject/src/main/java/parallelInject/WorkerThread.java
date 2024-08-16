package parallelInject;

import parallelInject.SharedResource;
import org.springframework.stereotype.Component;


@Component
public class WorkerThread implements Runnable {
    private SharedResource sharedResource;

    public WorkerThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    
    @Override
    public void run() {
        // Perform some work
        sharedResource.incrementCounter();
    }
}