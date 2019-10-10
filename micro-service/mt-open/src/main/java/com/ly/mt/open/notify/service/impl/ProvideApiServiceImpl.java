package com.ly.mt.open.notify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.open.base.service.impl.BaseServiceImpl;
import com.ly.mt.open.notify.service.ProvideApiService;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.ORDER_LIST_BY_SOURCE;

@Service
public class ProvideApiServiceImpl extends BaseServiceImpl implements ProvideApiService {

    @Override
    public ResponseJson queryOrder(String startTime) {
        JSONObject json = new JSONObject();
        json.put("order_source","999999");
        json.put("create_time",startTime);
        String  orderResult = callDataCenter(ORDER_LIST_BY_SOURCE, json);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,orderResult);
    }
}
