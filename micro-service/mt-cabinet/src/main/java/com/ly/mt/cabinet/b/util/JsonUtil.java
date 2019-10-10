package com.ly.mt.cabinet.b.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil<T> {
    /**
     * 下划线转驼峰
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T underling2Hump(String json,Class<T> clazz){
        SerializeConfig serializeConfig =  new SerializeConfig();
        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.CamelCase;
        T t = JSON.parseObject(json,clazz);
        return t;
    }

    /**
     * 下划线转驼峰
     * @param json
     * @return
     */
    public String underling2Hump(String json){
        SerializeConfig serializeConfig = serializeConfig(PropertyNamingStrategy.CamelCase);
        return JSON.toJSONString(JSON.parseObject(json), serializeConfig);
    }

    /**
     * 驼峰转下划线
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T hump2Underline(String json,Class<T> clazz){
        SerializeConfig serializeConfig = serializeConfig(PropertyNamingStrategy.SnakeCase);
        return JSON.parseObject(JSON.toJSONString(JSON.parseObject(json),serializeConfig),new TypeReference<T>(){});
    }

    /**
     * 驼峰转下划线
     * @param json
     * @return
     */
    public String hump2Underline(String json){
        SerializeConfig serializeConfig = serializeConfig(PropertyNamingStrategy.SnakeCase);
        return JSON.toJSONString(JSON.parseObject(json),serializeConfig);
    }

    private SerializeConfig serializeConfig(PropertyNamingStrategy propertyNamingStrategy){
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.propertyNamingStrategy = propertyNamingStrategy;
        return serializeConfig;
    }

    public <T> T json2Bean(String json,Class<T> clazz){
        return JSON.parseObject(json,clazz);
    }

    public String bean2Json(Object bean){
        return JSON.toJSONString(bean);
    }
}
