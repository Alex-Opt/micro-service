package com.ly.mt.center.data.activity.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.activity.mapper.ActivityGoodsDetailMapper;
import com.ly.mt.center.data.activity.service.ActivityGoodsDetailService;
import com.ly.mt.center.data.activity.entity.ActivityGoodsDetail;
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
public class ActivityGoodsDetailServiceImpl extends BaseServiceImpl implements ActivityGoodsDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ActivityGoodsDetailServiceImpl.class);
    @Resource
    ActivityGoodsDetailMapper mapper;

    /**
     * @Description 保存ActivityGoodsDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertActivityGoodsDetail(JSONObject jsonObject) {
        try {
            ActivityGoodsDetail activityGoodsDetail = JSONObject.toJavaObject(jsonObject, ActivityGoodsDetail.class);
            if (StringUtil.isEmpty(activityGoodsDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertActivityGoodsDetail(activityGoodsDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityGoodsDetailServiceImpl.insertActivityGoodsDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除ActivityGoodsDetail
     * @Author taoye
     */
    @Override
    public ResponseJson deleteActivityGoodsDetail(JSONObject jsonObject) {
        try {
            ActivityGoodsDetail activityGoodsDetail = JSONObject.toJavaObject(jsonObject, ActivityGoodsDetail.class);
            if (StringUtil.isEmpty(activityGoodsDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteActivityGoodsDetail(activityGoodsDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityGoodsDetailServiceImpl.deleteActivityGoodsDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新ActivityGoodsDetail
     * @Author taoye
     */
    @Override
    public ResponseJson updateActivityGoodsDetail(JSONObject jsonObject) {
        try {
            ActivityGoodsDetail activityGoodsDetail = JSONObject.toJavaObject(jsonObject, ActivityGoodsDetail.class);
            if (StringUtil.isEmpty(activityGoodsDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateActivityGoodsDetail(activityGoodsDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("ActivityGoodsDetailServiceImpl.updateActivityGoodsDetailById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询ActivityGoodsDetail
     * @Author taoye
     */
    @Override
    public ResponseJson getActivityGoodsDetail(JSONObject jsonObject) {
        try {
            ActivityGoodsDetail activityGoodsDetail = JSONObject.toJavaObject(jsonObject, ActivityGoodsDetail.class);
            if (StringUtil.isEmpty(activityGoodsDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            activityGoodsDetail = mapper.getActivityGoodsDetail(activityGoodsDetail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, activityGoodsDetail);
        } catch (Exception e) {
            LOGGER.error("ActivityGoodsDetailServiceImpl.getActivityGoodsDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}