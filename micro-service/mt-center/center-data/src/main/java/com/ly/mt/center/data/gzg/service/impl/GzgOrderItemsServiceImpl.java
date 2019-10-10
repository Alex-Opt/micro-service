package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgOrderItem;
import com.ly.mt.center.data.gzg.mapper.GzgOrderItemsMapper;
import com.ly.mt.center.data.gzg.service.GzgOrderItemsService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgOrderItemsServiceImpl extends BaseServiceImpl implements GzgOrderItemsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgOrderItemsServiceImpl.class);
    @Resource
    GzgOrderItemsMapper mapper;

    /**
     * @Description 保存GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgOrderItems(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
            if (StringUtil.isEmpty(gzgOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgOrderItems(gzgOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.insertGzgOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgOrderItemsById(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
            if (StringUtil.isEmpty(gzgOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgOrderItemsById(gzgOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.deleteGzgOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgOrderItemsById(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
            if (StringUtil.isEmpty(gzgOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgOrderItemsById(gzgOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.updateGzgOrderItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrderItems(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
           /* if (StringUtil.isEmpty(gzgOrderItems.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }*/
            List<GzgOrderItem> gzgOrderItemsList = mapper.getGzgOrderItems(gzgOrderItems);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgOrderItemsList);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.getGzgOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrderItemsByOrderId(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
            List<GzgOrderItem> gzgOrderItems1 = mapper.getGzgOrderItems(gzgOrderItems);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,gzgOrderItems1);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.getGzgOrderItemsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GzgOrderItems
     * @Author taoye
     */
    @Override
    public ResponseJson getSellNumBySkuId(JSONObject jsonObject) {
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
            int sellNumBySkuId = mapper.getSellNumBySkuId(gzgOrderItems);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,sellNumBySkuId);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.getSellNumBySkuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 更新订单明细
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateGzgOrderItemsByOrderId(JSONObject jsonObject){
        try {
            GzgOrderItem gzgOrderItems = JSONObject.toJavaObject(jsonObject, GzgOrderItem.class);
              mapper.updateGzgOrderItemsByOrderId(gzgOrderItems);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderItemsServiceImpl.updateGzgOrderItems出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

}