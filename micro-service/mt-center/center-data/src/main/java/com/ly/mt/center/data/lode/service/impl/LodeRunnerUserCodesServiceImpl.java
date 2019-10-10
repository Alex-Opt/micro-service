package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerUserCodesMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerUserCodesService;
import com.ly.mt.center.data.lode.entity.LodeRunnerUserCodes;
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
public class LodeRunnerUserCodesServiceImpl extends BaseServiceImpl implements LodeRunnerUserCodesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerUserCodesServiceImpl.class);
    @Resource
    LodeRunnerUserCodesMapper mapper;

    /**
     * @Description 保存LodeRunnerUserCodes
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerUserCodes(JSONObject jsonObject) {
        try {
            LodeRunnerUserCodes lodeRunnerUserCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerUserCodes.class);
            if (StringUtil.isEmpty(lodeRunnerUserCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerUserCodes(lodeRunnerUserCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserCodesServiceImpl.insertLodeRunnerUserCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerUserCodes
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerUserCodes(JSONObject jsonObject) {
        try {
            LodeRunnerUserCodes lodeRunnerUserCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerUserCodes.class);
            if (StringUtil.isEmpty(lodeRunnerUserCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerUserCodes(lodeRunnerUserCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserCodesServiceImpl.deleteLodeRunnerUserCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerUserCodes
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerUserCodes(JSONObject jsonObject) {
        try {
            LodeRunnerUserCodes lodeRunnerUserCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerUserCodes.class);
            if (StringUtil.isEmpty(lodeRunnerUserCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerUserCodes(lodeRunnerUserCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserCodesServiceImpl.updateLodeRunnerUserCodesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerUserCodes
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerUserCodes(JSONObject jsonObject) {
        try {
            LodeRunnerUserCodes lodeRunnerUserCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerUserCodes.class);
            if (StringUtil.isEmpty(lodeRunnerUserCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerUserCodes = mapper.getLodeRunnerUserCodes(lodeRunnerUserCodes);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerUserCodes);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserCodesServiceImpl.getLodeRunnerUserCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}