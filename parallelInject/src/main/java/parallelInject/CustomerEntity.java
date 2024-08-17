package parallelInject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;


@Entity
@Immutable
public class CustomerEntity{
    @Id 
    @Column(name="Customer_Id")
    private int custId;
    public int getCustId(){return custId;}

}