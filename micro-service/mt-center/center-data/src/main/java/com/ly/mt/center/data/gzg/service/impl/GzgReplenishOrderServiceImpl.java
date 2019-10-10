package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgReplenishOrderMapper;
import com.ly.mt.center.data.gzg.service.GzgReplenishOrderService;
import com.ly.mt.center.data.gzg.entity.GzgReplenishOrder;
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
public class GzgReplenishOrderServiceImpl extends BaseServiceImpl implements GzgReplenishOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgReplenishOrderServiceImpl.class);
    @Resource
    GzgReplenishOrderMapper mapper;

    /**
     * @Description 保存GzgReplenishOrder
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgReplenishOrder(JSONObject jsonObject) {
        try {
            GzgReplenishOrder gzgReplenishOrder = JSONObject.toJavaObject(jsonObject, GzgReplenishOrder.class);
            if (StringUtil.isEmpty(gzgReplenishOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgReplenishOrder(gzgReplenishOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishOrderServiceImpl.insertGzgReplenishOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgReplenishOrder
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgReplenishOrder(JSONObject jsonObject) {
        try {
            GzgReplenishOrder gzgReplenishOrder = JSONObject.toJavaObject(jsonObject, GzgReplenishOrder.class);
            if (StringUtil.isEmpty(gzgReplenishOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgReplenishOrder(gzgReplenishOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishOrderServiceImpl.deleteGzgReplenishOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgReplenishOrder
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgReplenishOrder(JSONObject jsonObject) {
        try {
            GzgReplenishOrder gzgReplenishOrder = JSONObject.toJavaObject(jsonObject, GzgReplenishOrder.class);
            if (StringUtil.isEmpty(gzgReplenishOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgReplenishOrder(gzgReplenishOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishOrderServiceImpl.updateGzgReplenishOrderById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgReplenishOrder
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgReplenishOrder(JSONObject jsonObject) {
        try {
            GzgReplenishOrder gzgReplenishOrder = JSONObject.toJavaObject(jsonObject, GzgReplenishOrder.class);
            if (StringUtil.isEmpty(gzgReplenishOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgReplenishOrder = mapper.getGzgReplenishOrder(gzgReplenishOrder);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgReplenishOrder);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishOrderServiceImpl.getGzgReplenishOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}