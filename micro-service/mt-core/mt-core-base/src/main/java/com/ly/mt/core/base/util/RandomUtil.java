package com.ly.mt.core.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
* @program: mt-blue-tooth
* @description: 随机数生成util
* @author: wanghongliang
* @create: 2019/8/27 14:44
**/
public class RandomUtil {
    private static final SimpleDateFormat dateFormatRepairID =new SimpleDateFormat("yyyyMMddHHmm");
    private static final SimpleDateFormat dateFormatReplenishMentID =new SimpleDateFormat("yyyyMMddHHmmss");


    private static final ThreadLocalRandom random=ThreadLocalRandom.current();
    private static final String repair_id_rule="888";//报修订单id规则 yyyyMMddHHmmss+888+4随机数

    //生成报修单编号-唯一
    public static String generateRepairID(){
        //TODO:时间戳+N为随机数流水号
        return dateFormatRepairID.format(new Date()) +repair_id_rule+ generateNumber(3);
    }

    //生成补货订单Id
    public static String generateReplenishMentId(){
        //TODO:时间戳+N为随机数流水号
        return dateFormatReplenishMentID.format(new Date()) + generateNumber(4);
    }
 
    //N为随机数流水号
    public static String generateNumber(final int num){
        StringBuffer sb=new StringBuffer();
        for (int i=1;i<=num;i++){
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       System.out.println(generateRepairID());
    }
}