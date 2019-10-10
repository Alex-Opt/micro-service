package com.ly.mt.activity.web.annotations;


import java.lang.annotation.*;

/**
 * @description
 * web层接口定时拒绝注解
 *
 * 格式如下，为结束时候年月日时分秒
 * {\"year\": 1,\"month\": 2,\"dayOfMonth\": 3,\"hour\": 4,\"minute\": 5,\"second\": 5}
 *
 * @author panjingtian
 * @date 2019/8/22 2:42 PM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimingMethodDown {


    /**
     {
     "year": 1,
     "month": 2,
     "dayOfMonth": 3,
     "hour": 4,
     "minute": 5,
     "second": 5
     }
     */

    /**
     * 结束时间
     * 格式为jsong格式
     *
     * @return
     */
    //  {\"year\": 1,\"month\": 2,\"dayOfMonth\": 3,\"hour\": 4,\"minute\": 5,\"second\": 5};
    String downtime();


}
