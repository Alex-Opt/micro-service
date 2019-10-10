package com.ly.mt.center.data.goods.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhanglifeng
 * @date 2019-09-16
 */
@ApiModel
public class GoodsSpuConfiguration {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "面向功能类型:1-到家C实名认证后的商品展示页", required = true)
    private String system_user_type;

    @ApiModelProperty(value = "spu_id", required = true)
    private String spu_id;

    @ApiModelProperty(value = "使用状态 1、正常，2、停用", required = true)
    private String status;

    @ApiModelProperty(value = "用途备注信息", required = true)
    private String remark;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "更新时间", required = true)
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystem_user_type() {
        return system_user_type;
    }

    public void setSystem_user_type(String system_user_type) {
        this.system_user_type = system_user_type;
    }

    public String getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(String spu_id) {
        this.spu_id = spu_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
