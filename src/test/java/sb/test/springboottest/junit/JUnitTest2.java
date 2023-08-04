package sb.test.springboottest.junit;

import sb.test.springboottest.TestDocument;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Slf4j
public class JUnitTest2 extends JUnitBaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    @SneakyThrows
    public void test() {
        System.out.println("=============================");
        System.out.println("==========  TEST#2  =========");
        System.out.println("=============================");

        final List<TestDocument> foundDocs = mongoTemplate.findAll(TestDocument.class, TEST_COLLECTION);

        Assertions.assertFalse(foundDocs.isEmpty());

        log.info("Read the doc from DB: {}", foundDocs.get(0));
    }
}
