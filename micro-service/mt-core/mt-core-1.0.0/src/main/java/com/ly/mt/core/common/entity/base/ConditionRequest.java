package com.ly.mt.core.common.entity.base;

import com.ly.mt.core.common.util.CamelCaseNameUtil;

import java.util.Map;

/**
 * @description 分页请求对象
 * @author panjingtian
 * @date 2019/6/13 2:42 PM
 */
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
