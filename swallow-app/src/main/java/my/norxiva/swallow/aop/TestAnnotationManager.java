package my.norxiva.swallow.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class TestAnnotationManager implements InitializingBean {

//    @Pointcut("execution(public * ((@TestAnnotation *)+).*(..)) && within(@TestAnnotation *)")
//    @Pointcut("execution(void test())")
    private void aspectMethod(){}

    public void afterPropertiesSet() throws Exception {

    }

    @Before("@annotation(TestAnnotation)")
//    @Before("aspectMethod()")
    public void testBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        TestAnnotation[] testAnnotations = method.getAnnotationsByType(TestAnnotation.class);
        log.info("testBefore.length[{}]",testAnnotations.length);

        Arrays.asList(testAnnotations).forEach(annotation -> log.info("testBefore testAnnotation [{}]",Arrays.asList(annotation.exceptions())));

        log.info("testBefore: [{}]", joinPoint.getTarget().toString());
        log.info("testBefore: [{}]", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "@annotation(TestAnnotation)", throwing = "ex")
    public void testAfterThrowing(JoinPoint joinPoint, Exception ex){

        log.info("testAfterThrowing: [{}][{}]", joinPoint.getSignature().getName(), ex.getMessage());
    }




}
