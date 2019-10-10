package com.ly.mt.center.data.hd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.hd.mapper.HdOperatorInfoMapper;
import com.ly.mt.center.data.hd.service.HdOperatorInfoService;
import com.ly.mt.center.data.hd.entity.HdOperatorInfo;
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
public class HdOperatorInfoServiceImpl extends BaseServiceImpl implements HdOperatorInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(HdOperatorInfoServiceImpl.class);
    @Resource
    HdOperatorInfoMapper mapper;

    /**
     * @Description 保存HdOperatorInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertHdOperatorInfo(JSONObject jsonObject) {
        try {
            HdOperatorInfo hdOperatorInfo = JSONObject.toJavaObject(jsonObject, HdOperatorInfo.class);
            if (StringUtil.isEmpty(hdOperatorInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertHdOperatorInfo(hdOperatorInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdOperatorInfoServiceImpl.insertHdOperatorInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除HdOperatorInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteHdOperatorInfo(JSONObject jsonObject) {
        try {
            HdOperatorInfo hdOperatorInfo = JSONObject.toJavaObject(jsonObject, HdOperatorInfo.class);
            if (StringUtil.isEmpty(hdOperatorInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteHdOperatorInfo(hdOperatorInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdOperatorInfoServiceImpl.deleteHdOperatorInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新HdOperatorInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateHdOperatorInfo(JSONObject jsonObject) {
        try {
            HdOperatorInfo hdOperatorInfo = JSONObject.toJavaObject(jsonObject, HdOperatorInfo.class);
            if (StringUtil.isEmpty(hdOperatorInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateHdOperatorInfo(hdOperatorInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("HdOperatorInfoServiceImpl.updateHdOperatorInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询HdOperatorInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getHdOperatorInfo(JSONObject jsonObject) {
        try {
            HdOperatorInfo hdOperatorInfo = JSONObject.toJavaObject(jsonObject, HdOperatorInfo.class);
            if (StringUtil.isEmpty(hdOperatorInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            hdOperatorInfo = mapper.getHdOperatorInfo(hdOperatorInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, hdOperatorInfo);
        } catch (Exception e) {
            LOGGER.error("HdOperatorInfoServiceImpl.getHdOperatorInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}