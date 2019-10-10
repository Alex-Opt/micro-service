package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgCouponGoodsMapper;
import com.ly.mt.center.data.gzg.service.GzgCouponGoodsService;
import com.ly.mt.center.data.gzg.entity.GzgCouponGoods;
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
public class GzgCouponGoodsServiceImpl extends BaseServiceImpl implements GzgCouponGoodsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCouponGoodsServiceImpl.class);
    @Resource
    GzgCouponGoodsMapper mapper;

    /**
     * @Description 保存GzgCouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgCouponGoods(JSONObject jsonObject) {
        try {
            GzgCouponGoods gzgCouponGoods = JSONObject.toJavaObject(jsonObject, GzgCouponGoods.class);
            if (StringUtil.isEmpty(gzgCouponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgCouponGoods(gzgCouponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponGoodsServiceImpl.insertGzgCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgCouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgCouponGoods(JSONObject jsonObject) {
        try {
            GzgCouponGoods gzgCouponGoods = JSONObject.toJavaObject(jsonObject, GzgCouponGoods.class);
            if (StringUtil.isEmpty(gzgCouponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgCouponGoods(gzgCouponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponGoodsServiceImpl.deleteGzgCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgCouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgCouponGoods(JSONObject jsonObject) {
        try {
            GzgCouponGoods gzgCouponGoods = JSONObject.toJavaObject(jsonObject, GzgCouponGoods.class);
            if (StringUtil.isEmpty(gzgCouponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgCouponGoods(gzgCouponGoods);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCouponGoodsServiceImpl.updateGzgCouponGoodsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCouponGoods
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCouponGoods(JSONObject jsonObject) {
        try {
            GzgCouponGoods gzgCouponGoods = JSONObject.toJavaObject(jsonObject, GzgCouponGoods.class);
            if (StringUtil.isEmpty(gzgCouponGoods.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgCouponGoods = mapper.getGzgCouponGoods(gzgCouponGoods);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCouponGoods);
        } catch (Exception e) {
            LOGGER.error("GzgCouponGoodsServiceImpl.getGzgCouponGoods出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}