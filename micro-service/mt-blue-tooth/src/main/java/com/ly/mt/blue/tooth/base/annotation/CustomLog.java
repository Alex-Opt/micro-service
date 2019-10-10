package com.ly.mt.blue.tooth.base.annotation;

import java.lang.annotation.*;

/**
* @program: mt-blue-tooth
* @description: 自定义 打印客户端入参/服务端出参
* @author: wanghongliang
* @create: 2019/7/24 15:14
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface CustomLog {
    String logStr() default "";
}