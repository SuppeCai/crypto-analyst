package me.bl0ckchain.analyst.core.monitoring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 06/06/2018
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Analyzer {

    /**
     * Name of Analyzer.
     *
     * @return
     */
    String value() default "";
}
