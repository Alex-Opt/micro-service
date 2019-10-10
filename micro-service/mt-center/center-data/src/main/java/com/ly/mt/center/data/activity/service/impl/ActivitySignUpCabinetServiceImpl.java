package com.ly.mt.center.data.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.activity.mapper.ActivitySignUpCabinetMapper;
import com.ly.mt.center.data.activity.service.ActivitySignUpCabinetService;
import com.ly.mt.center.data.activity.entity.ActivitySignUpCabinet;
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
public class ActivitySignUpCabinetServiceImpl extends BaseServiceImpl implements ActivitySignUpCabinetService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivitySignUpCabinetServiceImpl.class);
    @Resource
    ActivitySignUpCabinetMapper mapper;

    /**
     * @Description 保存ActivitySignUpCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson insertActivitySignUpCabinet(JSONObject jsonObject) {
        try {
            ActivitySignUpCabinet activitySignUpCabinet = JSONObject.toJavaObject(jsonObject, ActivitySignUpCabinet.class);
            if (StringUtil.isEmpty(activitySignUpCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertActivitySignUpCabinet(activitySignUpCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivitySignUpCabinetServiceImpl.insertActivitySignUpCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ActivitySignUpCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson deleteActivitySignUpCabinet(JSONObject jsonObject) {
        try {
            ActivitySignUpCabinet activitySignUpCabinet = JSONObject.toJavaObject(jsonObject, ActivitySignUpCabinet.class);
            if (StringUtil.isEmpty(activitySignUpCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteActivitySignUpCabinet(activitySignUpCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivitySignUpCabinetServiceImpl.deleteActivitySignUpCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ActivitySignUpCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson updateActivitySignUpCabinet(JSONObject jsonObject) {
        try {
            ActivitySignUpCabinet activitySignUpCabinet = JSONObject.toJavaObject(jsonObject, ActivitySignUpCabinet.class);
            if (StringUtil.isEmpty(activitySignUpCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateActivitySignUpCabinet(activitySignUpCabinet);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivitySignUpCabinetServiceImpl.updateActivitySignUpCabinetById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ActivitySignUpCabinet
     * @Author taoye
     */
    @Override
    public ResponseJson getActivitySignUpCabinet(JSONObject jsonObject) {
        try {
            ActivitySignUpCabinet activitySignUpCabinet = JSONObject.toJavaObject(jsonObject, ActivitySignUpCabinet.class);
            if (StringUtil.isEmpty(activitySignUpCabinet.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            activitySignUpCabinet = mapper.getActivitySignUpCabinet(activitySignUpCabinet);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, activitySignUpCabinet);
        } catch (Exception e) {
            LOGGER.error("ActivitySignUpCabinetServiceImpl.getActivitySignUpCabinet出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}