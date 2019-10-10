package com.ly.mt.core.common.util;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Description 字符串操作工具类
 * @Author taoye
 */
public class StringUtil {
    private final static String UNIT_CENTS[] = {"分", "角"};
    private final static String UNIT_YUAN[] = {"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};
    private final static String DIGIT_UPPERCASE[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    /**
     * @Description 判断字符串为空
     * @Author taoye
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        } else if ("NULL".equals(str.toUpperCase())) {
            return true;
        } else if ("UNDEFINED".equals(str.toUpperCase())) {
            return true;
        }
        return false;
    }

    /**
     * @Description 判断字符串非空
     * @Author taoye
     */
    public static boolean isNotEmpty(String str) {
        if (null == str) {
            return false;
        } else if ("".equals(str.trim())) {
            return false;
        } else if ("NULL".equals(str.toUpperCase())) {
            return false;
        } else if ("UNDEFINED".equals(str.toUpperCase())) {
            return false;
        }
        return true;
    }

    /**
     * @Description 判断字符串是否是正整数
     * @Author taoye
     */
    public static boolean isPositiveInteger(String str) {
        return Pattern.matches("^\\d+$", str.trim());
    }

    /**
     * @Description 判断字符串是否是数字
     * @Author taoye
     */
    public static boolean isNumber(String str) {
        if (isEmpty(str)) {
            return false;
        }
        // 如果只有一个"."且不在开头和结尾处则去掉"."当作整数判断
        int count = str.length() - str.replace(".", "").length();
        if (count == 1 && !str.startsWith(".") && !str.endsWith(".")) {
            str = str.replace(".", "");
        }
        // 如果只有一个"-"且在开头处则去掉"-"当作正数判断
        count = str.length() - str.replace("-", "").length();
        if (count == 1 && str.startsWith("-")) {
            str = str.replace("-", "");
        }
        return Pattern.matches("^[0-9]*$", str);
    }

    /**
     * @Description 判断字符串是否是中文
     * @Author taoye
     */
    public static boolean isChinese(String str) {
        return Pattern.matches("^[\u4E00-\u9FA5]+$", str.trim());
    }

    /**
     * @Description 判断字符串是否为纯字母
     * @Author taoye
     */
    public static boolean isEnglish(String str) {
        return str.matches("^[A-Za-z]+$");
    }

    /**
     * @Description 验证手机号
     * @Author taoye
     */
    public static boolean isPhoneNumber(String str) {
        return Pattern.matches("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(166))\\d{8}$", str.trim());
    }

    /**
     * @Description 验证身份证号
     * @Author taoye
     */
    public static boolean isIdCard(String str) {
        return Pattern.matches("(^\\d{18}$)|(^\\d{15}$)", str.trim());
    }

    /**
     * @Description 验证邮箱
     * @Author taoye
     */
    public static boolean isEmail(String str) {
        return Pattern.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", str.trim());
    }

    /**
     * @Description 验证域名（根据DNS规定）
     * @Author taoye
     */
    public static boolean isDomain(String str) {
        return Pattern.matches("^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$", str.trim());
    }

    /**
     * @Description 判断字符串是否为时间
     * @Author taoye
     */
    public static boolean isDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @Description 逗号分割字符串每个元素加引号
     * @Author taoye
     */
    public static String quotationMarks(String str) {
        StringBuilder sb = new StringBuilder();
        String[] arr = str.split(",");
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"");
            sb.append(arr[i]);
            sb.append("\"");
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * @Description 数字金额大写转换
     * @Author taoye
     */
    public static String moneyUppercase(String money) {
        StringBuilder sb = new StringBuilder();
        sb.append("整");
        String[] arr = money.split("\\.");
        int unitIndex = 0;
        int flag = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (flag != i) {
                unitIndex = 0;
                flag = i;
            }
            money = arr[i];
            for (int j = money.length() - 1; j >= 0; j--) {
                if (1 == i) {
                    if (unitIndex > 1) {
                        throw new RuntimeException("金额转大写获取单位超出范围");
                    }
                    sb.insert(0, UNIT_CENTS[unitIndex]);
                } else {
                    if (unitIndex > 8) {
                        throw new RuntimeException("金额转大写获取单位超出范围");
                    }
                    sb.insert(0, UNIT_YUAN[unitIndex]);
                }
                sb.insert(0, DIGIT_UPPERCASE[Integer.valueOf(String.valueOf(money.charAt(j)))]);
                unitIndex++;
            }
        }
        String moneyUppercase = sb.toString()
                .replace("零仟", "零")
                .replace("零佰", "零")
                .replace("零拾", "零")
                .replace("零万", "零")
                .replace("零角", "零")
                .replace("零分", "零");
        while (moneyUppercase.contains("零零")) {
            moneyUppercase = moneyUppercase.replace("零零", "零");
        }
        return moneyUppercase.replace("零元", "元").replace("零整", "整");
    }

    /**
     * @param length
     * @return
     * @Description 根据传入字符长度生成随机字符串
     * @Author zhanglifeng
     */
    public static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * @Description 根据长度获取随机数
     * @Author taoye
     */
    public static String getRandomIntByLength(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomIntByLength(6));
    }
}