package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.mapper.GzgPlanMapper;
import com.ly.mt.center.data.gzg.service.GzgPlanService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgPlanServiceImpl extends BaseServiceImpl implements GzgPlanService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgPlanServiceImpl.class);
    @Resource
    GzgPlanMapper mapper;

    /**
     * @Description 插入GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgPlan(JSONObject jsonObject) {
        try {
            GzgPlan gzgPlan = JSONObject.toJavaObject(jsonObject, GzgPlan.class);
//            mapper.insertGzgPlan(gzgPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgPlanServiceImpl.insertGzgPlan出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgPlanById(JSONObject jsonObject) {
        try {
            GzgPlan GzgPlan = JSONObject.toJavaObject(jsonObject, GzgPlan.class);
//            mapper.deleteGzgPlanById(GzgPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgPlanServiceImpl.deleteGzgPlanById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id更新GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgPlanById(JSONObject jsonObject) {
        try {
            GzgPlan GzgPlan = JSONObject.toJavaObject(jsonObject, GzgPlan.class);
//            mapper.updateGzgPlanById(GzgPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgPlanServiceImpl.updateGzgPlanById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据条件查询GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgPlan(JSONObject jsonObject) {
        try {
            GzgPlan gzgPlan = JSONObject.toJavaObject(jsonObject, GzgPlan.class);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, mapper.getGzgPlan(gzgPlan));
        } catch (Exception e) {
            LOGGER.error("GzgPlanServiceImpl.getGzgPlan出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}