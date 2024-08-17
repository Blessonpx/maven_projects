package parallelInject;

import org.springframework.stereotype.Repository;
import parallelInject.CLMEventExecution_RealTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;



@Repository
public interface CLMEERealTimeRepository extends JpaRepository<CLMEventExecution_RealTime, Long> {
    @Procedure(name = "CLM_Event_Execution_RealTime")
    Object[] CLM_Event_Execution_RealTime(@Param("EventId") Integer EventId,@Param("MinId") Integer MinId,@Param("MaxId") Integer MaxId);
}