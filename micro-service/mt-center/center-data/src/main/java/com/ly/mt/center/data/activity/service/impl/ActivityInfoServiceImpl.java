package com.ly.mt.center.data.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.activity.mapper.ActivityInfoMapper;
import com.ly.mt.center.data.activity.service.ActivityInfoService;
import com.ly.mt.center.data.activity.entity.ActivityInfo;
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
public class ActivityInfoServiceImpl extends BaseServiceImpl implements ActivityInfoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityInfoServiceImpl.class);
    @Resource
    ActivityInfoMapper mapper;

    /**
     * @Description 保存ActivityInfo
     * @Author taoye
     */
    @Override
    public ResponseJson insertActivityInfo(JSONObject jsonObject) {
        try {
            ActivityInfo activityInfo = JSONObject.toJavaObject(jsonObject, ActivityInfo.class);
            if (StringUtil.isEmpty(activityInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertActivityInfo(activityInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityInfoServiceImpl.insertActivityInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ActivityInfo
     * @Author taoye
     */
    @Override
    public ResponseJson deleteActivityInfo(JSONObject jsonObject) {
        try {
            ActivityInfo activityInfo = JSONObject.toJavaObject(jsonObject, ActivityInfo.class);
            if (StringUtil.isEmpty(activityInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteActivityInfo(activityInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityInfoServiceImpl.deleteActivityInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ActivityInfo
     * @Author taoye
     */
    @Override
    public ResponseJson updateActivityInfo(JSONObject jsonObject) {
        try {
            ActivityInfo activityInfo = JSONObject.toJavaObject(jsonObject, ActivityInfo.class);
            if (StringUtil.isEmpty(activityInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateActivityInfo(activityInfo);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityInfoServiceImpl.updateActivityInfoById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ActivityInfo
     * @Author taoye
     */
    @Override
    public ResponseJson getActivityInfo(JSONObject jsonObject) {
        try {
            ActivityInfo activityInfo = JSONObject.toJavaObject(jsonObject, ActivityInfo.class);
            if (StringUtil.isEmpty(activityInfo.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            activityInfo = mapper.getActivityInfo(activityInfo);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, activityInfo);
        } catch (Exception e) {
            LOGGER.error("ActivityInfoServiceImpl.getActivityInfo出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 根据spuId查询优惠活动数据
     * @Author xinguangzhi
     */
    @Override
    public ResponseJson getActivityInfoBySpuId(JSONObject jsonObject) {
        try {
            String spuId = jsonObject.getString("spu_id");
            if (StringUtil.isEmpty(spuId)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,mapper.getActivityInfoBySpuId(spuId));
        } catch (Exception e) {
            LOGGER.error("ActivityInfoServiceImpl.getActivityInfoBySpuId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}