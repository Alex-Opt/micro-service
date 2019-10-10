package com.ly.mt.purchase.server.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.purchase.ShopPurchasesVO;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.purchase.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.purchase.server.shop.mapper.PurchaseMapper;
import com.ly.mt.purchase.server.shop.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 购买情况
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 22:11:11
 */
@Service
public class PurchaseServiceImpl extends BaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Resource
    private PurchaseMapper purchaseMapper;

    /**
     * B端 获取最新的购买情况
     *
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public JSONObject getNewestPurchasesInfo(String json) throws Exception {
        ShopPurchasesVO shopPurchasesVO = purchaseMapper.selectNewestPurchasesInfo();
        return JsonUtil.getSuccessJson(shopPurchasesVO);
    }
}
