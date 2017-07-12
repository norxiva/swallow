package my.norxiva.swallow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableCaching
@EnableRetry
@EnableWebSecurity
@EnableAsync
@EnableScheduling
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
