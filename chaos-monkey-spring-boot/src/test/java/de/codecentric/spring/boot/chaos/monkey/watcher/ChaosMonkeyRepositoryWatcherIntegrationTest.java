package de.codecentric.spring.boot.chaos.monkey.watcher;

import de.codecentric.spring.boot.demo.chaos.monkey.ChaosDemoApplication;
import de.codecentric.spring.boot.demo.chaos.monkey.repository.CrudDemoRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ChaosMonkeyRepositoryWatcherIntegrationTest {


    @SpringBootTest(properties = {"chaos.monkey.watcher.repository=true", "chaos.monkey.assaults.exceptions-active=true"}, classes = {ChaosDemoApplication.class})
    @ActiveProfiles("chaos-monkey")
    @Nested
    class RepositoryIntegrationTest {

        @Autowired
        private CrudDemoRepository crudDemoRepository;

        @Test
        public void testIfRepositoryCallThrowsException() {
            assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> crudDemoRepository.count());
        }
    }
}
