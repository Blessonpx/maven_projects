package parallelInject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Scope;


@Component
@Scope("prototype")
public class FunctionCallResource{
    /**
     * Input of Event_id 
     * Customer_Min_Id and 
     * Customer_Max_Id 
     * and return file count
     */
    private int Event_Id;
    private int CustMinId;
    private int CustMaxId;
    private int fileCount;


    @Autowired
    @PersistenceContext
    private EntityManager em;

    //private Lock lock;
    private ReentrantLock lock = new ReentrantLock();

    public FunctionCallResource(int Event_Id,int CustMinId,int CustMaxId) {
        this.Event_Id=Event_Id;
        this.CustMinId=CustMinId;
        this.CustMaxId=CustMaxId;
        this.lock = new ReentrantLock();
    }

    @Transactional
    public void callParallelProc(){
        lock.lock();
        try{
            StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("CLM_Event_Execution_RealTime");
        System.out.println("Inside callParallelProc");
        proc.setParameter("EventId", Event_Id);
        proc.setParameter("MinId", CustMinId);
        proc.setParameter("MaxId", CustMaxId);
        proc.execute();
        Long res1 = (Long) proc.getOutputParameterValue("voutSize");
        System.out.println("res1=="+res1);
        }finally {
            lock.unlock();
        }
                
    }
}