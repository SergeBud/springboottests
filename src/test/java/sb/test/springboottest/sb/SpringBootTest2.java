package sb.test.springboottest.sb;

import sb.test.springboottest.TestDocument;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Slf4j
public class SpringBootTest2 extends SpringBootBaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void test() {
        System.out.println("=============================");
        System.out.println("==========  TEST#2  =========");
        System.out.println("=============================");

        final List<TestDocument> foundDocs = mongoTemplate.findAll(TestDocument.class, TEST_COLLECTION);

        Assertions.assertFalse(foundDocs.isEmpty());

        log.info("Read the doc from DB: {}", foundDocs.get(0));
    }
}
