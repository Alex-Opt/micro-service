package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerCluesMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerCluesService;
import com.ly.mt.center.data.lode.entity.LodeRunnerClues;
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
public class LodeRunnerCluesServiceImpl extends BaseServiceImpl implements LodeRunnerCluesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerCluesServiceImpl.class);
    @Resource
    LodeRunnerCluesMapper mapper;

    /**
     * @Description 保存LodeRunnerClues
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerClues(JSONObject jsonObject) {
        try {
            LodeRunnerClues lodeRunnerClues = JSONObject.toJavaObject(jsonObject, LodeRunnerClues.class);
            if (StringUtil.isEmpty(lodeRunnerClues.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerClues(lodeRunnerClues);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCluesServiceImpl.insertLodeRunnerClues出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerClues
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerClues(JSONObject jsonObject) {
        try {
            LodeRunnerClues lodeRunnerClues = JSONObject.toJavaObject(jsonObject, LodeRunnerClues.class);
            if (StringUtil.isEmpty(lodeRunnerClues.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerClues(lodeRunnerClues);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCluesServiceImpl.deleteLodeRunnerClues出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerClues
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerClues(JSONObject jsonObject) {
        try {
            LodeRunnerClues lodeRunnerClues = JSONObject.toJavaObject(jsonObject, LodeRunnerClues.class);
            if (StringUtil.isEmpty(lodeRunnerClues.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerClues(lodeRunnerClues);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCluesServiceImpl.updateLodeRunnerCluesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerClues
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerClues(JSONObject jsonObject) {
        try {
            LodeRunnerClues lodeRunnerClues = JSONObject.toJavaObject(jsonObject, LodeRunnerClues.class);
            if (StringUtil.isEmpty(lodeRunnerClues.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerClues = mapper.getLodeRunnerClues(lodeRunnerClues);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerClues);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerCluesServiceImpl.getLodeRunnerClues出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}