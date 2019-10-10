package com.ly.mt.center.data.shop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShopGrade {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("等级名称")
    private String title;
    @ApiModelProperty("等级图标")
    private String icon;
    @ApiModelProperty("累计进货金额")
    private String purchases_money;
    @ApiModelProperty("累计进货数量（目前按这个计算）")
    private String purchases_num;
    @ApiModelProperty("优惠金额")
    private String promotion_money;
    @ApiModelProperty("优惠折扣（例：8折：0.8，75折：0.75）")
    private String promotion_rate;
    @ApiModelProperty("创建时间")
    private String create_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPurchases_money() {
        return purchases_money;
    }

    public void setPurchases_money(String purchases_money) {
        this.purchases_money = purchases_money;
    }

    public String getPurchases_num() {
        return purchases_num;
    }

    public void setPurchases_num(String purchases_num) {
        this.purchases_num = purchases_num;
    }

    public String getPromotion_money() {
        return promotion_money;
    }

    public void setPromotion_money(String promotion_money) {
        this.promotion_money = promotion_money;
    }

    public String getPromotion_rate() {
        return promotion_rate;
    }

    public void setPromotion_rate(String promotion_rate) {
        this.promotion_rate = promotion_rate;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

}