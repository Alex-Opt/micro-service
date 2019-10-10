package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.bo.CabinetReplenishListBo;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenish;
import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrder;
import com.ly.mt.center.data.cabinet.mapper.CabinetReplenishMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetZgReplenishOrderMapper;
import com.ly.mt.center.data.cabinet.service.CabinetZgReplenishOrderService;
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

/**
 * @description:
 * @author: wanghongliang
 * @create: 2019-09-18 14:35
 **/
@Service
public class CabinetZgReplenishOrderServiceImpl implements CabinetZgReplenishOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetZgReplenishOrderServiceImpl.class);
    @Resource
    private CabinetZgReplenishOrderMapper cabinetZgReplenishOrderMapper;

    /**
     * 插入一条展柜补货单
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson insertCabinetZgReplenish(JSONObject jsonObject) {
        try {
            CabinetZgReplenishOrder cabinetZgReplenishOrder = JSONObject.toJavaObject(jsonObject, CabinetZgReplenishOrder.class);
            if (StringUtil.isEmpty(cabinetZgReplenishOrder.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            int i = cabinetZgReplenishOrderMapper.insert(cabinetZgReplenishOrder);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("CabinetZgReplenishOrderServiceImpl.insertCabinetZgReplenish:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * 查询展柜补货列表
     * @param
     * @return
     */
    @Override
    public ResponseJson getCabinetZgReplenishOrderList(JSONObject jsonObject) {
        try {
            CabinetZgReplenishOrder cabinetZgReplenishOrder = JSONObject.toJavaObject(jsonObject, CabinetZgReplenishOrder.class);
            List<CabinetZgReplenishOrder> cabinetReplenishList = cabinetZgReplenishOrderMapper.getCabinetZgReplenishByCondtion(cabinetZgReplenishOrder);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetReplenishList);
        } catch (Exception e) {
            LOGGER.error("CabinetZgReplenishOrderServiceImpl.getCabinetZgReplenishOrderList:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * 通过补货订单id更新
     * @param
     * @return
     */
    @Override
    public ResponseJson updateCabinetZgReplenishOrderById(JSONObject jsonObject) {
        try {
            CabinetZgReplenishOrder cabinetZgReplenishOrder = JSONObject.toJavaObject(jsonObject,CabinetZgReplenishOrder.class);
            int  i = cabinetZgReplenishOrderMapper.updateCabinetZgReplenishById(cabinetZgReplenishOrder);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,i);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishServiceImpl.getGoodsNameInfo:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}