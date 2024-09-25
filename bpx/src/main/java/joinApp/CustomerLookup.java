package joinApp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name ="CUSTOMER_LOOKUP")
public class CustomerLookup implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="CUSTOMER_ID")
    private long custId;
    public long getCustId(){return custId;}

    @Column (name="CUSTOMER_KEY")
    private String customerKey;
    public String getCustKey(){return customerKey;}

}