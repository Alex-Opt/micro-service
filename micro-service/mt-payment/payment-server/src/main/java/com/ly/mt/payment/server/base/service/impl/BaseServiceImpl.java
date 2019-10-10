package com.ly.mt.payment.server.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.ly.mt.core.base.entity.payment.PaymentOrderVo;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.mq.service.MqService;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.core.redis.service.RedisService;
import com.ly.mt.payment.server.base.config.PaymentServerYml;
import com.ly.mt.payment.server.base.service.BaseService;
import com.ly.mt.payment.server.payment.mapper.PaymentServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaseServiceImpl implements BaseService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);
    private static AlipayClient alipayClient;
    @Resource
    public RedisService redisServer;
    @Resource
    public RestTemplate restTemplate;
    @Resource
    public PaymentServerYml yml;
    @Resource
    public PaymentServiceMapper mapper;
    @Resource
    public MqService mqService;

    /**
     * @Description 阿里支付client获取
     * @Author taoye
     */
    @Override
    public AlipayClient getAlipayClient() {
        if (null == alipayClient) {
            alipayClient = new DefaultAlipayClient(
                    yml.getAlipayApiServer(), yml.getAlipayAppId(), yml.getAlipayKeyPrivate(),
                    yml.getAlipayFormat(), yml.getAlipayCharset(), yml.getAlipayKeyPublic(), yml.getAlipaySignType()
            );
        }
        LOGGER.info("===============阿里支付client获取==============alipayClient:" + alipayClient);
        return alipayClient;
    }

    /**
     * @Description 微信接口签名算法
     * @Author taoye
     */
    @Override
    public String getWeChatSign(TreeMap<String, Object> map, String merchantKey) throws Exception {
        StringBuffer sb = new StringBuffer();
        // 所有参与传参的参数按照accsii排序（升序）
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + merchantKey);
        return MD5Util.md5(sb.toString()).toUpperCase();
    }

    /**
     * @Description 获取系统订单
     * @Author taoye
     */
    @Override
    public PaymentOrderVo getH5PaymentOrder(RedisKey re, String orderNo) {
        String orderJson = redisServer.get(re, orderNo);
        if (StringUtil.isNotEmpty(orderJson)) {
            return JSON.parseObject(orderJson, PaymentOrderVo.class);
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("orderNo", orderNo);
        List<PaymentOrderVo> list = mapper.getOrderByOrderNo(map);
        if (null == list || list.size() <= 0) {
            LOGGER.error("未查到订单号={}的数据", orderNo);
            return null;
        }
        PaymentOrderVo vo = list.get(0);
        double totalMoney = 0.00;
        for (PaymentOrderVo order : list) {
            totalMoney += Double.valueOf(order.getOrderMoney());
        }
        vo.setOrderMoney(String.valueOf(totalMoney));
        return vo;
    }

/*    @Override
    public PaymentOrderVo getBDuanPaymentOrder(RedisEnum re, String orderNo) {
        String orderJson = redisServer.get(re, orderNo);
        if (StringUtil.isNotEmpty(orderJson)) {
            return JSON.parseObject(orderJson, PaymentOrderVo.class);
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("purchaseId", orderNo);
        PaymentOrderVo vo = mapper.getPurchaseOrderById(map);
        if(vo == null ){
            LOGGER.error("未查到订单号={}的数据", orderNo);
            return null;
        }
        return vo;
    }*/

    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String postExternalServer(String serverUrl, String param) {
        try {
            LOGGER.info("调用外部接口serverUrl={},param={}", serverUrl, param);
            return restTemplate.postForObject(serverUrl, param, String.class);
        } catch (Exception e) {
            LOGGER.error("调用外部接口serverUrl={},param={}出错:", serverUrl, param, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description 调用外部接口
     * @Author taoye
     */
    @Override
    public String getExternalServer(String serverUrl, Map<String, Object> map) {
        try {
            if (null != map) {
                StringBuilder sb = new StringBuilder();
                sb.append(serverUrl).append("?");
                map.forEach(
                        (k, v) -> sb.append(k).append("=").append(v).append("&")
                );
                sb.deleteCharAt(sb.length() - 1);
                serverUrl = sb.toString();
            }
            LOGGER.info("调用外部接口serverUrl={}", serverUrl);
            return restTemplate.getForObject(serverUrl, String.class);
        } catch (Exception e) {
            LOGGER.error("调用外部接口serverUrl={}出错:", serverUrl, e);
            throw new RuntimeException(e);
        }
    }

}