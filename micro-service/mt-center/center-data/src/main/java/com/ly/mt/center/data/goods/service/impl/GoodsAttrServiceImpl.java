package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsAttrMapper;
import com.ly.mt.center.data.goods.service.GoodsAttrService;
import com.ly.mt.center.data.goods.entity.GoodsAttr;
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
public class GoodsAttrServiceImpl extends BaseServiceImpl implements GoodsAttrService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsAttrServiceImpl.class);
    @Resource
    GoodsAttrMapper mapper;

    /**
     * @Description 保存GoodsAttr
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsAttr(JSONObject jsonObject) {
        try {
            GoodsAttr goodsAttr = JSONObject.toJavaObject(jsonObject, GoodsAttr.class);
            if (StringUtil.isEmpty(goodsAttr.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsAttr(goodsAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrServiceImpl.insertGoodsAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsAttr
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsAttr(JSONObject jsonObject) {
        try {
            GoodsAttr goodsAttr = JSONObject.toJavaObject(jsonObject, GoodsAttr.class);
            if (StringUtil.isEmpty(goodsAttr.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsAttr(goodsAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrServiceImpl.deleteGoodsAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsAttr
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsAttr(JSONObject jsonObject) {
        try {
            GoodsAttr goodsAttr = JSONObject.toJavaObject(jsonObject, GoodsAttr.class);
            if (StringUtil.isEmpty(goodsAttr.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsAttr(goodsAttr);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrServiceImpl.updateGoodsAttrById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsAttr
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsAttr(JSONObject jsonObject) {
        try {
            GoodsAttr goodsAttr = JSONObject.toJavaObject(jsonObject, GoodsAttr.class);
            if (StringUtil.isEmpty(goodsAttr.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsAttr = mapper.getGoodsAttr(goodsAttr);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsAttr);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrServiceImpl.getGoodsAttr出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}