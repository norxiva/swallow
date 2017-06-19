package my.norxiva.swallow.job;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        log.info(new String(Base64.getDecoder().decode("dXNlcjpjZTljYjc0Zi1kNjA5LTQwMmUtOTcyYS0wYjdhMGIyNTljZDM=".getBytes("UTF-8")), "UTF-8"));
    }
}
