package com.ly.mt.core.common.entity.shop;

/**
 * 身份信息Vo
 * @author wanglong
 */
public class IdCardVo {

    //店铺编号
    private String shopId;
    //身份证正面
    private String idcardFront;
    //身份证背面
    private String idcardBack;
    //真实姓名
    private String realName;
    //身份证号码
    private String idcard;
    //证件日期
    private String vaildType;
    //有效期开始日期
    private String vaildStartAt;
    //有效期结束日期
    private String vaildEndAt;
    //更新时间
    private String modifyTime;


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront;
    }

    public String getIdcardBack() {
        return idcardBack;
    }

    public void setIdcardBack(String idcardBack) {
        this.idcardBack = idcardBack;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getVaildType() {
        return vaildType;
    }

    public void setVaildType(String vaildType) {
        this.vaildType = vaildType;
    }

    public String getVaildStartAt() {
        return vaildStartAt;
    }

    public void setVaildStartAt(String vaildStartAt) {
        this.vaildStartAt = vaildStartAt;
    }

    public String getVaildEndAt() {
        return vaildEndAt;
    }

    public void setVaildEndAt(String vaildEndAt) {
        this.vaildEndAt = vaildEndAt;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
