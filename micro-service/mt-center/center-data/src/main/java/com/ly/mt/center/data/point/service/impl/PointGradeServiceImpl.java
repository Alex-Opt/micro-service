package com.ly.mt.center.data.point.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.point.mapper.PointGradeMapper;
import com.ly.mt.center.data.point.service.PointGradeService;
import com.ly.mt.center.data.point.entity.PointGrade;
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
public class PointGradeServiceImpl extends BaseServiceImpl implements PointGradeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PointGradeServiceImpl.class);
    @Resource
    PointGradeMapper mapper;

    /**
     * @Description 插入PointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson insertPointGrade(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            mapper.insertPointGrade(pointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.insertPointGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除PointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson deletePointGradeById(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            mapper.deletePointGradeById(pointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.deletePointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新PointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson updatePointGradeById(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            mapper.updatePointGradeById(pointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.updatePointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询PointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getPointGrade(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            mapper.getPointGrade(pointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.getPointGrade出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询PointGrade
     * @Author taoye
     */
    @Override
    public ResponseJson getPointGradeById(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            mapper.getPointGradeById(pointGrade);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.getPointGradeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getPointGradeByScore(JSONObject jsonObject) {
        try {
            PointGrade pointGrade = JSONObject.toJavaObject(jsonObject, PointGrade.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getPointGradeByScore(pointGrade));
        } catch (Exception e) {
            LOGGER.error("PointGradeServiceImpl.getPointGradeByScore出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}