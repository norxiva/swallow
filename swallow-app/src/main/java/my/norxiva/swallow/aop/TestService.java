package my.norxiva.swallow.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestService {

    @TestAnnotation(exceptions = {RuntimeException.class})
    public void test(TestBean bean){

        log.info("testing...");
        int i = 0;
        if (i == 0){
//            throw new RuntimeException();
        }
    }

    @TestAnnotation(exceptions = {RuntimeException.class})
    public void test2(String name, String password){
        log.info("test2 testing...");
        if (name.length() > 0 ){
            throw new RuntimeException("name.length is too long.");
        }
    }

}
