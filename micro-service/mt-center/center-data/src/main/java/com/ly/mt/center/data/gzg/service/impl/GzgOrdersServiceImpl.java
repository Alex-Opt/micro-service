package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetBussinessCoorperation;
import com.ly.mt.center.data.gzg.entity.GzgOrder;
import com.ly.mt.center.data.gzg.mapper.GzgOrdersMapper;
import com.ly.mt.center.data.gzg.service.GzgOrdersService;
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
public class GzgOrdersServiceImpl extends BaseServiceImpl implements GzgOrdersService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgOrdersServiceImpl.class);
    @Resource
    GzgOrdersMapper mapper;

    /**
     * @Description 保存GzgOrders
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgOrders(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            if (StringUtil.isEmpty(gzgOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgOrders(gzgOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.insertGzgOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgOrders
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgOrders(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            if (StringUtil.isEmpty(gzgOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgOrders(gzgOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.deleteGzgOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgOrders
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgOrders(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            if (StringUtil.isEmpty(gzgOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgOrders(gzgOrders);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.updateGzgOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgOrders
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrders(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            if (StringUtil.isEmpty(gzgOrders.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
           // gzgOrders = mapper.getGzgOrders(gzgOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgOrders);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.getGzgOrders出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GzgOrders
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrdersById(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            GzgOrder gzgOrdersById = mapper.getGzgOrdersById(gzgOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgOrdersById);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.getGzgOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据userId查询GzgOrderList
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrdersList(JSONObject jsonObject) {
        try {
            GzgOrder gzgOrders = JSONObject.toJavaObject(jsonObject, GzgOrder.class);
            List<GzgOrder> gzgOrdersList = mapper.getGzgOrdersList(gzgOrders);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgOrdersList);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.getGzgOrdersById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据imei查询店家分成比例l
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrderDivideScale(JSONObject jsonObject) {
        try {
            String imei = jsonObject.getString("imei");
            CabinetBussinessCoorperation cabinetBussinessCoorperation = mapper.getGzgOrderDivideScale(imei);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetBussinessCoorperation);
        } catch (Exception e) {
            LOGGER.error("GzgOrdersServiceImpl.getGzgOrderDivideScale 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}