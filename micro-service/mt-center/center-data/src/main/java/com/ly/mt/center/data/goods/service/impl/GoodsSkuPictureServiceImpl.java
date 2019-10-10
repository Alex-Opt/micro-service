package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSkuPictureMapper;
import com.ly.mt.center.data.goods.service.GoodsSkuPictureService;
import com.ly.mt.center.data.goods.entity.GoodsSkuPicture;
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
public class GoodsSkuPictureServiceImpl extends BaseServiceImpl implements GoodsSkuPictureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSkuPictureServiceImpl.class);
    @Resource
    GoodsSkuPictureMapper mapper;

    /**
     * @Description 插入GoodsSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSkuPicture(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            mapper.insertGoodsSkuPicture(goodsSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.insertGoodsSkuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSkuPictureById(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            mapper.deleteGoodsSkuPictureById(goodsSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.deleteGoodsSkuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSkuPictureById(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            mapper.updateGoodsSkuPictureById(goodsSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.updateGoodsSkuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuPicture(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            mapper.getGoodsSkuPicture(goodsSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.getGoodsSkuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSkuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuPictureById(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            mapper.getGoodsSkuPictureById(goodsSkuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.getGoodsSkuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSkuPictureBySkuId(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSkuPictureBySkuId(goodsSkuPicture));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.getGoodsSkuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getSkuPictureBySkuId(JSONObject jsonObject) {
        try {
            GoodsSkuPicture goodsSkuPicture = JSONObject.toJavaObject(jsonObject, GoodsSkuPicture.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getSkuPictureBySkuId(goodsSkuPicture));
        } catch (Exception e) {
            LOGGER.error("GoodsSkuPictureServiceImpl.getSkuPictureBySkuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}