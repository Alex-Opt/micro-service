package com.ly.mt.center.data.activity.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ActivityInfo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("促销活动名称")
    private String name;
    @ApiModelProperty("促销活动有效期开始时间")
    private String start_time;
    @ApiModelProperty("促销活动有效期结束时间")
    private String end_time;
    @ApiModelProperty("促销优惠金额")
    private String denomination;
    @ApiModelProperty("促销优惠折扣（与促销活动金额是互斥的）")
    private String discount_rate;
    @ApiModelProperty("促销活动类型 1：直降，2：满减，3：秒杀")
    private String coupon_activity_type;
    @ApiModelProperty("促销活动使用渠道 1:全渠道;2:app;3:H5")
    private String use_channel;
    @ApiModelProperty("促销活动创建来源 1:平台，2:小B")
    private String source;
    @ApiModelProperty("促销优惠活动允许使用的最小订单金额（满减，直降才使用本字段）")
    private String start_fee;
    @ApiModelProperty("spu商品限制最大购买量（当限定商品分类为全品类时用此字段，否则用活动商品表中对应的字段）")
    private String max_sell_num;
    @ApiModelProperty("限定商品分类：-1-全品类 ，1-指定品类")
    private String goods_limit_category;
    @ApiModelProperty("促销活动状态 1-上架 ，2-下架")
    private String status;
    @ApiModelProperty("促销优惠活动说明")
    private String description;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getDiscount_rate() {
        return discount_rate;
    }

    public void setDiscount_rate(String discount_rate) {
        this.discount_rate = discount_rate;
    }

    public String getCoupon_activity_type() {
        return coupon_activity_type;
    }

    public void setCoupon_activity_type(String coupon_activity_type) {
        this.coupon_activity_type = coupon_activity_type;
    }

    public String getUse_channel() {
        return use_channel;
    }

    public void setUse_channel(String use_channel) {
        this.use_channel = use_channel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStart_fee() {
        return start_fee;
    }

    public void setStart_fee(String start_fee) {
        this.start_fee = start_fee;
    }

    public String getMax_sell_num() {
        return max_sell_num;
    }

    public void setMax_sell_num(String max_sell_num) {
        this.max_sell_num = max_sell_num;
    }

    public String getGoods_limit_category() {
        return goods_limit_category;
    }

    public void setGoods_limit_category(String goods_limit_category) {
        this.goods_limit_category = goods_limit_category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}