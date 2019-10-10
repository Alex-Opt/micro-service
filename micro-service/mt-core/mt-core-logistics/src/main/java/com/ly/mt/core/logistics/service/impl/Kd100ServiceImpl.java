package com.ly.mt.core.logistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.logistics.config.Kd100Config;
import com.ly.mt.core.logistics.dict.Kd100Com;
import com.ly.mt.core.logistics.dict.Kd100State;
import com.ly.mt.core.logistics.entity.Kd100Request;
import com.ly.mt.core.logistics.entity.Kd100Result;
import com.ly.mt.core.logistics.entity.LogisticsInfo;
import com.ly.mt.core.logistics.entity.LogisticsNodeInfo;
import com.ly.mt.core.logistics.service.Kd100Service;
import com.ly.mt.core.logistics.util.Kd100HttpUtils;
import com.ly.mt.core.logistics.util.Kd100Md5Util;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.logistics.dict.Kd100Com.KD_COM_SF;


/**
 * 快递100服务接口
 *
 * @author taoye
 */
@Service
public class Kd100ServiceImpl implements Kd100Service {
    /**
     * 根据运单号查询对应快递公司码的物流追踪信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public LogisticsInfo getLogisticsInfo(String code, String no, String mobile) throws Exception {
        Assert.notNull(code, "Kd100ServiceImpl.listLogisticsInfo code must not be null");
        Assert.notNull(no, "Kd100ServiceImpl.listLogisticsInfo no must not be null");
        if (KD_COM_SF.getCom().equals(code)) {
            Assert.notNull(mobile, "Kd100ServiceImpl.listLogisticsInfo shunfeng mobile must not be null");
        }
        Kd100Request request = new Kd100Request(code, no, mobile);
        String queryJson = JSONObject.toJSONString(request);
        String customer = Kd100Config.CUSTOMER;
        String key = Kd100Config.KEY;
        String sign = Kd100Md5Util.encode(queryJson + key + customer).toUpperCase();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("customer", customer));
        params.add(new BasicNameValuePair("sign", sign));
        params.add(new BasicNameValuePair("param", queryJson));
        String kd100Resultjson = Kd100HttpUtils.postKd100(params, Kd100Config.uri);
        Assert.notNull(kd100Resultjson, "Kd100ServiceImpl.listLogisticsInfo postKd100 return null");
        Kd100Result kd100Result = JSONObject.parseObject(kd100Resultjson, Kd100Result.class);
        String successCode = "200";
        if (!successCode.equals(kd100Result.getStatus())) {
            throw new RuntimeException("Kd100ServiceImpl.listLogisticsInfo postKd100 return successCode is not 200");
        }
        String data = kd100Result.getData();
        Assert.notNull(data, "Kd100ServiceImpl.listLogisticsInfo postKd100 return data is null");
        List<LogisticsNodeInfo> logisticsNodeInfos = JSONObject.parseObject(data, new TypeReference<List<LogisticsNodeInfo>>() {
        });
        LogisticsInfo logisticsInfo = new LogisticsInfo();
        logisticsInfo.setLogisticsNodeInfos(logisticsNodeInfos);
        code = kd100Result.getCom();
        logisticsInfo.setLogisticsCode(code);
        logisticsInfo.setLogisticsName(Kd100Com.getCom(code));
        String state = kd100Result.getState();
        logisticsInfo.setLogisticsState(state);
        logisticsInfo.setLogisticsStateName(Kd100State.getNameById(state));
        logisticsInfo.setLogisticsNo(kd100Result.getNu());
        return logisticsInfo;
    }
}