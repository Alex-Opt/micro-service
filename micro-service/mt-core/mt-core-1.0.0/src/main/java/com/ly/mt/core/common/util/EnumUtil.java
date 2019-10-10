package com.ly.mt.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 字典枚举操作工具类
 * @Author taoye
 */
public class EnumUtil {
    private final static String ENUM_PACKAGE_PATH = "com.ly.mt.core.common.dict.";
    private final static String ENUM_NAME = "Enum";
    private final static Logger LOGGER = LoggerFactory.getLogger(EnumUtil.class);

    /**
     * @Description 根据字典枚举类名字获取字典枚举list集合
     * @Author taoye
     */
    public static List<Map<String, String>> getDictByType(String dictType) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Class<Enum> clazz = (Class<Enum>) Class.forName(ENUM_PACKAGE_PATH + dictType + ENUM_NAME);
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
}