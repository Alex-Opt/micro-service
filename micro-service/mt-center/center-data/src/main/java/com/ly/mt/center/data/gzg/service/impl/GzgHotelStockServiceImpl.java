package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgHotelStockMapper;
import com.ly.mt.center.data.gzg.service.GzgHotelStockService;
import com.ly.mt.center.data.gzg.entity.GzgHotelStock;
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
public class GzgHotelStockServiceImpl extends BaseServiceImpl implements GzgHotelStockService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgHotelStockServiceImpl.class);
    @Resource
    GzgHotelStockMapper mapper;

    /**
     * @Description 保存GzgHotelStock
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgHotelStock(JSONObject jsonObject) {
        try {
            GzgHotelStock gzgHotelStock = JSONObject.toJavaObject(jsonObject, GzgHotelStock.class);
            if (StringUtil.isEmpty(gzgHotelStock.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgHotelStock(gzgHotelStock);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelStockServiceImpl.insertGzgHotelStock出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgHotelStock
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgHotelStock(JSONObject jsonObject) {
        try {
            GzgHotelStock gzgHotelStock = JSONObject.toJavaObject(jsonObject, GzgHotelStock.class);
            if (StringUtil.isEmpty(gzgHotelStock.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgHotelStock(gzgHotelStock);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelStockServiceImpl.deleteGzgHotelStock出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgHotelStock
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgHotelStock(JSONObject jsonObject) {
        try {
            GzgHotelStock gzgHotelStock = JSONObject.toJavaObject(jsonObject, GzgHotelStock.class);
            if (StringUtil.isEmpty(gzgHotelStock.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgHotelStock(gzgHotelStock);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelStockServiceImpl.updateGzgHotelStockById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgHotelStock
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgHotelStock(JSONObject jsonObject) {
        try {
            GzgHotelStock gzgHotelStock = JSONObject.toJavaObject(jsonObject, GzgHotelStock.class);
            if (StringUtil.isEmpty(gzgHotelStock.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgHotelStock = mapper.getGzgHotelStock(gzgHotelStock);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgHotelStock);
        } catch (Exception e) {
            LOGGER.error("GzgHotelStockServiceImpl.getGzgHotelStock出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}