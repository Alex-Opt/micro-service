package com.ly.mt.core.base.util;

import java.util.HashMap;
import java.util.Map;


/**
 * @description
 * 驼峰命名法工具类
 * @author panjingtian
 * @date 2019/6/13 11:28 AM
 */
public class CamelCaseNameUtil {

    /**
     * 驼峰命名转表命名
     * @param source
     * @return
     */
    public static Map<String,Object> toDbfield(Map<String,Object> source){

        Map<String,Object> target = new HashMap<>(source.size());
        source.forEach((k,v)->{
            target.put(humpToUnderline(k),v);
        });
        return target;
    }

    /***
     * 下划线命名转为驼峰命名
     * @param para 下划线命名的字符串
     */

    public static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }



    /***
     * 驼峰命名转为下划线命名
     *
     * @param para 驼峰命名的字符串
     */

    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

}
