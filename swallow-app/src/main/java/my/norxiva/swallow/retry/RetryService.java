package my.norxiva.swallow.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RetryService {

    @Retryable(value= {Exception.class},maxAttempts = 5,
            backoff = @Backoff(delay = 5000L,multiplier = 1))
    public void test(){
        log.info("retry testing...");

        int i = 0;
        if (i == 0){
            throw new RuntimeException();
        }
    }
}
