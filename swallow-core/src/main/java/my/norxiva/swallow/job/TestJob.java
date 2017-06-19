package my.norxiva.swallow.job;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Getter
@Setter
public class TestJob implements Callable {

    private Map<String, Object> params;

    @Override
    public Boolean call() throws Exception {
        log.info("calling in TestJob: {}", params);
        return true;
    }
}
