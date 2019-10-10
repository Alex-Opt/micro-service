package com.ly.mt.core.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典枚举操作工具类
 *
 * @author taoye
 */
public class EnumUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(EnumUtil.class);
    private final static String ENUM_PACKAGE_PATH = "com.ly.mt.core.base.dict.";
    private final static String GET_ID = "getId";
    private final static String GET_NAME = "getName";

    /**
     * 根据字典枚举类名字获取字典枚举list集合
     *
     * @param dictName 字典枚举名称
     * @return 字典集合
     * @author taoye
     */
    public static List<Map<String, String>> listDictByDictName(String dictName) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Class<Enum> clazz = (Class<Enum>) Class.forName(ENUM_PACKAGE_PATH + dictName);
            Enum[] enums = clazz.getEnumConstants();
            for (Enum em : enums) {
                Map<String, String> map = new HashMap<>();
                for (Field field : em.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if ("java.lang.String".equals(field.getGenericType().getTypeName())) {
                        map.put(field.getName(), (String) field.get(em));
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            LOGGER.error("根据字典枚举类名字获取字典枚举list集合出错:", e);
        }
        return list;
    }

    /**
     * 根据字典枚举类名字和id获取对应name
     *
     * @param dictName 字典枚举名称
     * @param id       字典id
     * @return 字典name
     * @author taoye
     */
    public static String getNameById(String dictName, String id) {
        String name = null;
        try {
            Class<Enum> clazz = (Class<Enum>) Class.forName(ENUM_PACKAGE_PATH + dictName);
            name = getValueByKey(GET_ID, GET_NAME, clazz, id);
        } catch (ClassNotFoundException e) {
            LOGGER.error("根据字典名称和id获取name出错:", e);
        }
        return name;
    }

    private static String getValueByKey(String func1, String func2, Class<Enum> clazz, String key) {
        String value = null;
        if (StringUtil.isEmpty(key)) {
            return value;
        }
        try {
            Method m1 = clazz.getDeclaredMethod(func1);
            Method m2 = clazz.getDeclaredMethod(func2);
            Enum[] enums = clazz.getEnumConstants();
            for (Enum em : enums) {
                String eKey = (String) m1.invoke(em);
                if (key.equals(eKey)) {
                    value = (String) m2.invoke(em);
                }
            }
        } catch (Exception e) {
            LOGGER.error("枚举类func1={},func2={},key={},根据key获取value出错", func1, func2, key, e);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(getNameById("FuncLevel", "1"));
    }
}