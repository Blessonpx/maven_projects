package parallelInject;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import parallelInject.CustomerEntity;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerMaxIdRepo extends JpaRepository<CustomerEntity,Integer>{
    @Query(value="SELECT MAX(Customer_Id) AS Customer_Id FROM customer_list",nativeQuery=true)
    public List<CustomerEntity> getMaxCustomerId();
}