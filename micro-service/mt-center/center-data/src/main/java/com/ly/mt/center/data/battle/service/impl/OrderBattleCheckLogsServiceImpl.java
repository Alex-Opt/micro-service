package com.ly.mt.center.data.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.battle.mapper.OrderBattleCheckLogsMapper;
import com.ly.mt.center.data.battle.service.OrderBattleCheckLogsService;
import com.ly.mt.center.data.battle.entity.OrderBattleCheckLogs;
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
public class OrderBattleCheckLogsServiceImpl extends BaseServiceImpl implements OrderBattleCheckLogsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderBattleCheckLogsServiceImpl.class);
    @Resource
    OrderBattleCheckLogsMapper mapper;

    /**
     * @Description 保存OrderBattleCheckLogs
     * @Author taoye
     */
    @Override
    public ResponseJson insertOrderBattleCheckLogs(JSONObject jsonObject) {
        try {
            OrderBattleCheckLogs orderBattleCheckLogs = JSONObject.toJavaObject(jsonObject, OrderBattleCheckLogs.class);
            if (StringUtil.isEmpty(orderBattleCheckLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertOrderBattleCheckLogs(orderBattleCheckLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleCheckLogsServiceImpl.insertOrderBattleCheckLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除OrderBattleCheckLogs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteOrderBattleCheckLogs(JSONObject jsonObject) {
        try {
            OrderBattleCheckLogs orderBattleCheckLogs = JSONObject.toJavaObject(jsonObject, OrderBattleCheckLogs.class);
            if (StringUtil.isEmpty(orderBattleCheckLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteOrderBattleCheckLogs(orderBattleCheckLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleCheckLogsServiceImpl.deleteOrderBattleCheckLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新OrderBattleCheckLogs
     * @Author taoye
     */
    @Override
    public ResponseJson updateOrderBattleCheckLogs(JSONObject jsonObject) {
        try {
            OrderBattleCheckLogs orderBattleCheckLogs = JSONObject.toJavaObject(jsonObject, OrderBattleCheckLogs.class);
            if (StringUtil.isEmpty(orderBattleCheckLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateOrderBattleCheckLogs(orderBattleCheckLogs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrderBattleCheckLogsServiceImpl.updateOrderBattleCheckLogsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询OrderBattleCheckLogs
     * @Author taoye
     */
    @Override
    public ResponseJson getOrderBattleCheckLogs(JSONObject jsonObject) {
        try {
            OrderBattleCheckLogs orderBattleCheckLogs = JSONObject.toJavaObject(jsonObject, OrderBattleCheckLogs.class);
            if (StringUtil.isEmpty(orderBattleCheckLogs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            orderBattleCheckLogs = mapper.getOrderBattleCheckLogs(orderBattleCheckLogs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, orderBattleCheckLogs);
        } catch (Exception e) {
            LOGGER.error("OrderBattleCheckLogsServiceImpl.getOrderBattleCheckLogs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}