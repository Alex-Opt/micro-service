package com.ly.mt.center.data.cabinet.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 格子柜B端补货：格子柜补货单信息表
 *
 * @author zhanglifeng
 * @date 2019-08-26
 */
@ApiModel
public class CabinetReplenish {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "补货订单类型0:BD补货订单 1:店铺管理员/店铺员工补货订单", required = true)
    private String order_type;

    @ApiModelProperty(value = "订单完成时间", required = true)
    private String order_finish_time;

    @ApiModelProperty(value = "skuId", required = true)
    private String sku_id;

    @ApiModelProperty(value = "格子柜code", required = true)
    private String cabinet_code;

    @ApiModelProperty(value = "待补货的格子对应商品69码", required = true)
    private String six_nine_code;

    @ApiModelProperty(value = "商品所在格子的号码", required = true)
    private String cabinet_no;

    @ApiModelProperty(value = "格子柜类型 1:亿联，2：展柜，3：如金", required = true)
    private String cabinet_type;

    @ApiModelProperty(value = "订单id", required = true)
    private String order_id;

    @ApiModelProperty(value = "订单明细id", required = true)
    private String order_item_id;

    @ApiModelProperty(value = "店铺id", required = true)
    private String cabinet_store_id;

    @ApiModelProperty(value = "补货人员的用户id", required = true)
    private String user_id;

    @ApiModelProperty(value = "审核照片url", required = true)
    private String audit_picture_url;

    @ApiModelProperty(value = "本补货单完成奖励金额", required = true)
    private String reward_amount;

    @ApiModelProperty(value = "补货单状态(1:待抢补货单 3:锁定补货单 4：商品校验失败 5:商品校验成功 6：货柜校验失败 7:货柜校验成功 9:提交审核成功 11:审核通过 12:审核失败  13:奖励提现成功 14：补货超时 15:放弃补货)", required = true)
    private String status;

    @ApiModelProperty(value = "抢单时间", required = true)
    private String grab_time;

    @ApiModelProperty(value = "补货超时时间", required = true)
    private String replenishment_timeout_time;

    @ApiModelProperty(value = "商品校验时间", required = true)
    private String goods_check_time;

    @ApiModelProperty(value = "货柜校验时间", required = true)
    private String cabinet_check_time;

    @ApiModelProperty(value = "提交审核时间", required = true)
    private String submit_check_time;

    @ApiModelProperty(value = "审核通过或失败时间", required = true)
    private String check_pass_time;

    @ApiModelProperty(value = "奖励提现时间", required = true)
    private String cash_withdrawal_time;

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

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_finish_time() {
        return order_finish_time;
    }

    public void setOrder_finish_time(String order_finish_time) {
        this.order_finish_time = order_finish_time;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getCabinet_code() {
        return cabinet_code;
    }

    public void setCabinet_code(String cabinet_code) {
        this.cabinet_code = cabinet_code;
    }

    public String getSix_nine_code() {
        return six_nine_code;
    }

    public void setSix_nine_code(String six_nine_code) {
        this.six_nine_code = six_nine_code;
    }

    public String getCabinet_no() {
        return cabinet_no;
    }

    public void setCabinet_no(String cabinet_no) {
        this.cabinet_no = cabinet_no;
    }

    public String getCabinet_type() {
        return cabinet_type;
    }

    public void setCabinet_type(String cabinet_type) {
        this.cabinet_type = cabinet_type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getCabinet_store_id() {
        return cabinet_store_id;
    }

    public void setCabinet_store_id(String cabinet_store_id) {
        this.cabinet_store_id = cabinet_store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAudit_picture_url() {
        return audit_picture_url;
    }

    public void setAudit_picture_url(String audit_picture_url) {
        this.audit_picture_url = audit_picture_url;
    }

    public String getReward_amount() {
        return reward_amount;
    }

    public void setReward_amount(String reward_amount) {
        this.reward_amount = reward_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrab_time() {
        return grab_time;
    }

    public void setGrab_time(String grab_time) {
        this.grab_time = grab_time;
    }

    public String getReplenishment_timeout_time() {
        return replenishment_timeout_time;
    }

    public void setReplenishment_timeout_time(String replenishment_timeout_time) {
        this.replenishment_timeout_time = replenishment_timeout_time;
    }

    public String getGoods_check_time() {
        return goods_check_time;
    }

    public void setGoods_check_time(String goods_check_time) {
        this.goods_check_time = goods_check_time;
    }

    public String getCabinet_check_time() {
        return cabinet_check_time;
    }

    public void setCabinet_check_time(String cabinet_check_time) {
        this.cabinet_check_time = cabinet_check_time;
    }

    public String getSubmit_check_time() {
        return submit_check_time;
    }

    public void setSubmit_check_time(String submit_check_time) {
        this.submit_check_time = submit_check_time;
    }

    public String getCheck_pass_time() {
        return check_pass_time;
    }

    public void setCheck_pass_time(String check_pass_time) {
        this.check_pass_time = check_pass_time;
    }

    public String getCash_withdrawal_time() {
        return cash_withdrawal_time;
    }

    public void setCash_withdrawal_time(String cash_withdrawal_time) {
        this.cash_withdrawal_time = cash_withdrawal_time;
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
