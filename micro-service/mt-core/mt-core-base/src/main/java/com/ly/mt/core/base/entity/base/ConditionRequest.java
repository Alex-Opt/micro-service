package com.ly.mt.core.base.entity.base;

import com.ly.mt.core.base.util.CamelCaseNameUtil;

import java.util.Map;

/** @deprecated */
public class ConditionRequest {


    //页码
    private Integer pageNum;

    //页面条数
    private Integer pageSize;


    //复杂查询条件
    private Map<String ,Object> conditions;

    public ConditionRequest() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getConditions() {
        /**
         * 转换成schema 的 _ 命名方式
         */
      return   CamelCaseNameUtil.toDbfield(conditions);
    }

    public void setConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }
}
