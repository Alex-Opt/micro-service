package com.ly.mt.center.data.punch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.punch.mapper.PunchCardPointMapper;
import com.ly.mt.center.data.punch.service.PunchCardPointService;
import com.ly.mt.center.data.punch.entity.PunchCardPoint;
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
public class PunchCardPointServiceImpl extends BaseServiceImpl implements PunchCardPointService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PunchCardPointServiceImpl.class);
    @Resource
    PunchCardPointMapper mapper;

    /**
     * @Description 插入PunchCardPoint
     * @Author taoye
     */
    @Override
    public ResponseJson insertPunchCardPoint(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            mapper.insertPunchCardPoint(punchCardPoint);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.insertPunchCardPoint出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除PunchCardPoint
     * @Author taoye
     */
    @Override
    public ResponseJson deletePunchCardPointById(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            mapper.deletePunchCardPointById(punchCardPoint);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.deletePunchCardPointById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**

     * @Description 根据id更新PunchCardPoint
     * @Author taoye
     */
    @Override
    public ResponseJson updatePunchCardPointById(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            mapper.updatePunchCardPointById(punchCardPoint);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.updatePunchCardPointById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询PunchCardPoint
     * @Author taoye
     */
    @Override
    public ResponseJson getPunchCardPoint(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            mapper.getPunchCardPoint(punchCardPoint);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.getPunchCardPoint出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id查询PunchCardPoint
     * @Author taoye
     */
    @Override
    public ResponseJson getPunchCardPointById(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            mapper.getPunchCardPointById(punchCardPoint);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.getPunchCardPointById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getPunchCardPointByStatus(JSONObject jsonObject) {
        try {
            PunchCardPoint punchCardPoint = JSONObject.toJavaObject(jsonObject, PunchCardPoint.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getPunchCardPointByStatus(punchCardPoint));
        } catch (Exception e) {
            LOGGER.error("PunchCardPointServiceImpl.getPunchCardPointById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}