package my.norxiva.swallow.aop;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    Class<? extends Throwable>[] exceptions() default {RuntimeException.class};
}
