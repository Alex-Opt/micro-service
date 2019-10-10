package com.ly.mt.center.data.battle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.battle.mapper.OrdersBattleInfoMapper;
import com.ly.mt.center.data.battle.service.OrdersBattleInfoService;
import com.ly.mt.center.data.battle.entity.OrdersBattleInfo;
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
public class OrdersBattleInfoServiceImpl extends BaseServiceImpl implements OrdersBattleInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrdersBattleInfoServiceImpl.class);
    @Resource
    OrdersBattleInfoMapper mapper;

    /**
     * @Description 保存OrdersBattleInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertOrdersBattleInfo(JSONObject jsonObject) {
        try {
            OrdersBattleInfo ordersBattleInfo = JSONObject.toJavaObject(jsonObject, OrdersBattleInfo.class);
            if (StringUtil.isEmpty(ordersBattleInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertOrdersBattleInfo(ordersBattleInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrdersBattleInfoServiceImpl.insertOrdersBattleInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除OrdersBattleInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteOrdersBattleInfo(JSONObject jsonObject) {
        try {
            OrdersBattleInfo ordersBattleInfo = JSONObject.toJavaObject(jsonObject, OrdersBattleInfo.class);
            if (StringUtil.isEmpty(ordersBattleInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteOrdersBattleInfo(ordersBattleInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrdersBattleInfoServiceImpl.deleteOrdersBattleInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新OrdersBattleInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateOrdersBattleInfo(JSONObject jsonObject) {
        try {
            OrdersBattleInfo ordersBattleInfo = JSONObject.toJavaObject(jsonObject, OrdersBattleInfo.class);
            if (StringUtil.isEmpty(ordersBattleInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateOrdersBattleInfo(ordersBattleInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("OrdersBattleInfoServiceImpl.updateOrdersBattleInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询OrdersBattleInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getOrdersBattleInfo(JSONObject jsonObject) {
        try {
            OrdersBattleInfo ordersBattleInfo = JSONObject.toJavaObject(jsonObject, OrdersBattleInfo.class);
            if (StringUtil.isEmpty(ordersBattleInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            ordersBattleInfo = mapper.getOrdersBattleInfo(ordersBattleInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, ordersBattleInfo);
        } catch (Exception e) {
            LOGGER.error("OrdersBattleInfoServiceImpl.getOrdersBattleInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}