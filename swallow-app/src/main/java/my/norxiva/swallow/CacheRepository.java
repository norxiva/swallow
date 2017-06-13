package my.norxiva.swallow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@CacheDefaults(cacheName = "genKey")
public class CacheRepository {

    @CacheResult
    public String genKey(@CacheKey String key){
        log.info("get key: {}", key);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
    }
}
