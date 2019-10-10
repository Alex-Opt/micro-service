package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.goods.entity.GoodsFront;
import com.ly.mt.center.data.goods.mapper.GoodsFrontMapper;
import com.ly.mt.center.data.goods.service.GoodsFrontService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GoodsFrontServiceImpl extends BaseServiceImpl implements GoodsFrontService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsFrontServiceImpl.class);
    @Resource
    GoodsFrontMapper mapper;

    /**
     * @Description 保存GoodsFront
     * @Author
     */
    @Override
    public ResponseJson insertGoodsFront(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsFront(goodsFront);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.insertGoodsFront出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description  修改GoodsFront
     * @Author
     */
    @Override
    public ResponseJson updateGoodsFront(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "修改条件不能为空");
            }
            mapper.updateGoodsFront(goodsFront);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.updateGoodsFront出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsFront spuId（去重）
     * @Author
     */
    @Override
    public ResponseJson getGoodsFrontSpuId(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getApp_type())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            List<String> spuIdList = mapper.getGoodsFrontSpuId(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,spuIdList);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsFrontSpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description  查询GoodsFront
     * @Author
     */
    @Override
    public ResponseJson getGoodsFront(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getApp_type())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            List<GoodsFront> goodsFrontList = mapper.getGoodsFront(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsFrontList);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsFront出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description  根据spuId查询goodsFront
     * @Author
     */
    @Override
    public ResponseJson getGoodsFrontBySpuId(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getSpu_id())||StringUtil.isEmpty(goodsFront.getApp_type())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            List<GoodsFront> goodsOfoList = mapper.getGoodsFrontBySpuId(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsOfoList);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsFrontBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description  根据spuId查询sku数据
     * @Author
     */
    @Override
    public ResponseJson getGoodsSkuBySpuId(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getSpu_id())||StringUtil.isEmpty(goodsFront.getApp_type())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            List<Map<String,String>> goodsFrontList = mapper.getGoodsSkuBySpuId(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsFrontList);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsSkuBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description  根据spuId查询sku数据
     * @Author
     */
    @Override
    public ResponseJson getGoodsSkuBySkuId(JSONObject jsonObject) {
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getSku_id())||StringUtil.isEmpty(goodsFront.getApp_type())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            Map<String,String> goodsFrontMap = mapper.getGoodsSkuBySkuId(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsFrontMap);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsSkuBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 根据APPType和actiClass 查询数据
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson queryGoodsFrontByActiClass(JSONObject jsonObject){
        try {
            GoodsFront goodsFront = JSONObject.toJavaObject(jsonObject, GoodsFront.class);
            if (StringUtil.isEmpty(goodsFront.getApp_type())||StringUtil.isEmpty(goodsFront.getActi_class())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsFront = mapper.queryGoodsFrontByActiClass(goodsFront);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsFront);
        } catch (Exception e) {
            LOGGER.error("GoodsFrontServiceImpl.getGoodsSkuBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}