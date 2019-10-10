package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgBUserRelationMapper;
import com.ly.mt.center.data.gzg.service.GzgBUserRelationService;
import com.ly.mt.center.data.gzg.entity.GzgBUserRelation;
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
public class GzgBUserRelationServiceImpl extends BaseServiceImpl implements GzgBUserRelationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgBUserRelationServiceImpl.class);
    @Resource
    GzgBUserRelationMapper mapper;

    /**
     * @Description 保存GzgBUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgBUserRelation(JSONObject jsonObject) {
        try {
            GzgBUserRelation gzgBUserRelation = JSONObject.toJavaObject(jsonObject, GzgBUserRelation.class);
            if (StringUtil.isEmpty(gzgBUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgBUserRelation(gzgBUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserRelationServiceImpl.insertGzgBUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgBUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgBUserRelation(JSONObject jsonObject) {
        try {
            GzgBUserRelation gzgBUserRelation = JSONObject.toJavaObject(jsonObject, GzgBUserRelation.class);
            if (StringUtil.isEmpty(gzgBUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgBUserRelation(gzgBUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserRelationServiceImpl.deleteGzgBUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgBUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgBUserRelation(JSONObject jsonObject) {
        try {
            GzgBUserRelation gzgBUserRelation = JSONObject.toJavaObject(jsonObject, GzgBUserRelation.class);
            if (StringUtil.isEmpty(gzgBUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgBUserRelation(gzgBUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgBUserRelationServiceImpl.updateGzgBUserRelationById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgBUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgBUserRelation(JSONObject jsonObject) {
        try {
            GzgBUserRelation gzgBUserRelation = JSONObject.toJavaObject(jsonObject, GzgBUserRelation.class);
            if (StringUtil.isEmpty(gzgBUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgBUserRelation = mapper.getGzgBUserRelation(gzgBUserRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgBUserRelation);
        } catch (Exception e) {
            LOGGER.error("GzgBUserRelationServiceImpl.getGzgBUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}