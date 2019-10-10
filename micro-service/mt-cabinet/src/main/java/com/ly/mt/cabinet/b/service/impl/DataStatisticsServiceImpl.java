package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.DataStarusticsOrdersRequestBody;
import com.ly.mt.cabinet.b.common.request.DataStarusticsRequestBody;
import com.ly.mt.cabinet.b.common.response.*;
import com.ly.mt.cabinet.b.service.DataStatisticsService;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.feign.DataCenterMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataStatisticsServiceImpl extends BaseServiceImpl implements DataStatisticsService {

    @Autowired
    YmlConfig ymlConfig;

    @Override
    public List<ShopRespVo> findStores(TokenUserMessage user) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(user));
        String s = callDataCenter(DataCenterMethod.CABINET_FIND_BD_SHOPS, param);
        List<ShopRespVo> vos = JSONObject.parseObject(s, new TypeReference<List<ShopRespVo>>() {
        });
        //TODO 产品要插入个名曰"所有的数据放在顶头"
        List<ShopRespVo> newVos = new ArrayList<>(vos.size()+1);
        ShopRespVo shopRespVo = new ShopRespVo();
        shopRespVo.setId("");
        shopRespVo.setName("所有");
        newVos.add(shopRespVo);
        newVos.addAll(vos);
        return newVos;
    }

    @Override
    public BdDetailRespVo findBdDataDetail(DataStarusticsRequestBody body,TokenUserMessage user) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(user));
        param.put("start_time",body.getStart_time());
        param.put("end_time",body.getEnd_time());
        String s = callDataCenter(DataCenterMethod.CABINET_FIND_BD_DATA_DETAIL, param);
        BdDetailRespVo vo = JSONObject.toJavaObject(JSONObject.parseObject(s), BdDetailRespVo.class);
        return vo;

    }

    @Override
    public PageInfoResponseVo<BdNewDataStaticsticRespVo> findBdDataStatistics(DataStarusticsOrdersRequestBody body, TokenUserMessage user) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        param.put("userId",user.getUserId());
        param.put("phoneNo",user.getPhoneNo());
        param.put("stock_num",ymlConfig.getZgStockTotal());
        String s = callDataCenter(DataCenterMethod.CABINET_FIND_BD_DATA_STATISTICS, param);
        PageInfoResponseVo<BdNewDataStaticsticRespVo> vo = JSONObject.toJavaObject(JSONObject.parseObject(s), PageInfoResponseVo.class);
        return vo;
    }

    @Override
    public BdStoreOrdersRespVo<OrdersRespVo> findBdStoreOrders(DataStarusticsOrdersRequestBody body, TokenUserMessage user) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        param.put("userId",user.getUserId());
        param.put("phoneNo",user.getPhoneNo());
        String s = callDataCenter(DataCenterMethod.CABINET_FIND_BD_STORE_ORDERS, param);
        BdStoreOrdersRespVo<OrdersRespVo> vo = JSONObject.toJavaObject(JSONObject.parseObject(s), BdStoreOrdersRespVo.class);
        return vo;
    }

}
