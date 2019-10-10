package com.ly.mt.activity.web.annotations;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * web层访问专用麻豆对象
 * @author panjingtian
 */
@Data
public class TimeDown implements Serializable {


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
    private  int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private int second;

}
