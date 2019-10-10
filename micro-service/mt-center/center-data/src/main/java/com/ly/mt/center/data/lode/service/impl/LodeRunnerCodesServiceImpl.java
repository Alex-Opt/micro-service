package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerCodesMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerCodesService;
import com.ly.mt.center.data.lode.entity.LodeRunnerCodes;
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
public class LodeRunnerCodesServiceImpl extends BaseServiceImpl implements LodeRunnerCodesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerCodesServiceImpl.class);
    @Resource
    LodeRunnerCodesMapper mapper;

    /**
     * @Description 保存LodeRunnerCodes
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerCodes(JSONObject jsonObject) {
        try {
            LodeRunnerCodes lodeRunnerCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerCodes.class);
            if (StringUtil.isEmpty(lodeRunnerCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerCodes(lodeRunnerCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCodesServiceImpl.insertLodeRunnerCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerCodes
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerCodes(JSONObject jsonObject) {
        try {
            LodeRunnerCodes lodeRunnerCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerCodes.class);
            if (StringUtil.isEmpty(lodeRunnerCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerCodes(lodeRunnerCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCodesServiceImpl.deleteLodeRunnerCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerCodes
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerCodes(JSONObject jsonObject) {
        try {
            LodeRunnerCodes lodeRunnerCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerCodes.class);
            if (StringUtil.isEmpty(lodeRunnerCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerCodes(lodeRunnerCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCodesServiceImpl.updateLodeRunnerCodesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerCodes
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerCodesByInviteCode(JSONObject jsonObject) {
        try {
            LodeRunnerCodes lodeRunnerCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerCodes.class);
            if (StringUtil.isEmpty(lodeRunnerCodes.getCode())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerCodes = mapper.getLodeRunnerCodesByCode(lodeRunnerCodes);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerCodes);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCodesServiceImpl.getLodeRunnerCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson addInviteNums(JSONObject jsonObject) {
        try {
            LodeRunnerCodes lodeRunnerCodes = JSONObject.toJavaObject(jsonObject, LodeRunnerCodes.class);
            if (StringUtil.isEmpty(lodeRunnerCodes.getShop_id()) || StringUtil.isEmpty(lodeRunnerCodes.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            mapper.addInviteNum(lodeRunnerCodes);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCodesServiceImpl.getLodeRunnerCodes出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}