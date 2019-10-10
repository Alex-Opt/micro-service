package com.ly.mt.center.data.goods.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.goods.mapper.GoodsBrandInfoMapper;
import com.ly.mt.center.data.goods.service.GoodsBrandInfoService;
import com.ly.mt.center.data.goods.entity.GoodsBrandInfo;
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
public class GoodsBrandInfoServiceImpl extends BaseServiceImpl implements GoodsBrandInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GoodsBrandInfoServiceImpl.class);
    @Resource
    GoodsBrandInfoMapper mapper;

    /**
     * @Description 保存GoodsBrandInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGoodsBrandInfo(JSONObject jsonObject) {
        try {
            GoodsBrandInfo goodsBrandInfo = JSONObject.toJavaObject(jsonObject, GoodsBrandInfo.class);
            if (StringUtil.isEmpty(goodsBrandInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGoodsBrandInfo(goodsBrandInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsBrandInfoServiceImpl.insertGoodsBrandInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GoodsBrandInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGoodsBrandInfo(JSONObject jsonObject) {
        try {
            GoodsBrandInfo goodsBrandInfo = JSONObject.toJavaObject(jsonObject, GoodsBrandInfo.class);
            if (StringUtil.isEmpty(goodsBrandInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGoodsBrandInfo(goodsBrandInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsBrandInfoServiceImpl.deleteGoodsBrandInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GoodsBrandInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGoodsBrandInfo(JSONObject jsonObject) {
        try {
            GoodsBrandInfo goodsBrandInfo = JSONObject.toJavaObject(jsonObject, GoodsBrandInfo.class);
            if (StringUtil.isEmpty(goodsBrandInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGoodsBrandInfo(goodsBrandInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GoodsBrandInfoServiceImpl.updateGoodsBrandInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GoodsBrandInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGoodsBrandInfo(JSONObject jsonObject) {
        try {
            GoodsBrandInfo goodsBrandInfo = JSONObject.toJavaObject(jsonObject, GoodsBrandInfo.class);
            if (StringUtil.isEmpty(goodsBrandInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            goodsBrandInfo = mapper.getGoodsBrandInfo(goodsBrandInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, goodsBrandInfo);
        } catch (Exception e) {
            LOGGER.error("GoodsBrandInfoServiceImpl.getGoodsBrandInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}