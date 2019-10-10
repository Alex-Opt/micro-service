package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSpuPictureMapper;
import com.ly.mt.center.data.goods.service.GoodsSpuPictureService;
import com.ly.mt.center.data.goods.entity.GoodsSpuPicture;
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
public class GoodsSpuPictureServiceImpl extends BaseServiceImpl implements GoodsSpuPictureService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSpuPictureServiceImpl.class);
    @Resource
    GoodsSpuPictureMapper mapper;

    /**
     * @Description 插入GoodsSpuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSpuPicture(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            mapper.insertGoodsSpuPicture(goodsSpuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.insertGoodsSpuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GoodsSpuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSpuPictureById(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            mapper.deleteGoodsSpuPictureById(goodsSpuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.deleteGoodsSpuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GoodsSpuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSpuPictureById(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            mapper.updateGoodsSpuPictureById(goodsSpuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.updateGoodsSpuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GoodsSpuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuPicture(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            mapper.getGoodsSpuPicture(goodsSpuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.getGoodsSpuPicture出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GoodsSpuPicture
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSpuPictureById(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            mapper.getGoodsSpuPictureById(goodsSpuPicture);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.getGoodsSpuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGoodsSpuPictureBySpuId(JSONObject jsonObject) {
        try {
            GoodsSpuPicture goodsSpuPicture = JSONObject.toJavaObject(jsonObject, GoodsSpuPicture.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGoodsSpuPictureBySpuId(goodsSpuPicture));
        } catch (Exception e) {
            LOGGER.error("GoodsSpuPictureServiceImpl.getGoodsSpuPictureById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}