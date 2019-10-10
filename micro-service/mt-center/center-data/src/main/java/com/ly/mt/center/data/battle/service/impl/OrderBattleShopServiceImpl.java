package com.ly.mt.center.data.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.battle.mapper.OrderBattleShopMapper;
import com.ly.mt.center.data.battle.service.OrderBattleShopService;
import com.ly.mt.center.data.battle.entity.OrderBattleShop;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class OrderBattleShopServiceImpl extends BaseServiceImpl implements OrderBattleShopService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderBattleShopServiceImpl.class);
    @Resource
    OrderBattleShopMapper mapper;

    /**
     * @Description 保存OrderBattleShop
     * @Author taoye
     */
    @Override
    public ResponseJson insertOrderBattleShop(JSONObject jsonObject) {
        try {
            OrderBattleShop orderBattleShop = JSONObject.toJavaObject(jsonObject, OrderBattleShop.class);
            if (StringUtil.isEmpty(orderBattleShop.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertOrderBattleShop(orderBattleShop);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleShopServiceImpl.insertOrderBattleShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除OrderBattleShop
     * @Author taoye
     */
    @Override
    public ResponseJson deleteOrderBattleShop(JSONObject jsonObject) {
        try {
            OrderBattleShop orderBattleShop = JSONObject.toJavaObject(jsonObject, OrderBattleShop.class);
            if (StringUtil.isEmpty(orderBattleShop.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteOrderBattleShop(orderBattleShop);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleShopServiceImpl.deleteOrderBattleShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新OrderBattleShop
     * @Author taoye
     */
    @Override
    public ResponseJson updateOrderBattleShop(JSONObject jsonObject) {
        try {
            OrderBattleShop orderBattleShop = JSONObject.toJavaObject(jsonObject, OrderBattleShop.class);
            if (StringUtil.isEmpty(orderBattleShop.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateOrderBattleShop(orderBattleShop);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleShopServiceImpl.updateOrderBattleShopById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询OrderBattleShop
     * @Author taoye
     */
    @Override
    public ResponseJson getOrderBattleShop(JSONObject jsonObject) {
        try {
            OrderBattleShop orderBattleShop = JSONObject.toJavaObject(jsonObject, OrderBattleShop.class);
            if (StringUtil.isEmpty(orderBattleShop.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            orderBattleShop = mapper.getOrderBattleShop(orderBattleShop);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, orderBattleShop);
        } catch (Exception e) {
            LOGGER.error("OrderBattleShopServiceImpl.getOrderBattleShop出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}