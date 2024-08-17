package parallelInject;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;



@Entity
@NamedStoredProcedureQuery(name = "CLM_Event_Execution_RealTime", procedureName = "CLM_Event_Execution_RealTime",
        parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "EventId", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "MinId", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "MaxId", type = Integer.class),
        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "voutSize", type = Long.class)
})
public class CLMEventExecution_RealTime{
    @Id
    private Long countLines;
}
