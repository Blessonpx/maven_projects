package parallelInject;

import org.springframework.stereotype.Service;
import parallelInject.SharedResource;
import parallelInject.CustomerMaxIdRepo;
import parallelInject.CLMEERealTimeRepository;
import parallelInject.FunctionCallResource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.TimeUnit;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.context.ApplicationContext;
import parallelInject.WorkerThreadFuncCall;



@Service
public class MyService{

    @Autowired
    private SharedResource sharedResource ;
    @Autowired
    private CustomerMaxIdRepo custmaxRepo ;
    @Autowired
    private CLMEERealTimeRepository clmeeRTRepo;
    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FunctionCallResource funcCallResource ;

    @Autowired
    private ApplicationContext context;



    public int getTest() {
        return 1000;
    }

    public int getMaxCustomerId() {
        return custmaxRepo.getMaxCustomerId().get(0).getCustId();
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


    @Transactional
    public Long getParallelProcValue(){

        StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CLM_Event_Execution_RealTime");
        proc.setParameter("EventId", 93);
        proc.setParameter("MinId", 1);
        proc.setParameter("MaxId", 9300000);
        proc.execute();
        Long res1 = (Long) proc.getOutputParameterValue("voutSize");
        return res1;

    }


    public void runFunctionCallResourceThreads(){
        int numThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            FunctionCallResource funcCallResource = context.getBean(FunctionCallResource.class, 93, 1, 9300000);
            Runnable workerThread_l = new WorkerThreadFuncCall(funcCallResource);
            executorService.execute(workerThread_l);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // Handle exception
            System.out.println("Execution_Stopped Due to Thread Interuppted");
        }
    }

    // private FunctionCallResource createFunctionCallResource(int eventId, int custMinId, int custMaxId) {
    //     return new FunctionCallResource(eventId, custMinId, custMaxId);
    // }


}