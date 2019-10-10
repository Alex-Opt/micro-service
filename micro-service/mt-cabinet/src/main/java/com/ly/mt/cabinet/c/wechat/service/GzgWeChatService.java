package com.ly.mt.cabinet.c.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.c.order.entity.GzgOrder;
import com.ly.mt.cabinet.c.wechat.entity.WeChatPayReqVO;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.Map;

public interface GzgWeChatService {
    /**
     * 微信浏览器外H5支付
     * @param
     * @return
     */
    ResponseJson weChatPayByH5(String billCreateIp,String gzgOrderId) throws Exception;
    /**
     * 微信浏览器内H5支付
     * @param
     * @return
     */
    ResponseJson weChatPayByJsApi(WeChatPayReqVO weChatPayReqVO ,String billCreateIp) throws Exception;

//    JSONObject getOpenIdByCode(String jsonStr) ;


    boolean orderQuery(long orderNo);


    ResponseJson weChatNotify(Map map) throws Exception;


    ResponseJson weChatPayDetail(String gzgOrderId) throws Exception;

  //  void test(String  id);

}
