package com.ly.mt.center.data.point.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.point.mapper.PointConfigMapper;
import com.ly.mt.center.data.point.service.PointConfigService;
import com.ly.mt.center.data.point.entity.PointConfig;
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
public class PointConfigServiceImpl extends BaseServiceImpl implements PointConfigService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PointConfigServiceImpl.class);
    @Resource
    PointConfigMapper mapper;

    /**
     * @Description 插入PointConfig
     * @Author taoye
     */
    @Override
    public ResponseJson insertPointConfig(JSONObject jsonObject) {
        try {
            PointConfig pointConfig = JSONObject.toJavaObject(jsonObject, PointConfig.class);
            mapper.insertPointConfig(pointConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointConfigServiceImpl.insertPointConfig出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除PointConfig
     * @Author taoye
     */
    @Override
    public ResponseJson deletePointConfigById(JSONObject jsonObject) {
        try {
            PointConfig pointConfig = JSONObject.toJavaObject(jsonObject, PointConfig.class);
            mapper.deletePointConfigById(pointConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointConfigServiceImpl.deletePointConfigById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新PointConfig
     * @Author taoye
     */
    @Override
    public ResponseJson updatePointConfigById(JSONObject jsonObject) {
        try {
            PointConfig pointConfig = JSONObject.toJavaObject(jsonObject, PointConfig.class);
            mapper.updatePointConfigById(pointConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointConfigServiceImpl.updatePointConfigById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询PointConfig
     * @Author taoye
     */
    @Override
    public ResponseJson getPointConfig(JSONObject jsonObject) {
        try {
            PointConfig pointConfig = JSONObject.toJavaObject(jsonObject, PointConfig.class);
            mapper.getPointConfig(pointConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointConfigServiceImpl.getPointConfig出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询PointConfig
     * @Author taoye
     */
    @Override
    public ResponseJson getPointConfigById(JSONObject jsonObject) {
        try {
            PointConfig pointConfig = JSONObject.toJavaObject(jsonObject, PointConfig.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getPointConfigById(pointConfig));
        } catch (Exception e) {
            LOGGER.error("PointConfigServiceImpl.getPointConfigById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}