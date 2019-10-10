package com.ly.mt.center.data.gy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gy.entity.GyShopInfo;
import com.ly.mt.center.data.gy.mapper.GyShopInfoMapper;
import com.ly.mt.center.data.gy.service.GyShopInfoService;
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
public class GyShopInfoServiceImpl extends BaseServiceImpl implements GyShopInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GyShopInfoServiceImpl.class);
    @Resource
    GyShopInfoMapper mapper;

    /**
     * @Description 保存GyShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGyShopInfo(JSONObject jsonObject) {
        try {
            GyShopInfo gyShopInfo = JSONObject.toJavaObject(jsonObject, GyShopInfo.class);
            if (StringUtil.isEmpty(gyShopInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGyShopInfo(gyShopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GyShopInfoServiceImpl.insertGyShopInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GyShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGyShopInfo(JSONObject jsonObject) {
        try {
            GyShopInfo gyShopInfo = JSONObject.toJavaObject(jsonObject, GyShopInfo.class);
            if (StringUtil.isEmpty(gyShopInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGyShopInfo(gyShopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GyShopInfoServiceImpl.deleteGyShopInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GyShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGyShopInfo(JSONObject jsonObject) {
        try {
            GyShopInfo gyShopInfo = JSONObject.toJavaObject(jsonObject, GyShopInfo.class);
            if (StringUtil.isEmpty(gyShopInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGyShopInfo(gyShopInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GyShopInfoServiceImpl.updateGyShopInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GyShopInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGyShopInfo(JSONObject jsonObject) {
        try {
            GyShopInfo gyShopInfo = JSONObject.toJavaObject(jsonObject, GyShopInfo.class);
            if (StringUtil.isEmpty(gyShopInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gyShopInfo = mapper.getGyShopInfo(gyShopInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gyShopInfo);
        } catch (Exception e) {
            LOGGER.error("GyShopInfoServiceImpl.getGyShopInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}