package joinApp;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import joinApp.CustomerLookup;
import joinApp.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ApiService{
    @Autowired
    private CustomerRepo custRepo;
    public HashMap<String,Object> getCustomerRep(String customer_key){
        HashMap<String,Object> response = new HashMap<String,Object>();
        List<CustomerLookup> clkp = custRepo.findValue(customer_key);

        response.put("Customer_Id",clkp.get(0).getCustId());
        return response;
    }
}