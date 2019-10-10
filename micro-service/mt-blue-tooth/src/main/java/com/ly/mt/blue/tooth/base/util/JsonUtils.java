package com.ly.mt.blue.tooth.base.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Json字符串操作类
 * @author WHL
 *
 */
public class JsonUtils {
    private static final char UNDERLINE='_';

    /**
     * 将文件转成base64码
     * @param file
     * @return
     * @throws Exception
     */
    public static String getImageBase64EncodeStr(MultipartFile file) throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64EncoderImg = base64Encoder.encode(file.getBytes());
        String encoded = base64EncoderImg.replaceAll("[\\s*\t\n\r]", "");
        return encoded;
    }

    /**
     * JSON的驼峰和下划线互转帮助类
     * 将对象的大写转换为下划线加小写，例如：userName-->user_name
     * 
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toUnderlineJSONString(Object object) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);      
        String reqJson = mapper.writeValueAsString(object);
        return reqJson;
    }

    /**
     * 将下划线转换为驼峰的形式，例如：user_name-->userName
     * 
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T toSnakeObject(String json, Class<T> clazz) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        T reqJson =  mapper.readValue(json, clazz);
        return reqJson;
    }

    //转化长整数为十六进制编码
    public static String toHexInt(String s) {
        long l = Long.parseLong(s);
        return Long.toHexString(l);
    }

    /**
     * 8~20位，数字字母组合
     * 效验密码
     * @param
     * @return
     */
    public static Boolean validatePassword(String password){
        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(password);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }

    /**
     * 下划线 转 驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = Character.toLowerCase(param.charAt(i));
            if (c == UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}