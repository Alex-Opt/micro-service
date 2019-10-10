package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSkuPriceMapper;
import com.ly.mt.center.data.goods.service.GoodsSkuPriceService;
import com.ly.mt.center.data.goods.entity.GoodsSkuPrice;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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
public class GoodsSkuPriceServiceImpl extends BaseServiceImpl implements GoodsSkuPriceService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSkuPriceServiceImpl.class);
    @Resource
    GoodsSkuPriceMapper mapper;

    /**
     * @Description 保存GoodsSkuPrice
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSkuPrice(JSONObject jsonObject) {
        try {
            GoodsSkuPrice goodsSkuPrice = JSONObject.toJavaObject(jsonObject, GoodsSkuPrice.class);
            if (StringUtil.isEmpty(goodsSkuPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsSkuPrice(goodsSkuPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPriceServiceImpl.insertGoodsSkuPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsSkuPrice
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSkuPrice(JSONObject jsonObject) {
        try {
            GoodsSkuPrice goodsSkuPrice = JSONObject.toJavaObject(jsonObject, GoodsSkuPrice.class);
            if (StringUtil.isEmpty(goodsSkuPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsSkuPrice(goodsSkuPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPriceServiceImpl.deleteGoodsSkuPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsSkuPrice
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSkuPrice(JSONObject jsonObject) {
        try {
            GoodsSkuPrice goodsSkuPrice = JSONObject.toJavaObject(jsonObject, GoodsSkuPrice.class);
            if (StringUtil.isEmpty(goodsSkuPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsSkuPrice(goodsSkuPrice);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPriceServiceImpl.updateGoodsSkuPriceById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsSkuPrice
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuPrice(JSONObject jsonObject) {
        try {
            GoodsSkuPrice goodsSkuPrice = JSONObject.toJavaObject(jsonObject, GoodsSkuPrice.class);
            if (StringUtil.isEmpty(goodsSkuPrice.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsSkuPrice = mapper.getGoodsSkuPrice(goodsSkuPrice);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsSkuPrice);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPriceServiceImpl.getGoodsSkuPrice出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}