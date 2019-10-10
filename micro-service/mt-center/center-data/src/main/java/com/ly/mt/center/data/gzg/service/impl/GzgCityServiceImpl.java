package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgCityMapper;
import com.ly.mt.center.data.gzg.service.GzgCityService;
import com.ly.mt.center.data.gzg.entity.GzgCity;
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
public class GzgCityServiceImpl extends BaseServiceImpl implements GzgCityService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgCityServiceImpl.class);
    @Resource
    GzgCityMapper mapper;

    /**
     * @Description 保存GzgCity
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgCity(JSONObject jsonObject) {
        try {
            GzgCity gzgCity = JSONObject.toJavaObject(jsonObject, GzgCity.class);
            if (StringUtil.isEmpty(gzgCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgCity(gzgCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCityServiceImpl.insertGzgCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgCity
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgCity(JSONObject jsonObject) {
        try {
            GzgCity gzgCity = JSONObject.toJavaObject(jsonObject, GzgCity.class);
            if (StringUtil.isEmpty(gzgCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgCity(gzgCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCityServiceImpl.deleteGzgCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgCity
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgCity(JSONObject jsonObject) {
        try {
            GzgCity gzgCity = JSONObject.toJavaObject(jsonObject, GzgCity.class);
            if (StringUtil.isEmpty(gzgCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgCity(gzgCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgCityServiceImpl.updateGzgCityById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgCity
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgCity(JSONObject jsonObject) {
        try {
            GzgCity gzgCity = JSONObject.toJavaObject(jsonObject, GzgCity.class);
            if (StringUtil.isEmpty(gzgCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgCity = mapper.getGzgCity(gzgCity);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgCity);
        } catch (Exception e) {
            LOGGER.error("GzgCityServiceImpl.getGzgCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}