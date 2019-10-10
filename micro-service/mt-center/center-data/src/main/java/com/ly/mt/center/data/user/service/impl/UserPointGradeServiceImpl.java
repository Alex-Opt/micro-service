package com.ly.mt.center.data.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.user.mapper.UserPointGradeMapper;
import com.ly.mt.center.data.user.service.UserPointGradeService;
import com.ly.mt.center.data.user.entity.UserPointGrade;
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
public class UserPointGradeServiceImpl extends BaseServiceImpl implements UserPointGradeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserPointGradeServiceImpl.class);
    @Resource
    UserPointGradeMapper mapper;

    /**
     * @Description 插入UserPointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson insertUserPointGrade(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            mapper.insertUserPointGrade(userPointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.insertUserPointGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除UserPointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson deleteUserPointGradeById(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            mapper.deleteUserPointGradeById(userPointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.deleteUserPointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新UserPointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson updateUserPointGradeById(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            mapper.updateUserPointGradeById(userPointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.updateUserPointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询UserPointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPointGrade(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            mapper.getUserPointGrade(userPointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.getUserPointGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询UserPointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getUserPointGradeById(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            mapper.getUserPointGradeById(userPointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.getUserPointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getUserPointGradeByUserId(JSONObject jsonObject) {
        try {
            UserPointGrade userPointGrade = JSONObject.toJavaObject(jsonObject, UserPointGrade.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getUserPointGradeByUserId(userPointGrade));
        } catch (Exception e) {
            LOGGER.error("UserPointGradeServiceImpl.getUserPointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}