package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.gzg.entity.GzgPlan;
import com.ly.mt.center.data.gzg.entity.GzgRujinRelation;
import com.ly.mt.center.data.gzg.mapper.GzgPlanMapper;
import com.ly.mt.center.data.gzg.mapper.GzgRujinRelationMapper;
import com.ly.mt.center.data.gzg.service.GzgPlanService;
import com.ly.mt.center.data.gzg.service.GzgRujinRelationService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class GzgRujinRelationServiceImpl extends BaseServiceImpl implements GzgRujinRelationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgRujinRelationServiceImpl.class);
    @Resource
    GzgRujinRelationMapper mapper;

    /**
     * @Description 插入GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgRujinRelation(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgPlan = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            mapper.insertGzgRujinRelation(gzgPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.insertGzgRujinRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id删除GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgRujinRelationById(JSONObject jsonObject) {
        try {
            GzgPlan GzgPlan = JSONObject.toJavaObject(jsonObject, GzgPlan.class);
//            mapper.deleteGzgPlanById(GzgPlan);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.deleteGzgPlanById  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 根据id更新GzgPlan
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgRujinRelationById(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            mapper.updateGzgRujinRelationById(gzgRujinRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.updateGzgRujinRelationById  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询未绑定的如金柜子
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgRujinRelationByNameAndTid(JSONObject jsonObject) {
        try {
//            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            List<GzgRujinRelation> gzgRujinRelationList = mapper.getGzgRujinRelationListByNameAndTid();
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgRujinRelationList);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.getGzgRujinRelationByNameAndTid  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 查询未绑定的如金柜子
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgRujinRelationByName(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            GzgRujinRelation gzgRujinRelations = mapper.getGzgRujinRelationListByName(gzgRujinRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgRujinRelations);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.getGzgRujinRelationByName  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson getGzgRujinRelationByNameAndTname(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            GzgRujinRelation gzgRujinRelation1 = mapper.getGzgRujinRelationByNameAndTname(gzgRujinRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,gzgRujinRelation1);
        }catch (Exception e){
            LOGGER.error("GzgRujinRelationServiceImpl.getGzgRujinRelationByNameAndTname  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
    /**
     * @Description 通过 tname 查询 GzgRujinRelation
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgRujinRelationByTname(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            GzgRujinRelation gzgRujinRelation1 = mapper.getGzgRujinRelationByTname(gzgRujinRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgRujinRelation1);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.getGzgRujinRelationByTname  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * @Description 通过 tid 查询 GzgRujinRelation
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgRujinRelationByTid(JSONObject jsonObject) {
        try {
            GzgRujinRelation gzgRujinRelation = JSONObject.toJavaObject(jsonObject, GzgRujinRelation.class);
            GzgRujinRelation gzgRujinRelation1 = mapper.getGzgRujinRelationByTid(gzgRujinRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgRujinRelation1);
        } catch (Exception e) {
            LOGGER.error("GzgRujinRelationServiceImpl.getGzgRujinRelationByTid  出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}