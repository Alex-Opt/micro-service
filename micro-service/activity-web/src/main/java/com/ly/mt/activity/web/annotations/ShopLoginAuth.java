package com.ly.mt.activity.web.annotations;


import java.lang.annotation.*;

/**
 * @description 登录鉴权简单方法注解
 *
 * 用于需要坚定用户是否登录的状态上的方法注解
 * 用在controller层需要商家登录的方法上
 *
 * @author panjingtian
 * @date 2019/6/12 11:18 AM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ShopLoginAuth {
}
