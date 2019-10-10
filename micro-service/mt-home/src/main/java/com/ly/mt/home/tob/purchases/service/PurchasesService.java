package com.ly.mt.home.tob.purchases.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.home.tob.purchases.vo.ShopPurchasesVo;

import java.util.List;

/**
 * 进货单接口
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public interface PurchasesService {
    /**
     * 进货单分页
     *
     * @param rows 行数
     * @param page 页码
     * @return List<ShopPurchasesVo>
     */
    List<ShopPurchasesVo> queryPurchasesList(String rows, String page);

    /**
     * 进货单详情
     *
     * @param id 进货单id
     * @return ShopPurchasesVo
     */
    ShopPurchasesVo getPurchasesAndItem(String id);

    /**
     * 进货单详情
     *
     * @param id 进货单id
     * @return ShopPurchasesVo
     */
    ShopPurchasesVo getPurchases(String id, String shopId);

    /**
     * 更新进货单
     *
     * @param vo 进货单
     */
    void updatePurchase(ShopPurchasesVo vo);

    /**
     * 取消进货单
     *
     * @param id 进货单id
     */
    void cancelPurchases(String id);

    /**
     * 确认收货
     *
     * @param id 进货单id
     */
    void completePurchases(String id);

    /**
     * 获取运费
     *
     * @return JSONObject
     */
    JSONObject getPurchasesFreight();

    /**
     * 获取物流信息
     *
     * @param id 进货单id
     * @return ResponseJson
     */
    ResponseJson getPurchasesDeliveryInfo(String id);

    /**
     * 下单
     *
     * @param vo
     * @return JSONObject
     */
    JSONObject addPurchases(ShopPurchasesVo vo);

    /**
     * 检查订单
     *
     * @param id 进货单id
     * @return boolean
     */
    boolean checkPurchases(String id);

    /**
     * 检查订单状态
     *
     * @param id 进货单id
     * @return boolean
     */
    boolean checkPurchasesStatus(String id);
}
