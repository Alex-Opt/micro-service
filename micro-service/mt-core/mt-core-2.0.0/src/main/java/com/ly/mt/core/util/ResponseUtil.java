package com.ly.mt.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ly.mt.core.entity.ResponseCode;
import com.ly.mt.core.entity.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description 响应实体工具类
 * @Author taoye
 */
public class ResponseUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    /**
     * @Description 根据code生成响应实体
     * @Author taoye
     */
    public static ResponseJson getResponseCode(ResponseCode responseCode) {
        return getResponseJson(responseCode, null, null);
    }

    /**
     * @Description 根据code、msg生成响应实体
     * @Author taoye
     */
    public static ResponseJson getResponseMsg(ResponseCode responseCode, String msg) {
        return getResponseJson(responseCode, msg, null);
    }

    /**
     * @Description 根据code、obj生成响应实体
     * @Author taoye
     */
    public static ResponseJson getResponseObj(ResponseCode responseCode, Object obj) {
        return getResponseJson(responseCode, null, obj);
    }

    /**
     * @Description 根据code、msg、obj生成响应实体
     * @Author taoye
     */
    public static ResponseJson getResponseJson(ResponseCode responseCode, String msg, Object obj) {
        ResponseJson responseJson = new ResponseJson();
        responseJson.setCode(responseCode.getCode());
        responseJson.setMsg(StringUtil.isNotEmpty(msg) ? msg : responseCode.getMsg());
        return getResponseJson(responseJson, obj);
    }

    private static ResponseJson getResponseJson(ResponseJson responseJson, Object obj) {
        if (null == obj) {
            return responseJson;
        }
        if (obj instanceof String) {
            String json = (String) obj;
            if (json.startsWith("{")) {
                responseJson.setResult(JSONObject.parseObject(json).toJSONString());
            } else if (json.startsWith("[")) {
                responseJson.setResult(JSONArray.parseArray(json).toJSONString());
            } else {
                responseJson.setResult(json);
            }
        } else {
          //responseJson.setResult(JSONObject.toJSONString(obj));//JSONObject.toJSONString(obj) 默认忽略值为null的属性 因此修改为下面这种方式
            responseJson.setResult(JSONObject.toJSONString(obj, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty));
        }
        return responseJson;
    }

    /**
     * @Description 输出json
     * @Author taoye
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
}