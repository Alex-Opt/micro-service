package com.ly.mt.center.third.fn.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.third.fn.entity.FnOrderQuery;
import com.ly.mt.center.third.fn.entity.FnRequest;
import com.ly.mt.center.third.fn.entity.FnResult;
import com.ly.mt.center.third.fn.service.FnCarrierService;
import com.ly.mt.center.third.fn.service.FnTokenService;
import com.ly.mt.center.third.fn.util.FnRandomUtil;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.center.third.fn.dict.FnApi.FN_API_CARRIER_QUERY;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;


/**
 * @Description 蜂鸟骑手接口
 * @Author taoye
 */
@Service
public class FnCarrierServiceImpl extends BaseServiceImpl implements FnCarrierService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FnCarrierServiceImpl.class);
    @Resource
    FnTokenService tokenService;
    @Resource
    YmlConfig yml;

    /**
     * @Description 查询骑手
     * @Author taoye
     */
    @Override
    public ResponseJson carrierQuery(JSONObject jsonObject) {
        try {
            String partner_order_code = jsonObject.getString("orderNo");
            if (StringUtil.isEmpty(partner_order_code)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误—订单号为空");
            }
            // 订单信息封装
            FnOrderQuery orderQuery = new FnOrderQuery();
            orderQuery.setPartner_order_code(partner_order_code);
            // 获取签名
            int salt = FnRandomUtil.getInstance().generateValue(1000, 10000);
            String orderJson = JSONObject.toJSONString(orderQuery);
            String sign = tokenService.getBusinessSign(orderJson, salt);
            // 请求参数封装
            FnRequest FnRequest = new FnRequest();
            FnRequest.setApp_id(yml.getFnAppId());
            FnRequest.setSalt(String.valueOf(salt));
            FnRequest.setData(orderJson);
            FnRequest.setSignature(sign);
            String requestJson = JSONObject.toJSONString(FnRequest);
            String url = tokenService.getRequestUtl(FN_API_CARRIER_QUERY);
            String resultJson = restTemplatePost(url, requestJson);
            FnResult FnResult = JSONObject.parseObject(resultJson, FnResult.class);
            String successCode = "200";
            if (null == FnResult || !successCode.equals(FnResult.getCode())) {
                LOGGER.error("订单编号={}查询骑手位置失败", partner_order_code);
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}