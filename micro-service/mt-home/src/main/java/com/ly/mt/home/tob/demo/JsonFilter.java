package com.ly.mt.home.tob.demo;

import java.lang.annotation.*;

/**
 * @author joel
 * @description: TODO
 * @date 2019/9/211:25
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonFilter {

}
