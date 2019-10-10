package com.ly.mt.core.base.entity.trade;
/**
 * 订单媒体信息表对应实体类
 *
 * @author zhanglifeng
 * @String 2019-09-16
 */

/**
 * @deprecated
 */
public class TradeOrderMedia {
    private String id;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 媒体活动页面类型
     */
    private String type;
    /**
     * '媒体素材
     */
    private String material;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 备用字段1
     */
    private String col1;
    /**
     * 备用字段2
     */
    private String col2;
    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
