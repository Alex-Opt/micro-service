package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgHotelUserRelationMapper;
import com.ly.mt.center.data.gzg.service.GzgHotelUserRelationService;
import com.ly.mt.center.data.gzg.entity.GzgHotelUserRelation;
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
public class GzgHotelUserRelationServiceImpl extends BaseServiceImpl implements GzgHotelUserRelationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgHotelUserRelationServiceImpl.class);
    @Resource
    GzgHotelUserRelationMapper mapper;

    /**
     * @Description 保存GzgHotelUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgHotelUserRelation(JSONObject jsonObject) {
        try {
            GzgHotelUserRelation gzgHotelUserRelation = JSONObject.toJavaObject(jsonObject, GzgHotelUserRelation.class);
            if (StringUtil.isEmpty(gzgHotelUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgHotelUserRelation(gzgHotelUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelUserRelationServiceImpl.insertGzgHotelUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgHotelUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgHotelUserRelation(JSONObject jsonObject) {
        try {
            GzgHotelUserRelation gzgHotelUserRelation = JSONObject.toJavaObject(jsonObject, GzgHotelUserRelation.class);
            if (StringUtil.isEmpty(gzgHotelUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgHotelUserRelation(gzgHotelUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelUserRelationServiceImpl.deleteGzgHotelUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgHotelUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgHotelUserRelation(JSONObject jsonObject) {
        try {
            GzgHotelUserRelation gzgHotelUserRelation = JSONObject.toJavaObject(jsonObject, GzgHotelUserRelation.class);
            if (StringUtil.isEmpty(gzgHotelUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgHotelUserRelation(gzgHotelUserRelation);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgHotelUserRelationServiceImpl.updateGzgHotelUserRelationById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgHotelUserRelation
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgHotelUserRelation(JSONObject jsonObject) {
        try {
            GzgHotelUserRelation gzgHotelUserRelation = JSONObject.toJavaObject(jsonObject, GzgHotelUserRelation.class);
            if (StringUtil.isEmpty(gzgHotelUserRelation.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgHotelUserRelation = mapper.getGzgHotelUserRelation(gzgHotelUserRelation);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgHotelUserRelation);
        } catch (Exception e) {
            LOGGER.error("GzgHotelUserRelationServiceImpl.getGzgHotelUserRelation出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}