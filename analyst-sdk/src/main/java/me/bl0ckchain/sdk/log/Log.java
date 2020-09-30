package me.bl0ckchain.sdk.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caisupeng
 * @version $Id: Log.java, v 0.1 2018-12-06 3:50 PM caisupeng Exp $$
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    boolean isComplete() default false;
}