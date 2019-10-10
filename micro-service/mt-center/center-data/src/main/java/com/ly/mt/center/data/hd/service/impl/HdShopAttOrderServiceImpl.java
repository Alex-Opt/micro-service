package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdShopAttOrderMapper;
import com.ly.mt.center.data.hd.service.HdShopAttOrderService;
import com.ly.mt.center.data.hd.entity.HdShopAttOrder;
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
public class HdShopAttOrderServiceImpl implements HdShopAttOrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdShopAttOrderServiceImpl.class);
    @Resource
    HdShopAttOrderMapper mapper;

    /**
     * @Description 保存HdShopAttOrder
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdShopAttOrder(JSONObject jsonObject) {
        try {
            HdShopAttOrder hdShopAttOrder = JSONObject.toJavaObject(jsonObject, HdShopAttOrder.class);
            if (StringUtil.isEmpty(hdShopAttOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdShopAttOrder(hdShopAttOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderServiceImpl.insertHdShopAttOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdShopAttOrder
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdShopAttOrder(JSONObject jsonObject) {
        try {
            HdShopAttOrder hdShopAttOrder = JSONObject.toJavaObject(jsonObject, HdShopAttOrder.class);
            if (StringUtil.isEmpty(hdShopAttOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdShopAttOrder(hdShopAttOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderServiceImpl.deleteHdShopAttOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdShopAttOrder
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdShopAttOrder(JSONObject jsonObject) {
        try {
            HdShopAttOrder hdShopAttOrder = JSONObject.toJavaObject(jsonObject, HdShopAttOrder.class);
            if (StringUtil.isEmpty(hdShopAttOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdShopAttOrder(hdShopAttOrder);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderServiceImpl.updateHdShopAttOrderById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdShopAttOrder
     * @Author taoye
     */
    @Override
    public ResponseJson getHdShopAttOrder(JSONObject jsonObject) {
        try {
            HdShopAttOrder hdShopAttOrder = JSONObject.toJavaObject(jsonObject, HdShopAttOrder.class);
            if (StringUtil.isEmpty(hdShopAttOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdShopAttOrder = mapper.getHdShopAttOrder(hdShopAttOrder);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdShopAttOrder);
        } catch (Exception e) {
            LOGGER.error("HdShopAttOrderServiceImpl.getHdShopAttOrder出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}