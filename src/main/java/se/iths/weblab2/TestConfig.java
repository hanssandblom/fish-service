package se.iths.weblab2;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public Service service() {
        return new TestService();
    }
}
