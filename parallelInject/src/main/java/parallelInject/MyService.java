package parallelInject;

import org.springframework.stereotype.Service;
import parallelInject.SharedResource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.TimeUnit;


@Service
public class MyService{

    @Autowired
    private SharedResource sharedResource ;

    public int getTest() {
        return 1000;
    }

    public void runParallelThreads(){
        int numThreads = 5;
        System.out.println("Point 1");
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        System.out.println("Point 2");
        for (int i = 0; i < numThreads; i++) {
            Runnable workerThread = new WorkerThread(sharedResource);
            executorService.execute(workerThread);
        }
        System.out.println("Point 3");

        executorService.shutdown();
        System.out.println("Point 4");
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            System.out.println("Point 5");
        } catch (InterruptedException e) {
            // Handle exception
            System.out.println("Execution_Stopped Due to Thread Interuppted");
        }
        System.out.println("Point 6");
        System.out.println("Counter: " + sharedResource.getCounter());

    }

}