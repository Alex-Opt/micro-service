package com.ly.mt.core.common.entity.marketing;

import com.ly.mt.core.common.entity.base.BaseEntity;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/22
 * @描述
 */
public class LodeRunnerCodes extends BaseEntity {
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 店铺编号
     */
    private Long shopId;

    /**
     * 专属邀请码
     */
    private String code;

    /**
     * 邀请成功次数
     */
    private Integer nums;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }
}
