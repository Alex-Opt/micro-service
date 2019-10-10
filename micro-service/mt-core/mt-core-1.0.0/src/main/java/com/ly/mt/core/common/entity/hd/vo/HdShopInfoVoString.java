package com.ly.mt.core.common.entity.hd.vo;

public class HdShopInfoVoString {
    private String id;

    private String shopName;
    private Long operatorId;

    public HdShopInfoVoString() {
    }

    public HdShopInfoVoString(String id, String shopName, Long operatorId) {
        this.id = id;
        this.shopName = shopName;
        this.operatorId = operatorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
