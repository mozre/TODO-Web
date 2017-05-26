package com.ckt.utils;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by mozre on 2017/5/25.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisDao {
    String value() default "";
}
