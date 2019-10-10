package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerUserConfigsMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerUserConfigsService;
import com.ly.mt.center.data.lode.entity.LodeRunnerUserConfigs;
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
public class LodeRunnerUserConfigsServiceImpl extends BaseServiceImpl implements LodeRunnerUserConfigsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerUserConfigsServiceImpl.class);
    @Resource
    LodeRunnerUserConfigsMapper mapper;

    /**
     * @Description 保存LodeRunnerUserConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerUserConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerUserConfigs lodeRunnerUserConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerUserConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerUserConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerUserConfigs(lodeRunnerUserConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserConfigsServiceImpl.insertLodeRunnerUserConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerUserConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerUserConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerUserConfigs lodeRunnerUserConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerUserConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerUserConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerUserConfigs(lodeRunnerUserConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserConfigsServiceImpl.deleteLodeRunnerUserConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerUserConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerUserConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerUserConfigs lodeRunnerUserConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerUserConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerUserConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerUserConfigs(lodeRunnerUserConfigs);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserConfigsServiceImpl.updateLodeRunnerUserConfigsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerUserConfigs
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerUserConfigs(JSONObject jsonObject) {
        try {
            LodeRunnerUserConfigs lodeRunnerUserConfigs = JSONObject.toJavaObject(jsonObject, LodeRunnerUserConfigs.class);
            if (StringUtil.isEmpty(lodeRunnerUserConfigs.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerUserConfigs = mapper.getLodeRunnerUserConfigs(lodeRunnerUserConfigs);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerUserConfigs);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserConfigsServiceImpl.getLodeRunnerUserConfigs出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}