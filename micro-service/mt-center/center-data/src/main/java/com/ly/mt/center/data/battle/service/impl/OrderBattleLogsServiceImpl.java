package com.ly.mt.center.data.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.battle.mapper.OrderBattleLogsMapper;
import com.ly.mt.center.data.battle.service.OrderBattleLogsService;
import com.ly.mt.center.data.battle.entity.OrderBattleLogs;
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
public class OrderBattleLogsServiceImpl extends BaseServiceImpl implements OrderBattleLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderBattleLogsServiceImpl.class);
    @Resource
    OrderBattleLogsMapper mapper;

    /**
     * @Description 保存OrderBattleLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertOrderBattleLogs(JSONObject jsonObject) {
        try {
            OrderBattleLogs orderBattleLogs = JSONObject.toJavaObject(jsonObject, OrderBattleLogs.class);
            if (StringUtil.isEmpty(orderBattleLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertOrderBattleLogs(orderBattleLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleLogsServiceImpl.insertOrderBattleLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除OrderBattleLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteOrderBattleLogs(JSONObject jsonObject) {
        try {
            OrderBattleLogs orderBattleLogs = JSONObject.toJavaObject(jsonObject, OrderBattleLogs.class);
            if (StringUtil.isEmpty(orderBattleLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteOrderBattleLogs(orderBattleLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleLogsServiceImpl.deleteOrderBattleLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新OrderBattleLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateOrderBattleLogs(JSONObject jsonObject) {
        try {
            OrderBattleLogs orderBattleLogs = JSONObject.toJavaObject(jsonObject, OrderBattleLogs.class);
            if (StringUtil.isEmpty(orderBattleLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateOrderBattleLogs(orderBattleLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleLogsServiceImpl.updateOrderBattleLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询OrderBattleLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getOrderBattleLogs(JSONObject jsonObject) {
        try {
            OrderBattleLogs orderBattleLogs = JSONObject.toJavaObject(jsonObject, OrderBattleLogs.class);
            if (StringUtil.isEmpty(orderBattleLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            orderBattleLogs = mapper.getOrderBattleLogs(orderBattleLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, orderBattleLogs);
        } catch (Exception e) {
            LOGGER.error("OrderBattleLogsServiceImpl.getOrderBattleLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}