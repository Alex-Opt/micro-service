package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSkuInfoMapper;
import com.ly.mt.center.data.goods.service.GoodsSkuInfoService;
import com.ly.mt.center.data.goods.entity.GoodsSkuInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GoodsSkuInfoServiceImpl extends BaseServiceImpl implements GoodsSkuInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSkuInfoServiceImpl.class);
    @Resource
    GoodsSkuInfoMapper mapper;

    /**
     * @Description 插入GoodsSkuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSkuInfo(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            mapper.insertGoodsSkuInfo(goodsSkuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.insertGoodsSkuInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSkuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSkuInfoById(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            mapper.deleteGoodsSkuInfoById(goodsSkuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.deleteGoodsSkuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSkuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSkuInfoById(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            mapper.updateGoodsSkuInfoById(goodsSkuInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.updateGoodsSkuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSkuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuInfo(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            mapper.getGoodsSkuInfo(goodsSkuInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsSkuInfo);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.getGoodsSkuInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSkuInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuInfoById(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,  mapper.getGoodsSkuInfoById(goodsSkuInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.getGoodsSkuInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSkuInfoBySpuIdAndAttr(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,  mapper.getGoodsSkuInfoBySpuIdAndAttr(goodsSkuInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.getGoodsSkuInfoBySpuIdAndAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSkuInfoBySpuId(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,  mapper.getGoodsSkuInfoBySpuId(goodsSkuInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.getGoodsSkuInfoBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSkuInfoBySpuIdNew(JSONObject jsonObject) {
        try {
            GoodsSkuInfo goodsSkuInfo = JSONObject.toJavaObject(jsonObject, GoodsSkuInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,  mapper.getGoodsSkuInfoBySpuId(goodsSkuInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuInfoServiceImpl.getGoodsSkuInfoBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}