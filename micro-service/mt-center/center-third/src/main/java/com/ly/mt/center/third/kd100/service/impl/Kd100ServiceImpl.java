package com.ly.mt.center.third.kd100.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.kd100.entity.Kd100DeliveryInfoQuery;
import com.ly.mt.center.third.kd100.entity.Kd100Result;
import com.ly.mt.center.third.kd100.service.Kd100Service;
import com.ly.mt.center.third.kd100.util.Kd100HttpUtils;
import com.ly.mt.center.third.kd100.util.Kd100Md5Util;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.base.dict.Kd100Com.SHUN_FENG;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 快递100服务
 *
 * @author zhanglifeng
 * @date 2019-06-26
 */
@Service
public class Kd100ServiceImpl extends BaseServiceImpl implements Kd100Service {

    private final static Logger LOGGER = LoggerFactory.getLogger(Kd100ServiceImpl.class);
    @Resource
    private YmlConfig yml;

    /**
     * 根据运单号查询对应快递公司码的物流追踪信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public ResponseJson getDeliveryInfo(JSONObject jsonObject) {
        Kd100DeliveryInfoQuery query = JSONObject.toJavaObject(jsonObject, Kd100DeliveryInfoQuery.class);
        String com = query.getCom();
        if (StringUtil.isEmpty(com)) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "快递一百入参：com-查询的快递公司的编码,一律用小写字母 不能为空");
        } else if (SHUN_FENG.getCom().equals(com)) {
            if (StringUtil.isEmpty(query.getPhone())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "快递一百入参：phone-寄件人或收件人手机号（顺丰单号必填） 不能为空");
            }
        }
        if (StringUtil.isEmpty(query.getNum())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "快递一百入参：num-查询的快递单号,单号的最大长度是32个字符 不能为空");
        }
        String customer = yml.getKd100Customer();
        String key = yml.getKd100Key();
        String queryJson = JSONObject.toJSONString(query);
        String sign = Kd100Md5Util.encode(queryJson + key + customer).toUpperCase();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("customer", customer));
        params.add(new BasicNameValuePair("sign", sign));
        params.add(new BasicNameValuePair("param", queryJson));
        String resultJson = Kd100HttpUtils.postQuery(params, yml.getKd100Uri());
        LOGGER.info("------------------------------------------调用快递一百返回的数据："+resultJson);
        if (StringUtil.isNotEmpty(resultJson)) {
            Kd100Result kd100Result = JSONObject.parseObject(resultJson, Kd100Result.class);
            String successCode = "200";
            if (successCode.equals(kd100Result.getStatus())) {
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, kd100Result.getData());
            }
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
    }
}