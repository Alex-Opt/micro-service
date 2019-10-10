package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgStoreInfoMapper;
import com.ly.mt.center.data.gzg.service.GzgStoreInfoService;
import com.ly.mt.center.data.gzg.entity.GzgStoreInfo;
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
public class GzgStoreInfoServiceImpl extends BaseServiceImpl implements GzgStoreInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgStoreInfoServiceImpl.class);
    @Resource
    GzgStoreInfoMapper mapper;

    /**
     * @Description 保存GzgStoreInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgStoreInfo(JSONObject jsonObject) {
        try {
            GzgStoreInfo gzgStoreInfo = JSONObject.toJavaObject(jsonObject, GzgStoreInfo.class);
            if (StringUtil.isEmpty(gzgStoreInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgStoreInfo(gzgStoreInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgStoreInfoServiceImpl.insertGzgStoreInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgStoreInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgStoreInfo(JSONObject jsonObject) {
        try {
            GzgStoreInfo gzgStoreInfo = JSONObject.toJavaObject(jsonObject, GzgStoreInfo.class);
            if (StringUtil.isEmpty(gzgStoreInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgStoreInfo(gzgStoreInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgStoreInfoServiceImpl.deleteGzgStoreInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgStoreInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgStoreInfo(JSONObject jsonObject) {
        try {
            GzgStoreInfo gzgStoreInfo = JSONObject.toJavaObject(jsonObject, GzgStoreInfo.class);
            if (StringUtil.isEmpty(gzgStoreInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgStoreInfo(gzgStoreInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgStoreInfoServiceImpl.updateGzgStoreInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgStoreInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgStoreInfo(JSONObject jsonObject) {
        try {
            GzgStoreInfo gzgStoreInfo = JSONObject.toJavaObject(jsonObject, GzgStoreInfo.class);
            if (StringUtil.isEmpty(gzgStoreInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgStoreInfo = mapper.getGzgStoreInfo(gzgStoreInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgStoreInfo);
        } catch (Exception e) {
            LOGGER.error("GzgStoreInfoServiceImpl.getGzgStoreInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}