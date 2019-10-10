package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSpuAttrValueMapper;
import com.ly.mt.center.data.goods.service.GoodsSpuAttrValueService;
import com.ly.mt.center.data.goods.entity.GoodsSpuAttrValue;
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
public class GoodsSpuAttrValueServiceImpl extends BaseServiceImpl implements GoodsSpuAttrValueService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSpuAttrValueServiceImpl.class);
    @Resource
    GoodsSpuAttrValueMapper mapper;

    /**
     * @Description 插入GoodsSpuAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSpuAttrValue(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);
            mapper.insertGoodsSpuAttrValue(goodsSpuAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.insertGoodsSpuAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSpuAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSpuAttrValueById(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);
            mapper.deleteGoodsSpuAttrValueById(goodsSpuAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.deleteGoodsSpuAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSpuAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSpuAttrValueById(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);
            mapper.updateGoodsSpuAttrValueById(goodsSpuAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.updateGoodsSpuAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSpuAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuAttrValue(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);
            mapper.getGoodsSpuAttrValue(goodsSpuAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.getGoodsSpuAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSpuAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuAttrValueById(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);
            mapper.getGoodsSpuAttrValueById(goodsSpuAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.getGoodsSpuAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuAttrValueBySpuId(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);

            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSpuAttrValueBySpuId(goodsSpuAttrValue));
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.getGoodsSpuAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuAllAttrValueBySpuId(JSONObject jsonObject) {
        try {
            GoodsSpuAttrValue goodsSpuAttrValue = JSONObject.toJavaObject(jsonObject, GoodsSpuAttrValue.class);

            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSpuAllAttrValueBySpuId(goodsSpuAttrValue));
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrValueServiceImpl.getGoodsSpuAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}