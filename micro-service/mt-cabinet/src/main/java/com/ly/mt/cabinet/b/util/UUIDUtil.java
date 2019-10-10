package com.ly.mt.cabinet.b.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

  public static String creatUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static String getRandomStr(final int sum) {
    String base = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < sum; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }

}
