package my.norxiva.swallow;

import my.norxiva.swallow.util.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfig {

    @Bean
    public SnowFlake snowFlake() {
        return new SnowFlake(1L, 1L, SnowFlake.DEFAULT_WORKER_ID_BITS,
                SnowFlake.DEFAULT_DATA_CENTER_ID_BITS, SnowFlake.DEFAULT_SEQUENCE_BITS);
    }
}
