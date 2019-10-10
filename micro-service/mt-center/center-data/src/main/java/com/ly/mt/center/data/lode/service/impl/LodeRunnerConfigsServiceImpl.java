package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerConfigsMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerConfigsService;
import com.ly.mt.center.data.lode.entity.LodeRunnerConfigs;
import com.ly.mt.base.service.impl.BaseServiceImpl;
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
public class LodeRunnerConfigsServiceImpl extends BaseServiceImpl implements LodeRunnerConfigsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerConfigsServiceImpl.class);
    @Resource
    LodeRunnerConfigsMapper mapper;

    /**
     * @Description 保存LodeRunnerConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerConfigs lodeRunnerConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerConfigs(lodeRunnerConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerConfigsServiceImpl.insertLodeRunnerConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerConfigs lodeRunnerConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerConfigs(lodeRunnerConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerConfigsServiceImpl.deleteLodeRunnerConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerConfigs lodeRunnerConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerConfigs(lodeRunnerConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerConfigsServiceImpl.updateLodeRunnerConfigsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerConfigs lodeRunnerConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerConfigs = mapper.getLodeRunnerConfigs(lodeRunnerConfigs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerConfigs);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerConfigsServiceImpl.getLodeRunnerConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getAllLodeRunnerConfigs(JSONObject jsonObject) {
        try {
            List<LodeRunnerConfigs> lodeRunnerConfigs = mapper.getAllLodeRunnerConfigs();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerConfigs);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerConfigsServiceImpl.getLodeRunnerConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}