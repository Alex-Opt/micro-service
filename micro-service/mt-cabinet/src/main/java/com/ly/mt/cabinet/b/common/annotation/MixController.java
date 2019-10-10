package com.ly.mt.cabinet.b.common.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;


/**
 * 组合
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@RestController
@RequestMapping
public  @interface MixController {

    @AliasFor(
            annotation = RequestMapping.class,
            attribute = "path"
    )
    String[] value() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String name() default "";

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] path() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    RequestMethod[] method() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] headers() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] consumes() default {};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] produces() default {"application/json; charset=UTF-8"};

    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] params() default {};


}
