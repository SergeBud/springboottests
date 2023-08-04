package sb.test.springboottest.sb;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

@DirtiesContext
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SpringbootTestConfig.class)
public abstract class SpringBootBaseTest {

    protected static final String TEST_COLLECTION = "test-collection";

    private static final String VERSION = "3.6";

    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:" + VERSION)
            .withExposedPorts(27017)
            .withReuse(true);

    @BeforeAll
    //@DynamicPropertySource
    public static void init(/*DynamicPropertyRegistry registry*/) {
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
