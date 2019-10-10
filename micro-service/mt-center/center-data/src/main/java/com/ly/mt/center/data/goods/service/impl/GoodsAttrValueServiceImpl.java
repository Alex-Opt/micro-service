package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsAttrValueMapper;
import com.ly.mt.center.data.goods.service.GoodsAttrValueService;
import com.ly.mt.center.data.goods.entity.GoodsAttrValue;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GoodsAttrValueServiceImpl extends BaseServiceImpl implements GoodsAttrValueService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsAttrValueServiceImpl.class);
    @Resource
    GoodsAttrValueMapper mapper;

    /**
     * @Description 保存GoodsAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsAttrValue(JSONObject jsonObject) {
        try {
            GoodsAttrValue goodsAttrValue = JSONObject.toJavaObject(jsonObject, GoodsAttrValue.class);
            if (StringUtil.isEmpty(goodsAttrValue.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsAttrValue(goodsAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrValueServiceImpl.insertGoodsAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsAttrValue(JSONObject jsonObject) {
        try {
            GoodsAttrValue goodsAttrValue = JSONObject.toJavaObject(jsonObject, GoodsAttrValue.class);
            if (StringUtil.isEmpty(goodsAttrValue.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsAttrValue(goodsAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrValueServiceImpl.deleteGoodsAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsAttrValue(JSONObject jsonObject) {
        try {
            GoodsAttrValue goodsAttrValue = JSONObject.toJavaObject(jsonObject, GoodsAttrValue.class);
            if (StringUtil.isEmpty(goodsAttrValue.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsAttrValue(goodsAttrValue);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrValueServiceImpl.updateGoodsAttrValueById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsAttrValue
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsAttrValue(JSONObject jsonObject) {
        try {
            GoodsAttrValue goodsAttrValue = JSONObject.toJavaObject(jsonObject, GoodsAttrValue.class);
            if (StringUtil.isEmpty(goodsAttrValue.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsAttrValue = mapper.getGoodsAttrValue(goodsAttrValue);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsAttrValue);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrValueServiceImpl.getGoodsAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 根据属性值id查询属性值和属性
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson getGoodsAttrByValueId(JSONObject jsonObject) {
        try {
            GoodsAttrValue goodsAttrValue = JSONObject.toJavaObject(jsonObject, GoodsAttrValue.class);
            if (StringUtil.isEmpty(goodsAttrValue.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            Map map = mapper.getGoodsAttrByValueId(goodsAttrValue);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, map);
        } catch (Exception e) {
            LOGGER.error("GoodsAttrValueServiceImpl.getGoodsAttrValue出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}