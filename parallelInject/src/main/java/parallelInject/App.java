package parallelInject;

/**
 * Hello world!
 *
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import parallelInject.MyService;
@SpringBootApplication
public class App implements CommandLineRunner 
{
    @Autowired
	private MyService service;
    public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
    @Override
    public void run(String... args) throws Exception {
        // Your computation logic here
        System.out.println("Running computations...");
		System.out.println("Getting Service Class details...");
        // Once done, exit the 
		System.out.println(service.getTest());
		service.runParallelThreads();
        System.exit(0);
    }
}
