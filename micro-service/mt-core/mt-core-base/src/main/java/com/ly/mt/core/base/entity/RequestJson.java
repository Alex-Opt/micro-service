package com.ly.mt.core.base.entity;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description 请求实体
 * @Author taoye
 */
@ApiModel
public class RequestJson {
    @ApiModelProperty(value = "接口名称", required = true)
    private String serverName;
    @ApiModelProperty(value = "方法名称", required = true)
    private String functionName;
    @ApiModelProperty("请求参数,具体参数请查看对应接口")
    private JSONObject jsonObject;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "{serverName=\"" + serverName + "\", functionName=\"" + functionName + "\", jsonObject=" + jsonObject + "}";
    }
}