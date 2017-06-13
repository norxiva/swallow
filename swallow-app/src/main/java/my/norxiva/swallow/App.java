package my.norxiva.swallow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@Configuration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
