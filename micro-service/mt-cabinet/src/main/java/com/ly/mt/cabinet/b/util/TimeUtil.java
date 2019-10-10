package com.ly.mt.cabinet.b.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangchaoqun
 */
public class TimeUtil {
  public static String getFormatDate(Long timestamp) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
    return sdf.format(new Date(timestamp));
  }

  public static String getSpecifiedDay(int n) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, n);
    return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
  }

//  public static void main(String[] args) {
//    System.out.print(getSpecifiedDay(0));
//  }
}
