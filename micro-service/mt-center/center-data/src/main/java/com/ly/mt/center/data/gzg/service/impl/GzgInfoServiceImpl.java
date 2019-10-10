package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgInfoMapper;
import com.ly.mt.center.data.gzg.service.GzgInfoService;
import com.ly.mt.center.data.gzg.entity.GzgInfo;
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
public class GzgInfoServiceImpl extends BaseServiceImpl implements GzgInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgInfoServiceImpl.class);
    @Resource
    GzgInfoMapper mapper;

    /**
     * @Description 插入GzgInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgInfo(JSONObject jsonObject) {
        try {
            GzgInfo gzgInfo = JSONObject.toJavaObject(jsonObject, GzgInfo.class);
            mapper.insertGzgInfo(gzgInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgInfoServiceImpl.insertGzgInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GzgInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgInfoById(JSONObject jsonObject) {
        try {
            GzgInfo gzgInfo = JSONObject.toJavaObject(jsonObject, GzgInfo.class);
            mapper.deleteGzgInfoById(gzgInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgInfoServiceImpl.deleteGzgInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新GzgInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgInfoById(JSONObject jsonObject) {
        try {
            GzgInfo gzgInfo = JSONObject.toJavaObject(jsonObject, GzgInfo.class);
            mapper.updateGzgInfoById(gzgInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgInfoServiceImpl.updateGzgInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GzgInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgInfo(JSONObject jsonObject) {
        try {
            GzgInfo gzgInfo = JSONObject.toJavaObject(jsonObject, GzgInfo.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getGzgInfoByCode(gzgInfo));
        } catch (Exception e) {
            LOGGER.error("GzgInfoServiceImpl.getGzgInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询GzgInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgInfoById(JSONObject jsonObject) {
        try {
            GzgInfo gzgInfo = JSONObject.toJavaObject(jsonObject, GzgInfo.class);
            mapper.getGzgInfoById(gzgInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgInfoServiceImpl.getGzgInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}