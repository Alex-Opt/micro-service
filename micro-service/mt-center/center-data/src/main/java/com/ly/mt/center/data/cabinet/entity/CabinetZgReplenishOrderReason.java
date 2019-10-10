package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：展柜订单补货理由表
 *
 * @author wanghongliang
 * @date 2019-09-18
 */
public class CabinetZgReplenishOrderReason {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "对应展柜补货订单id", required = true)
    private String zg_replenish_order_id;

    @ApiModelProperty(value = "货柜编号", required = true)
    private String cabinet_code;

    @ApiModelProperty(value = "补货理由 补货理由 0:TOP3商品售罄（运营给）1:套装售罄 2:该展柜库存剩余小于7", required = true)
    private String replenishment_reason;

    @ApiModelProperty(value = "状态 0：待补货  1：已完成", required = true)
    private String status;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "修改时间", required = true)
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZg_replenish_order_id() {
        return zg_replenish_order_id;
    }

    public void setZg_replenish_order_id(String zg_replenish_order_id) {
        this.zg_replenish_order_id = zg_replenish_order_id;
    }

    public String getCabinet_code() {
        return cabinet_code;
    }

    public void setCabinet_code(String cabinet_code) {
        this.cabinet_code = cabinet_code;
    }

    public String getReplenishment_reason() {
        return replenishment_reason;
    }

    public void setReplenishment_reason(String replenishment_reason) {
        this.replenishment_reason = replenishment_reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
