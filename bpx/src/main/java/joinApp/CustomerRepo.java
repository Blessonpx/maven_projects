package joinApp;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import joinApp.CustomerLookup;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerLookup,Long>{
    @Query(value = "SELECT CUSTOMER_ID FROM CUSTOMER_LOOKUP WHERE CUSTOMER_KEY=:custKey", nativeQuery = true)
    List<CustomerLookup> findValue(@Param("custKey") String customer_key);
}