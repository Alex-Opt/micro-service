package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgDeviceCheckLogMapper;
import com.ly.mt.center.data.gzg.service.GzgDeviceCheckLogService;
import com.ly.mt.center.data.gzg.entity.GzgDeviceCheckLog;
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
public class GzgDeviceCheckLogServiceImpl extends BaseServiceImpl implements GzgDeviceCheckLogService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgDeviceCheckLogServiceImpl.class);
    @Resource
    GzgDeviceCheckLogMapper mapper;

    /**
     * @Description 保存GzgDeviceCheckLog
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgDeviceCheckLog(JSONObject jsonObject) {
        try {
            GzgDeviceCheckLog gzgDeviceCheckLog = JSONObject.toJavaObject(jsonObject, GzgDeviceCheckLog.class);
            if (StringUtil.isEmpty(gzgDeviceCheckLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgDeviceCheckLog(gzgDeviceCheckLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgDeviceCheckLogServiceImpl.insertGzgDeviceCheckLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgDeviceCheckLog
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgDeviceCheckLog(JSONObject jsonObject) {
        try {
            GzgDeviceCheckLog gzgDeviceCheckLog = JSONObject.toJavaObject(jsonObject, GzgDeviceCheckLog.class);
            if (StringUtil.isEmpty(gzgDeviceCheckLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgDeviceCheckLog(gzgDeviceCheckLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgDeviceCheckLogServiceImpl.deleteGzgDeviceCheckLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgDeviceCheckLog
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgDeviceCheckLog(JSONObject jsonObject) {
        try {
            GzgDeviceCheckLog gzgDeviceCheckLog = JSONObject.toJavaObject(jsonObject, GzgDeviceCheckLog.class);
            if (StringUtil.isEmpty(gzgDeviceCheckLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgDeviceCheckLog(gzgDeviceCheckLog);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgDeviceCheckLogServiceImpl.updateGzgDeviceCheckLogById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgDeviceCheckLog
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgDeviceCheckLog(JSONObject jsonObject) {
        try {
            GzgDeviceCheckLog gzgDeviceCheckLog = JSONObject.toJavaObject(jsonObject, GzgDeviceCheckLog.class);
            if (StringUtil.isEmpty(gzgDeviceCheckLog.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgDeviceCheckLog = mapper.getGzgDeviceCheckLog(gzgDeviceCheckLog);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgDeviceCheckLog);
        } catch (Exception e) {
            LOGGER.error("GzgDeviceCheckLogServiceImpl.getGzgDeviceCheckLog出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}