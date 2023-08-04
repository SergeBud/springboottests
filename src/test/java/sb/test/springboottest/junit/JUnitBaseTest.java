package sb.test.springboottest.junit;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MongoDBContainer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = JUnitTestConfig.class)
@ComponentScan(basePackages = {"sb.test"})
public abstract class JUnitBaseTest {

    protected static final String TEST_COLLECTION = "test-collection";

    private static final String VERSION = "3.6";

    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:" + VERSION)
            .withExposedPorts(27017)
            .withReuse(true);

    @Before
    //@DynamicPropertySource
    public void init(/*DynamicPropertyRegistry registry*/) {
        if(mongoDBContainer.isRunning()) {
            return;
        }

        System.out.println("=============================================");
        System.out.println("=                                           =");
        System.out.println("=                                           =");
        System.out.println("=           Starting mongodb                =");
        System.out.println("=                                           =");
        System.out.println("=                                           =");
        System.out.println("=============================================");
        mongoDBContainer.start();
        //registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @DynamicPropertySource
    static void mongoSettings(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
