package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsSkuCodeMapper;
import com.ly.mt.center.data.goods.service.GoodsSkuCodeService;
import com.ly.mt.center.data.goods.entity.GoodsSkuCode;
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
public class GoodsSkuCodeServiceImpl extends BaseServiceImpl implements GoodsSkuCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsSkuCodeServiceImpl.class);
    @Resource
    GoodsSkuCodeMapper mapper;

    /**
     * @Description 保存GoodsSkuCode
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsSkuCode(JSONObject jsonObject) {
        try {
            GoodsSkuCode goodsSkuCode = JSONObject.toJavaObject(jsonObject, GoodsSkuCode.class);
            if (StringUtil.isEmpty(goodsSkuCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsSkuCode(goodsSkuCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuCodeServiceImpl.insertGoodsSkuCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsSkuCode
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsSkuCode(JSONObject jsonObject) {
        try {
            GoodsSkuCode goodsSkuCode = JSONObject.toJavaObject(jsonObject, GoodsSkuCode.class);
            if (StringUtil.isEmpty(goodsSkuCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsSkuCode(goodsSkuCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuCodeServiceImpl.deleteGoodsSkuCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsSkuCode
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsSkuCode(JSONObject jsonObject) {
        try {
            GoodsSkuCode goodsSkuCode = JSONObject.toJavaObject(jsonObject, GoodsSkuCode.class);
            if (StringUtil.isEmpty(goodsSkuCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsSkuCode(goodsSkuCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuCodeServiceImpl.updateGoodsSkuCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsSkuCode
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsSkuCode(JSONObject jsonObject) {
        try {
            GoodsSkuCode goodsSkuCode = JSONObject.toJavaObject(jsonObject, GoodsSkuCode.class);
            if (StringUtil.isEmpty(goodsSkuCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsSkuCode = mapper.getGoodsSkuCode(goodsSkuCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsSkuCode);
        } catch (Exception e) {
            LOGGER.error("GoodsSkuCodeServiceImpl.getGoodsSkuCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}