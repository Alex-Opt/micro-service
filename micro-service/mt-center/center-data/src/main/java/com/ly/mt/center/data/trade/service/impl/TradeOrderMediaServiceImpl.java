package com.ly.mt.center.data.trade.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.trade.entity.TradeOrderMedia;
import com.ly.mt.center.data.trade.mapper.TradeOrderMediaMapper;
import com.ly.mt.center.data.trade.service.TradeOrderMediaService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class TradeOrderMediaServiceImpl implements TradeOrderMediaService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TradeOrderMediaServiceImpl.class);
    @Resource
    private TradeOrderMediaMapper mapper;

    @Override
    public ResponseJson insertTradeOrderMeida(JSONObject jsonObject) {
      try{
          TradeOrderMedia tradeOrderMedia  = JSONObject.toJavaObject(jsonObject, TradeOrderMedia.class);
          if (StringUtil.isEmpty(tradeOrderMedia.getId())) {
              return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
          }
          mapper.insertTradeOrderMedia(tradeOrderMedia);
          return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
      }catch (Exception e){
          LOGGER.error("TradeOrderMediaServiceImpl.insertTradeOrderMeida出错:", e);
          return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
      }
    }
}
