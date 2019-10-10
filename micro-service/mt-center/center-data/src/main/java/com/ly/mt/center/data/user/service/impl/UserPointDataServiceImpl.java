package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserPointDataMapper;
import com.ly.mt.center.data.user.service.UserPointDataService;
import com.ly.mt.center.data.user.entity.UserPointData;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class UserPointDataServiceImpl extends BaseServiceImpl implements UserPointDataService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserPointDataServiceImpl.class);
    @Resource
    UserPointDataMapper mapper;

    /**
     * @Description 插入UserPointData
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserPointData(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            mapper.insertUserPointData(userPointData);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.insertUserPointData出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除UserPointData
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserPointDataById(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            mapper.deleteUserPointDataById(userPointData);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.deleteUserPointDataById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新UserPointData
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserPointDataById(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            mapper.updateUserPointDataById(userPointData);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.updateUserPointDataById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询UserPointData
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPointData(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            mapper.getUserPointData(userPointData);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.getUserPointData出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询UserPointData
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPointDataById(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            mapper.getUserPointDataById(userPointData);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.getUserPointDataById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserPointDataByUserId(JSONObject jsonObject) {
        try {
            UserPointData userPointData = JSONObject.toJavaObject(jsonObject, UserPointData.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getUserPointDataByUserId(userPointData));
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.getUserPointDataByUserId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserPointDataByUserIdAndToday(JSONObject jsonObject) {
        try {
            String userId= jsonObject.getString("user_id");
            String todayStartTime = jsonObject.getString("today_start_time");
            String todayEndTime = jsonObject.getString("today_end_time");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getUserPointDataByUserIdAndToday(userId,todayStartTime,todayEndTime));
        } catch (Exception e) {
            LOGGER.error("UserPointDataServiceImpl.getUserPointDataByUserId出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}