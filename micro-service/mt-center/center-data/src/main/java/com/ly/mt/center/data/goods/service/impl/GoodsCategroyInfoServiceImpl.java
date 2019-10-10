package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsCategroyInfoMapper;
import com.ly.mt.center.data.goods.service.GoodsCategroyInfoService;
import com.ly.mt.center.data.goods.entity.GoodsCategroyInfo;
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
public class GoodsCategroyInfoServiceImpl extends BaseServiceImpl implements GoodsCategroyInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsCategroyInfoServiceImpl.class);
    @Resource
    GoodsCategroyInfoMapper mapper;

    /**
     * @Description 保存GoodsCategroyInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsCategroyInfo(JSONObject jsonObject) {
        try {
            GoodsCategroyInfo goodsCategroyInfo = JSONObject.toJavaObject(jsonObject, GoodsCategroyInfo.class);
            if (StringUtil.isEmpty(goodsCategroyInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsCategroyInfo(goodsCategroyInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyInfoServiceImpl.insertGoodsCategroyInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsCategroyInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsCategroyInfo(JSONObject jsonObject) {
        try {
            GoodsCategroyInfo goodsCategroyInfo = JSONObject.toJavaObject(jsonObject, GoodsCategroyInfo.class);
            if (StringUtil.isEmpty(goodsCategroyInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsCategroyInfo(goodsCategroyInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyInfoServiceImpl.deleteGoodsCategroyInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsCategroyInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsCategroyInfo(JSONObject jsonObject) {
        try {
            GoodsCategroyInfo goodsCategroyInfo = JSONObject.toJavaObject(jsonObject, GoodsCategroyInfo.class);
            if (StringUtil.isEmpty(goodsCategroyInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsCategroyInfo(goodsCategroyInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyInfoServiceImpl.updateGoodsCategroyInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsCategroyInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsCategroyInfo(JSONObject jsonObject) {
        try {
            GoodsCategroyInfo goodsCategroyInfo = JSONObject.toJavaObject(jsonObject, GoodsCategroyInfo.class);
            if (StringUtil.isEmpty(goodsCategroyInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsCategroyInfo = mapper.getGoodsCategroyInfo(goodsCategroyInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsCategroyInfo);
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyInfoServiceImpl.getGoodsCategroyInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * 根据上级id查询子级数据
     * @param goodsCategroyInfo
     * @return
     */
    @Override
    public ResponseJson getGoodsCategroyInfoByParentId(JSONObject jsonObject) {
        try {
            GoodsCategroyInfo goodsCategroyInfo = JSONObject.toJavaObject(jsonObject, GoodsCategroyInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getGoodsCategroyInfoByParentId(goodsCategroyInfo));
        } catch (Exception e) {
            LOGGER.error("GoodsCategroyInfoServiceImpl.getGoodsCategroyInfoByParentId:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}