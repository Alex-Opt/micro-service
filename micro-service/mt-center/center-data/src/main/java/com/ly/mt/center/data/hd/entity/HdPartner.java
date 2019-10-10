package com.ly.mt.center.data.hd.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合伙人信息
 * @author zhanglifeng
 * @date 2019-08-21
 */
@ApiModel
public class HdPartner {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("合伙人姓名")
    private String partner_name;
    @ApiModelProperty("合伙人电话")
    private String partner_mobile;
    @ApiModelProperty("合伙人代理的城市名称")
    private String agent_city_name;
    @ApiModelProperty("合伙人代理的城市编码")
    private String agent_city_code;
    @ApiModelProperty("数据来源")
    private String source;
    @ApiModelProperty("数据的渠道")
    private String channel;
    @ApiModelProperty("备用字段1")
    private String reserve1;
    @ApiModelProperty("备用字段1")
    private String reserve2;
    @ApiModelProperty("备用字段1")
    private String reserve3;
    @ApiModelProperty("创建时间")
    private String create_time;
    @ApiModelProperty("有效状态 0失效 1有效 3已经参与过活动")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_mobile() {
        return partner_mobile;
    }

    public void setPartner_mobile(String partner_mobile) {
        this.partner_mobile = partner_mobile;
    }

    public String getAgent_city_name() {
        return agent_city_name;
    }

    public void setAgent_city_name(String agent_city_name) {
        this.agent_city_name = agent_city_name;
    }

    public String getAgent_city_code() {
        return agent_city_code;
    }

    public void setAgent_city_code(String agent_city_code) {
        this.agent_city_code = agent_city_code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
