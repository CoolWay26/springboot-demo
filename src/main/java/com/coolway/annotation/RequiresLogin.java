package com.coolway.annotation;

import java.lang.annotation.*;

@Repeatable(RequiresLogins.class)
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresLogin {
    String loginName() default "zhangsan";
}
