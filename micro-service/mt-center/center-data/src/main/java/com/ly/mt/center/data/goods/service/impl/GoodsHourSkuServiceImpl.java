package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.entity.GoodsHourSku;
import com.ly.mt.center.data.goods.mapper.GoodsHourSkuMapper;
import com.ly.mt.center.data.goods.service.GoodsHourSkuService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GoodsHourSkuServiceImpl  implements GoodsHourSkuService {

    //日志
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsHourSkuServiceImpl.class);
    @Resource
    GoodsHourSkuMapper mapper;

    @Override
    public ResponseJson getGoodsSkuHourInfoBySkuIds(JSONObject jsonObject) {
        try {
            List<String> skuIds = JSONObject.parseArray(jsonObject.getString("skuIds"), String.class);
            List<GoodsHourSku> goodsHourSkus = mapper.selectGoodsHourSkuList(skuIds);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsHourSkus);
        } catch (Exception e) {
            LOGGER.error("GoodsHourSkuServiceImpl.getGoodsSkuHourInfoBySkuIds:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsHourSkuBySkuId(JSONObject jsonObject) {
        try {
            GoodsHourSku goodsHourSku = JSONObject.toJavaObject(jsonObject,GoodsHourSku.class);
            GoodsHourSku goodsHourSku1 = mapper.getGoodsHourSku(goodsHourSku);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsHourSku1);
        } catch (Exception e) {
            LOGGER.error("GoodsHourSkuServiceImpl.getGoodsHourSkuBySkuId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
