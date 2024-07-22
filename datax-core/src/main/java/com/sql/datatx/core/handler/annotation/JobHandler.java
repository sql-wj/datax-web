package com.sql.datatx.core.handler.annotation;

import java.lang.annotation.*;

/**
 * annotation for job handler
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface JobHandler {

    String value() default "";

}
