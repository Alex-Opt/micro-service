package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSpuAttrMapper;
import com.ly.mt.center.data.goods.service.GoodsSpuAttrService;
import com.ly.mt.center.data.goods.entity.GoodsSpuAttr;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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
public class GoodsSpuAttrServiceImpl extends BaseServiceImpl implements GoodsSpuAttrService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSpuAttrServiceImpl.class);
    @Resource
    GoodsSpuAttrMapper mapper;

    /**
     * @Description 插入GoodsSpuAttr
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSpuAttr(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            mapper.insertGoodsSpuAttr(goodsSpuAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.insertGoodsSpuAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSpuAttr
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSpuAttrById(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            mapper.deleteGoodsSpuAttrById(goodsSpuAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.deleteGoodsSpuAttrById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSpuAttr
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSpuAttrById(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            mapper.updateGoodsSpuAttrById(goodsSpuAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.updateGoodsSpuAttrById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSpuAttr
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuAttr(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            mapper.getGoodsSpuAttr(goodsSpuAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.getGoodsSpuAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSpuAttr
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuAttrById(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            mapper.getGoodsSpuAttrById(goodsSpuAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.getGoodsSpuAttrById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuAttrBySpuId(JSONObject jsonObject) {
        try {
            GoodsSpuAttr goodsSpuAttr = JSONObject.toJavaObject(jsonObject, GoodsSpuAttr.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSpuAttrBySpuId(goodsSpuAttr));
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.getGoodsSpuAttrById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsHourSpuAttrValueBySpuId(JSONObject jsonObject) {
        Long spu_id = jsonObject.getLong("spu_id");
        try {
            List<String> goodsSkuAttrList = mapper.getGoodsSkuAttrByHourSpuId(spu_id);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,goodsSkuAttrList);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuAttrServiceImpl.getGoodsHourSpuAttrValueBySpuId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}