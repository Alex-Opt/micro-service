package com.ly.mt.core.common.entity.hd.dto;


import com.ly.mt.core.common.entity.hd.HdShopAttOrderDetail;
import com.ly.mt.core.common.entity.hd.HdShopInfo;
import com.ly.mt.core.common.entity.hd.HdUserInfo;

import java.util.List;

/**
 * @description
 * 非支付 订单信息dto
 * @author panjingtian
 * @date 2019/6/14 10:53 AM
 */
public class NoPayOrderIngDto {


    /**
     * 用户信息信息
     */
    private HdUserInfo user;

    /**
     * 订单详情
     */
    private List<HdShopAttOrderDetail> details;

    /**
     * 商店信息
     */
    private HdShopInfo shopInfo;





}
