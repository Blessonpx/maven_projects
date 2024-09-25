package joinApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
/**
 * Hello world!
 *
 */
@SpringBootApplication
@EntityScan(basePackages = "joinApp") 
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Beginning !!!!!" );
        SpringApplication.run(App.class, args);
    }
}
