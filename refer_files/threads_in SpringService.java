/**
 * Service interface defining the contract for object identifiers processing
 */
public interface ProcessingService {

	/**
	 * Processes the list of objects identified by id and returns a an identifiers
	 * list of the successfully processed objects
	 * 
	 * @param objectIds List of object identifiers
	 * 
	 * @return identifiers list of the successfully processed objects
	 */
	List<Integer> processObjects(List objectIds);
}


/**
 * The default implementation of this service is based on database storage. However, the example is very simplistic:
 * Service implementation for database related ids processing
 */
@Service("ProcessingDBService")
public class ProcessingDBService implements ProcessingService {
	
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
    @Transactional
    @Override
    public List processObjects(List objectIds) {
        // Process and save to DB
		
	logger.info("Running in thread " + Thread.currentThread().getName() + " with object ids " + objectIds.toString());
		
	return objectIds.stream().collect(Collectors.toList());
    }
}



/**
 * Now, we would like to have the option to run this processing in chunks and parallel processes. 
 * In order to keep the code clean and decoupled from its runtime context, we will use the Decorator pattern as follows:
 * Service implementation for parallel chunk processing
 */
@Service
@Primary
@ConditionalOnProperty(prefix = "service", name = "parallel", havingValue = "true")
public class ProcessingServiceParallelRunDecorator implements ProcessingService {

	private ProcessingService delegate;
	
	public ProcessingServiceParallelRunDecorator(ProcessingService delegate) {
		this.delegate = delegate;
	}

	/**
	 * In a real scenario it should be an external configuration
	 */
	private int batchSize = 10;

	@Override
	public List<Integer> processObjects(List objectIds) {
		List<List<Integer>> chuncks = getBatches(objectIds, batchSize);
		List<List<Integer>> processedObjectIds = chuncks.parallelStream().map(delegate::processObjects)
				.collect(Collectors.toList());

		return flatList(processedObjectIds);
	}

	private List<List<Integer>> getBatches(List collection, int batchSize) {
		return IntStream.iterate(0, i -> i < collection.size(), i -> i + batchSize)
				.mapToObj(i -> collection.subList(i, Math.min(i + batchSize, collection.size())))
				.collect(Collectors.toList());
	}

	private List<Integer> flatList(List> listOfLists) {
		return listOfLists.stream().collect(ArrayList::new, List::addAll, List::addAll);
	}



@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest(properties = { "service.parallel=false" })
public class ProcessingServiceTest {
	
	@Autowired
	ProcessingService processingService;
	
	ProcessingService processingServiceDecorator;
	
	@Test
	public void shouldRunParallelProcessingUsingDecorator() {
		processingServiceDecorator = new ProcessingServiceParallelRunDecorator(processingService);
		List objectIds = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
		
		List resultList = processingServiceDecorator.processObjects(objectIds);
		
		Assert.assertEquals(objectIds, resultList);
	}
	
}