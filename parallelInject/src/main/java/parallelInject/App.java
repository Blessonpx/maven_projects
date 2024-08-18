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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@SpringBootApplication
@EnableJpaRepositories(basePackages = "parallelInject")
@EntityScan(basePackages = "parallelInject")
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
        System.out.println("Get the max Customer Id");
        System.out.println(service.getMaxCustomerId());
        System.out.println("Get the Procedure Call Output");
        System.out.println(service.getParallelProcValue());
        System.out.println("Calling Parallel Proce method 2");
        service.runFunctionCallResourceThreads();
        System.exit(0);
    }
}
