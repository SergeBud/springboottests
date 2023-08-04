package sb.test.springboottest.junit;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class JUnitTestConfig {

    protected static final String TEST_COLLECTION = "test-collection";

    private static final String VERSION = "3.6";
    private static final String DATABASE_NAME = "test";

    public MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:" + VERSION).withExposedPorts(27017)
                                                                                       .withReuse(true);

    @Bean
    public MongoClient mongoClient() {
        if(!mongoDBContainer.isRunning()) {
            System.out.println("=============================================");
            System.out.println("=                                           =");
            System.out.println("=                                           =");
            System.out.println("=           Starting mongodb                =");
            System.out.println("=                                           =");
            System.out.println("=                                           =");
            System.out.println("=============================================");
            mongoDBContainer.start();
        }

        while(!mongoDBContainer.isRunning()) {
            try {
                Thread.sleep(1_000L);
            } catch(InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return MongoClients.create(mongoDBContainer.getReplicaSetUrl());
    }

    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory(@Autowired MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, DATABASE_NAME);
    }

    @Bean
    public MongoTemplate mongoTemplate(@Autowired SimpleMongoClientDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }
}
