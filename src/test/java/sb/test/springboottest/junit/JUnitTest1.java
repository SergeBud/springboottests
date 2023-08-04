package sb.test.springboottest.junit;

import com.mongodb.client.MongoCollection;
import sb.test.springboottest.TestDocument;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Objects;

@Slf4j
public class JUnitTest1 extends JUnitBaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

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
