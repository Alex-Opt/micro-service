package com.ly.mt.cabinet.b.common.bo;

import java.util.HashMap;
import java.util.Map;

public class SysLogBO {
    private String className;
    private String methodName;
    private Map<String,Object> params;
    private String createDate;
    private String executeTime;
    private String remark;

    public SysLogBO(){
        this.params = new HashMap<>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public String getClassName() {
        return className;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getRemark() {
        return remark;
    }
}
