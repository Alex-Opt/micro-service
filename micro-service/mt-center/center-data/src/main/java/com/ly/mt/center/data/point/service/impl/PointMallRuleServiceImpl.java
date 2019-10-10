package com.ly.mt.center.data.point.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.point.mapper.PointMallRuleMapper;
import com.ly.mt.center.data.point.service.PointMallRuleService;
import com.ly.mt.center.data.point.entity.PointMallRule;
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
public class PointMallRuleServiceImpl extends BaseServiceImpl implements PointMallRuleService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PointMallRuleServiceImpl.class);
    @Resource
    PointMallRuleMapper mapper;

    /**
     * @Description 保存PointMallRule
     * @Author taoye
     */
    @Override
    public ResponseJson insertPointMallRule(JSONObject jsonObject) {
        try {
            PointMallRule pointMallRule = JSONObject.toJavaObject(jsonObject, PointMallRule.class);
            if (StringUtil.isEmpty(pointMallRule.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertPointMallRule(pointMallRule);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleServiceImpl.insertPointMallRule出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除PointMallRule
     * @Author taoye
     */
    @Override
    public ResponseJson deletePointMallRule(JSONObject jsonObject) {
        try {
            PointMallRule pointMallRule = JSONObject.toJavaObject(jsonObject, PointMallRule.class);
            if (StringUtil.isEmpty(pointMallRule.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deletePointMallRule(pointMallRule);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleServiceImpl.deletePointMallRule出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新PointMallRule
     * @Author taoye
     */
    @Override
    public ResponseJson updatePointMallRule(JSONObject jsonObject) {
        try {
            PointMallRule pointMallRule = JSONObject.toJavaObject(jsonObject, PointMallRule.class);
            if (StringUtil.isEmpty(pointMallRule.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updatePointMallRule(pointMallRule);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleServiceImpl.updatePointMallRuleById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询PointMallRule
     * @Author taoye
     */
    @Override
    public ResponseJson getPointMallRule(JSONObject jsonObject) {
        try {
            PointMallRule pointMallRule = JSONObject.toJavaObject(jsonObject, PointMallRule.class);
            if (StringUtil.isEmpty(pointMallRule.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            pointMallRule = mapper.getPointMallRule(pointMallRule);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, pointMallRule);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleServiceImpl.getPointMallRule出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}