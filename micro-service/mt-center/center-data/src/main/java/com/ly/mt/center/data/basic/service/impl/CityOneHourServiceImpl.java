package com.ly.mt.center.data.basic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.basic.mapper.CityOneHourMapper;
import com.ly.mt.center.data.basic.service.CityOneHourService;
import com.ly.mt.center.data.basic.entity.CityOneHour;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CityOneHourServiceImpl extends BaseServiceImpl implements CityOneHourService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CityOneHourServiceImpl.class);
    @Resource
    CityOneHourMapper mapper;

    /**
     * @Description 保存CityOneHour
     * @Author taoye
     */
    @Override
    public ResponseJson insertCityOneHour(JSONObject jsonObject) {
        try {
            CityOneHour cityOneHour = JSONObject.toJavaObject(jsonObject, CityOneHour.class);
            if (StringUtil.isEmpty(cityOneHour.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertCityOneHour(cityOneHour);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CityOneHourServiceImpl.insertCityOneHour出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除CityOneHour
     * @Author taoye
     */
    @Override
    public ResponseJson deleteCityOneHour(JSONObject jsonObject) {
        try {
            CityOneHour cityOneHour = JSONObject.toJavaObject(jsonObject, CityOneHour.class);
            if (StringUtil.isEmpty(cityOneHour.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteCityOneHour(cityOneHour);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CityOneHourServiceImpl.deleteCityOneHour出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新CityOneHour
     * @Author taoye
     */
    @Override
    public ResponseJson updateCityOneHour(JSONObject jsonObject) {
        try {
            CityOneHour cityOneHour = JSONObject.toJavaObject(jsonObject, CityOneHour.class);
            if (StringUtil.isEmpty(cityOneHour.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateCityOneHour(cityOneHour);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CityOneHourServiceImpl.updateCityOneHourById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询CityOneHour
     * @Author taoye
     */
    @Override
    public ResponseJson getCityOneHour(JSONObject jsonObject) {
        try {
            CityOneHour cityOneHour = JSONObject.toJavaObject(jsonObject, CityOneHour.class);
            if (StringUtil.isEmpty(cityOneHour.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            cityOneHour = mapper.getCityOneHour(cityOneHour);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cityOneHour);
        } catch (Exception e) {
            LOGGER.error("CityOneHourServiceImpl.getCityOneHour出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 是否开通一小时达
     * @param jsonObject key="areaId" {@link Long} 区域id
     * @return areaOpen  true已经开通 false 未开通
     */
    @Override
    public ResponseJson findByAreaId(JSONObject jsonObject) {
        Long areaId = jsonObject.getLong("areaId");
        List<CityOneHour>  list = mapper.findByAreaId(areaId);
        JSONObject result = new JSONObject(1);
        if (list != null && list.size()>0){
            result.put("areaOpen",true);
        }else {
            result.put("areaOpen",false);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,result);
    }
}