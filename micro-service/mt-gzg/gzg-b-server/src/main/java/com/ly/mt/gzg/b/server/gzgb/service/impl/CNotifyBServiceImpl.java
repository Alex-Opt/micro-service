package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.entity.gzg.GzgReplenishOrder;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgReplenishOrderMapper;
import com.ly.mt.gzg.b.server.gzgb.service.CNotifyBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CNotifyBServiceImpl implements CNotifyBService {

    @Autowired
    private GzgReplenishOrderMapper gzgReplenishOrderMapper;

    @Override
    public JSONObject cNotify(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String orderNo = jsonObject.getString("orderNo");
        String orderItemNo = jsonObject.getString("orderItemNo");
        int status = jsonObject.getInteger("status");
        Date createTime = jsonObject.getDate("createTime");
        String hotelId = jsonObject.getString("hotelId");
        String gzgId = jsonObject.getString("gzgId");
        int cabinetNo = jsonObject.getInteger("cabinetNo");
        GzgReplenishOrder gzgReplenishOrder = new GzgReplenishOrder();
        gzgReplenishOrder.setId(IdUtil.getId(IdEnum.REPLENISH_ORDER));
        gzgReplenishOrder.setCreateTime(createTime);
        gzgReplenishOrder.setGzgOrderId(String.valueOf(orderNo));
        gzgReplenishOrder.setGzgOrderItmeId(String.valueOf(orderItemNo));
        gzgReplenishOrder.setState(status);
        gzgReplenishOrder.setGzgId(String.valueOf(gzgId));
        gzgReplenishOrder.setCabinetNo(String.valueOf(cabinetNo));
        gzgReplenishOrder.setHotelId(String.valueOf(hotelId));
        int i = gzgReplenishOrderMapper.insertSelective(gzgReplenishOrder);

        return JsonUtil.getSuccessJson();
    }
}
