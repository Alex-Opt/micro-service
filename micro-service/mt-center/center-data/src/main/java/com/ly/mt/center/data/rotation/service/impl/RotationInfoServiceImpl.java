package com.ly.mt.center.data.rotation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.rotation.entity.RotationInfo;
import com.ly.mt.center.data.rotation.mapper.RotationInfoMapper;
import com.ly.mt.center.data.rotation.service.RotationInfoService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class RotationInfoServiceImpl extends BaseServiceImpl implements RotationInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RotationInfoServiceImpl.class);
    @Resource
    RotationInfoMapper mapper;

    /**
     * @Description 保存RotationInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertRotationInfo(JSONObject jsonObject) {
        try {
            RotationInfo rotationInfo = JSONObject.toJavaObject(jsonObject, RotationInfo.class);
            if (StringUtil.isEmpty(rotationInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertRotationInfo(rotationInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("RotationInfoServiceImpl.insertRotationInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除RotationInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteRotationInfo(JSONObject jsonObject) {
        try {
            RotationInfo rotationInfo = JSONObject.toJavaObject(jsonObject, RotationInfo.class);
            if (StringUtil.isEmpty(rotationInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteRotationInfo(rotationInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("RotationInfoServiceImpl.deleteRotationInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新RotationInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateRotationInfo(JSONObject jsonObject) {
        try {
            RotationInfo rotationInfo = JSONObject.toJavaObject(jsonObject, RotationInfo.class);
            if (StringUtil.isEmpty(rotationInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateRotationInfo(rotationInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("RotationInfoServiceImpl.updateRotationInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询RotationInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getRotationInfo(JSONObject jsonObject) {
        try {
            RotationInfo rotationInfo = JSONObject.toJavaObject(jsonObject, RotationInfo.class);
            if (StringUtil.isEmpty(rotationInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            rotationInfo = mapper.getRotationInfo(rotationInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, rotationInfo);
        } catch (Exception e) {
            LOGGER.error("RotationInfoServiceImpl.getRotationInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询RotationInfo集合
     * @Author taoye
     */
    @Override
    public ResponseJson listRotationInfo(JSONObject jsonObject) {
        try {
            List<RotationInfo> list  = mapper.listRotationInfo();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, list);
        } catch (Exception e) {
            LOGGER.error("RotationInfoServiceImpl.listRotationInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}