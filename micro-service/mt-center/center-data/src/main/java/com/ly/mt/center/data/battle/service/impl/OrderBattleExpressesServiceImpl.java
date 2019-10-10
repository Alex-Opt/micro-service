package com.ly.mt.center.data.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.battle.mapper.OrderBattleExpressesMapper;
import com.ly.mt.center.data.battle.service.OrderBattleExpressesService;
import com.ly.mt.center.data.battle.entity.OrderBattleExpresses;
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
public class OrderBattleExpressesServiceImpl extends BaseServiceImpl implements OrderBattleExpressesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderBattleExpressesServiceImpl.class);
    @Resource
    OrderBattleExpressesMapper mapper;

    /**
     * @Description 保存OrderBattleExpresses
     * @Author taoye
     */
    @Override
    public ResponseJson insertOrderBattleExpresses(JSONObject jsonObject) {
        try {
            OrderBattleExpresses orderBattleExpresses = JSONObject.toJavaObject(jsonObject, OrderBattleExpresses.class);
            if (StringUtil.isEmpty(orderBattleExpresses.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertOrderBattleExpresses(orderBattleExpresses);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleExpressesServiceImpl.insertOrderBattleExpresses出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除OrderBattleExpresses
     * @Author taoye
     */
    @Override
    public ResponseJson deleteOrderBattleExpresses(JSONObject jsonObject) {
        try {
            OrderBattleExpresses orderBattleExpresses = JSONObject.toJavaObject(jsonObject, OrderBattleExpresses.class);
            if (StringUtil.isEmpty(orderBattleExpresses.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteOrderBattleExpresses(orderBattleExpresses);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleExpressesServiceImpl.deleteOrderBattleExpresses出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新OrderBattleExpresses
     * @Author taoye
     */
    @Override
    public ResponseJson updateOrderBattleExpresses(JSONObject jsonObject) {
        try {
            OrderBattleExpresses orderBattleExpresses = JSONObject.toJavaObject(jsonObject, OrderBattleExpresses.class);
            if (StringUtil.isEmpty(orderBattleExpresses.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateOrderBattleExpresses(orderBattleExpresses);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleExpressesServiceImpl.updateOrderBattleExpressesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询OrderBattleExpresses
     * @Author taoye
     */
    @Override
    public ResponseJson getOrderBattleExpresses(JSONObject jsonObject) {
        try {
            OrderBattleExpresses orderBattleExpresses = JSONObject.toJavaObject(jsonObject, OrderBattleExpresses.class);
            if (StringUtil.isEmpty(orderBattleExpresses.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            orderBattleExpresses = mapper.getOrderBattleExpresses(orderBattleExpresses);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, orderBattleExpresses);
        } catch (Exception e) {
            LOGGER.error("OrderBattleExpressesServiceImpl.getOrderBattleExpresses出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}