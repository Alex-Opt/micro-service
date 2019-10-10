package com.ly.mt.core.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.entity.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_OPERATION_SUCCESS;

/** @deprecated */
public class JsonUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * @Description 获取操作成功json
     * @Author taoye
     */
    public static JSONObject getSuccessJson() {
        return getResultJson(RESULT_CODE_OPERATION_SUCCESS, null);
    }

    public static JSONObject getSuccessJson(Object obj) {
        return getResultJson(RESULT_CODE_OPERATION_SUCCESS, obj);
    }

    /**
     * @Description 获取错误返回json
     * @Author taoye
     */
    public static JSONObject getErrorJson(ResponseCode resultCodeEnum) {
        return getResultJson(resultCodeEnum, null);
    }

    /**
     * 获取错误返回json
     * 返回数据
     * @param resultCodeEnum
     * @param obj
     * @return
     */
    public static JSONObject getErrorJson(ResponseCode resultCodeEnum,Object obj) {
        return getResultJson(resultCodeEnum, obj);
    }

    /**
     * @Description 获取json
     * @Author taoye
     */
    private static JSONObject getResultJson(ResponseCode resultCodeEnum, Object obj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", resultCodeEnum.getCode());
        jsonObject.put("msg", resultCodeEnum.getMsg());
        if (obj instanceof String) {
            String jsonrst = (String) obj;
            JSON json;
            if (jsonrst.startsWith("{")) {
                json = JSONObject.parseObject(jsonrst);
                jsonObject.put("result", json);
            } else if (jsonrst.startsWith("[")) {
                json = JSONArray.parseArray(jsonrst);
                jsonObject.put("result", json);
            } else {
                jsonObject.put("result", jsonrst);
            }
        } else {
            jsonObject.put("result", obj);
        }
        return jsonObject;
    }

    /**
     * @Description 获取request请求参数
     * @Author taoye
     */
    public static JSONObject getJSONObject(HttpServletRequest request) {
        //请求参数
        String parameters = "";
        if ("GET".equals(request.getMethod())) {
            //GET请求时的参数 网址中的参数
            parameters = request.getQueryString();
        } else if ("POST".equals(request.getMethod())) {
            //POST请求时的参数 表单及网址中全部参数
            String totalParameter = "";
            Map<String, String[]> params = request.getParameterMap();
            //参数个数
            int parametersNum = request.getParameterMap().size();
            int flag = 1;
            for (String key : params.keySet()) {
                String[] values = params.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    totalParameter += key + "=" + value;
                }
                if (flag < parametersNum) {
                    totalParameter += "&";
                }
                flag += 1;
            }
            parameters = totalParameter;
        }
        JSONObject jsonObject = new JSONObject();
        if (StringUtil.isNotEmpty(parameters)) {
            String[] parameterArr = parameters.split("&");
            for (int i = 0; i < parameterArr.length; i++) {
                String[] paramArr = parameterArr[i].split("=");
                String paramValue = "";
                if (paramArr.length > 1) {
                    String value = paramArr[1];
                    if (StringUtil.isNotEmpty(value)) {
                        try {
                            // 去除前后空格
                            paramValue = URLDecoder.decode(value, "UTF-8").trim();
                        } catch (Exception e) {
                            LOGGER.error("request参数key={},value={}转json出错:", paramArr[0], value, e);
                        }
                    }
                }
                jsonObject.put(paramArr[0], paramValue);
            }
        }
        return jsonObject;
    }

    /**
     * 解析并判断是否存在必填字段
     *
     * @param tClass
     * @param params        json入参
     * @param requireFileds 必填字段
     * @param <T>
     * @return
     */
    public static <T> JSONObject parseAndRequire(Class<T> tClass, String params, String... requireFileds) throws Exception {
        T t = null;
        try {
            t = JSON.parseObject(params, tClass);
        } catch (Exception e) {
            throw new Exception("参数类型转换异常:" + e.getMessage());
        }
        //判断必填项是否已填
        if (requireFileds != null && requireFileds.length > 0) {
            Field[] fields = tClass.getDeclaredFields();
            int i = 0, j = 0;
            while (i < fields.length && j < requireFileds.length) {
                if (fields[i].getName().equals(requireFileds[j])) {
                    fields[i].setAccessible(true);
                    try {
                        if (fields[i].get(t) == null || "".equals(fields[i].get(t))) {
                            throw new Exception("必填项:" + requireFileds[j] + "对应的参数为空");
                        } else {
                            j++;
                            i = 0;
                        }
                    } catch (IllegalAccessException e) {
                        throw new Exception("判断必填项异常");
                    }
                } else {
                    i++;
                }
                if (i == fields.length) {
                    throw new Exception("必填项:" + requireFileds[j] + "对应的参数为空");
                }
            }
        }
        return JSONObject.parseObject(JSON.toJSONString(t));
    }

    /**
     * @Description 返回提示信息
     * @Author taoye
     * @Date 2018/9/28
     */
    public static void outPrint(HttpServletResponse response, String json) {
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.print(json);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            LOGGER.error("JsonUtil返回json异常:", e);
        }
    }

    public static Map<String,Object> json2Map(String json){
        return JSON.parseObject(json,new TypeReference<Map<String,Object>>(){});
    }
}