package sb.test.springboottest.sb;

import com.mongodb.client.MongoCollection;
import sb.test.springboottest.TestDocument;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

@Slf4j
@DirtiesContext
public class SpringBootTest1 extends SpringBootBaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @SneakyThrows
    public void test() {
        System.out.println("=============================");
        System.out.println("==========  TEST#1  =========");
        System.out.println("=============================");

        MongoCollection<Document> collection = mongoTemplate.getCollection(TEST_COLLECTION);
        if(Objects.isNull(collection)) {
            mongoTemplate.createCollection(TEST_COLLECTION);
        }

        TestDocument testDocument = TestDocument.builder()
                                                .property("TEST#2")
                                                .build();

        final TestDocument savedDoc = mongoTemplate.save(testDocument, TEST_COLLECTION);

        log.info("Saved doc into DB: {}", savedDoc);
    }
}
