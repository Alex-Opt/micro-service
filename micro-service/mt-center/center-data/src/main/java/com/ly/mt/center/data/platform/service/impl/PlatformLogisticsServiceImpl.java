package com.ly.mt.center.data.platform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.platform.mapper.PlatformLogisticsMapper;
import com.ly.mt.center.data.platform.service.PlatformLogisticsService;
import com.ly.mt.center.data.platform.entity.PlatformLogistics;
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
public class PlatformLogisticsServiceImpl extends BaseServiceImpl implements PlatformLogisticsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PlatformLogisticsServiceImpl.class);
    @Resource
    PlatformLogisticsMapper mapper;

    /**
     * @Description 保存PlatformLogistics
     * @Author taoye
     */
    @Override
    public ResponseJson insertPlatformLogistics(JSONObject jsonObject) {
        try {
            PlatformLogistics platformLogistics = JSONObject.toJavaObject(jsonObject, PlatformLogistics.class);
            if (StringUtil.isEmpty(platformLogistics.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertPlatformLogistics(platformLogistics);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PlatformLogisticsServiceImpl.insertPlatformLogistics出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除PlatformLogistics
     * @Author taoye
     */
    @Override
    public ResponseJson deletePlatformLogistics(JSONObject jsonObject) {
        try {
            PlatformLogistics platformLogistics = JSONObject.toJavaObject(jsonObject, PlatformLogistics.class);
            if (StringUtil.isEmpty(platformLogistics.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deletePlatformLogistics(platformLogistics);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PlatformLogisticsServiceImpl.deletePlatformLogistics出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新PlatformLogistics
     * @Author taoye
     */
    @Override
    public ResponseJson updatePlatformLogistics(JSONObject jsonObject) {
        try {
            PlatformLogistics platformLogistics = JSONObject.toJavaObject(jsonObject, PlatformLogistics.class);
            if (StringUtil.isEmpty(platformLogistics.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updatePlatformLogistics(platformLogistics);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PlatformLogisticsServiceImpl.updatePlatformLogisticsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询PlatformLogistics
     * @Author taoye
     */
    @Override
    public ResponseJson getPlatformLogistics(JSONObject jsonObject) {
        try {
            PlatformLogistics platformLogistics = JSONObject.toJavaObject(jsonObject, PlatformLogistics.class);
            if (StringUtil.isEmpty(platformLogistics.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            platformLogistics = mapper.getPlatformLogistics(platformLogistics);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, platformLogistics);
        } catch (Exception e) {
            LOGGER.error("PlatformLogisticsServiceImpl.getPlatformLogistics出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}