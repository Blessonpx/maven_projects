package joinApp;


import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import joinApp.ApiService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController 
public class Controller{

    @Autowired
    private ApiService service ;

    @PostMapping("/sample_endpoint_1")
    public HashMap<String,Object> getSampleEndpoint1(@RequestBody HashMap<String,String> request){
        HashMap<String,Object> response = new HashMap<String,Object>();
        String customer_key= request.get("Customer_Key");
        response=service.getCustomerRep(customer_key);
        return response;
    }
}