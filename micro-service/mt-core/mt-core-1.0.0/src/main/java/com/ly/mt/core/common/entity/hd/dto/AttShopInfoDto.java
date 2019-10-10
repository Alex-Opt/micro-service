package com.ly.mt.core.common.entity.hd.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 带活动门店注册信息id的dto
 */
public class AttShopInfoDto extends ShopInfoDto {

    /**
     * 门店活动信息主键id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shopAttDetailId;

    public Long getShopAttDetailId() {
        return shopAttDetailId;
    }

    public void setShopAttDetailId(Long shopAttDetailId) {
        this.shopAttDetailId = shopAttDetailId;
    }
}
