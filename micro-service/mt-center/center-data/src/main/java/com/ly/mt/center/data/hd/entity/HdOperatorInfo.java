package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HdOperatorInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("代理商唯一编码，重复的话尾号加区号")
    private String operator_code;
    @ApiModelProperty("代理商国家id(主键)")
    private String operator_country_id;
    @ApiModelProperty("省主键id")
    private String operator_province_id;
    @ApiModelProperty("市主键id")
    private String operator_city_id;
    @ApiModelProperty("代理商名称")
    private String operator_name;
    @ApiModelProperty("代理商备注信息")
    private String operator_msg;
    @ApiModelProperty("代理商电话")
    private String operator_phone;
    @ApiModelProperty("代理商负责人")
    private String operator_person;
    @ApiModelProperty("代理商状态（0失效 1有效）")
    private String operator_status;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("更新时间")
    private String update_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperator_code() {
        return operator_code;
    }

    public void setOperator_code(String operator_code) {
        this.operator_code = operator_code;
    }

    public String getOperator_country_id() {
        return operator_country_id;
    }

    public void setOperator_country_id(String operator_country_id) {
        this.operator_country_id = operator_country_id;
    }

    public String getOperator_province_id() {
        return operator_province_id;
    }

    public void setOperator_province_id(String operator_province_id) {
        this.operator_province_id = operator_province_id;
    }

    public String getOperator_city_id() {
        return operator_city_id;
    }

    public void setOperator_city_id(String operator_city_id) {
        this.operator_city_id = operator_city_id;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public String getOperator_msg() {
        return operator_msg;
    }

    public void setOperator_msg(String operator_msg) {
        this.operator_msg = operator_msg;
    }

    public String getOperator_phone() {
        return operator_phone;
    }

    public void setOperator_phone(String operator_phone) {
        this.operator_phone = operator_phone;
    }

    public String getOperator_person() {
        return operator_person;
    }

    public void setOperator_person(String operator_person) {
        this.operator_person = operator_person;
    }

    public String getOperator_status() {
        return operator_status;
    }

    public void setOperator_status(String operator_status) {
        this.operator_status = operator_status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

}