package com.ly.mt.center.data.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.activity.mapper.ActivityUserGradeMapper;
import com.ly.mt.center.data.activity.service.ActivityUserGradeService;
import com.ly.mt.center.data.activity.entity.ActivityUserGrade;
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
public class ActivityUserGradeServiceImpl extends BaseServiceImpl implements ActivityUserGradeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityUserGradeServiceImpl.class);
    @Resource
    ActivityUserGradeMapper mapper;

    /**
     * @Description 保存ActivityUserGrade
     * @Author taoye
     */
    @Override
    public ResponseJson insertActivityUserGrade(JSONObject jsonObject) {
        try {
            ActivityUserGrade activityUserGrade = JSONObject.toJavaObject(jsonObject, ActivityUserGrade.class);
            if (StringUtil.isEmpty(activityUserGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertActivityUserGrade(activityUserGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeServiceImpl.insertActivityUserGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ActivityUserGrade
     * @Author taoye
     */
    @Override
    public ResponseJson deleteActivityUserGrade(JSONObject jsonObject) {
        try {
            ActivityUserGrade activityUserGrade = JSONObject.toJavaObject(jsonObject, ActivityUserGrade.class);
            if (StringUtil.isEmpty(activityUserGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteActivityUserGrade(activityUserGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeServiceImpl.deleteActivityUserGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ActivityUserGrade
     * @Author taoye
     */
    @Override
    public ResponseJson updateActivityUserGrade(JSONObject jsonObject) {
        try {
            ActivityUserGrade activityUserGrade = JSONObject.toJavaObject(jsonObject, ActivityUserGrade.class);
            if (StringUtil.isEmpty(activityUserGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateActivityUserGrade(activityUserGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeServiceImpl.updateActivityUserGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ActivityUserGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getActivityUserGrade(JSONObject jsonObject) {
        try {
            ActivityUserGrade activityUserGrade = JSONObject.toJavaObject(jsonObject, ActivityUserGrade.class);
            if (StringUtil.isEmpty(activityUserGrade.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            activityUserGrade = mapper.getActivityUserGrade(activityUserGrade);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, activityUserGrade);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeServiceImpl.getActivityUserGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}