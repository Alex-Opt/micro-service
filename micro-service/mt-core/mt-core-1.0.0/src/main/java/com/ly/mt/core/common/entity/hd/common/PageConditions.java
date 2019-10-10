package com.ly.mt.core.common.entity.hd.common;

import com.ly.mt.core.common.util.CamelCaseNameUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @description
 *
 * 分页条件查询对象
 *
 * @author panjingtian
 * @date 2019/6/13 10:10 AM
 */
@ApiModel("条件请求")
public  class PageConditions {

    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private Integer pageNum;


    /**
     * 每页显示的条数
     */
    @ApiModelProperty("当前页数据条数")
    private Integer pageSize;


    /**
     * 查询条件
     * key 为查询的字段名称，value为值
     */
    @ApiModelProperty("{'orderStatus':'FINISH完结，CANCEL取消,UNPAY待支付，OKPAY已支付，SEND已发货','phone':'订单手机号'}")
    private Map<String,Object> conditions;


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

    public Map<String, Object> getCamelCaseNameConditions() {
        /**
         * 转换成schema 的 _ 命名方式
         */
        return   CamelCaseNameUtil.toDbfield(conditions);
    }

    public Map<String, Object> getConditions() {
        /**
         * 普通命名
         */
        return  conditions;
    }
    public void setCamelCaseNameConditions(Map<String, Object> conditions) {
        this.conditions = conditions;
    }

    public PageConditions() {
    }
}
