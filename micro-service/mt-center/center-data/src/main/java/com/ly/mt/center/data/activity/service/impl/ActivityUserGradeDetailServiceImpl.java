package com.ly.mt.center.data.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.activity.mapper.ActivityUserGradeDetailMapper;
import com.ly.mt.center.data.activity.service.ActivityUserGradeDetailService;
import com.ly.mt.center.data.activity.entity.ActivityUserGradeDetail;
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
public class ActivityUserGradeDetailServiceImpl extends BaseServiceImpl implements ActivityUserGradeDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityUserGradeDetailServiceImpl.class);
    @Resource
    ActivityUserGradeDetailMapper mapper;

    /**
     * @Description 保存ActivityUserGradeDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertActivityUserGradeDetail(JSONObject jsonObject) {
        try {
            ActivityUserGradeDetail activityUserGradeDetail = JSONObject.toJavaObject(jsonObject, ActivityUserGradeDetail.class);
            if (StringUtil.isEmpty(activityUserGradeDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertActivityUserGradeDetail(activityUserGradeDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeDetailServiceImpl.insertActivityUserGradeDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ActivityUserGradeDetail
     * @Author taoye
     */
    @Override
    public ResponseJson deleteActivityUserGradeDetail(JSONObject jsonObject) {
        try {
            ActivityUserGradeDetail activityUserGradeDetail = JSONObject.toJavaObject(jsonObject, ActivityUserGradeDetail.class);
            if (StringUtil.isEmpty(activityUserGradeDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteActivityUserGradeDetail(activityUserGradeDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeDetailServiceImpl.deleteActivityUserGradeDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ActivityUserGradeDetail
     * @Author taoye
     */
    @Override
    public ResponseJson updateActivityUserGradeDetail(JSONObject jsonObject) {
        try {
            ActivityUserGradeDetail activityUserGradeDetail = JSONObject.toJavaObject(jsonObject, ActivityUserGradeDetail.class);
            if (StringUtil.isEmpty(activityUserGradeDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateActivityUserGradeDetail(activityUserGradeDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeDetailServiceImpl.updateActivityUserGradeDetailById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ActivityUserGradeDetail
     * @Author taoye
     */
    @Override
    public ResponseJson getActivityUserGradeDetail(JSONObject jsonObject) {
        try {
            ActivityUserGradeDetail activityUserGradeDetail = JSONObject.toJavaObject(jsonObject, ActivityUserGradeDetail.class);
            if (StringUtil.isEmpty(activityUserGradeDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            activityUserGradeDetail = mapper.getActivityUserGradeDetail(activityUserGradeDetail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, activityUserGradeDetail);
        } catch (Exception e) {
            LOGGER.error("ActivityUserGradeDetailServiceImpl.getActivityUserGradeDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}