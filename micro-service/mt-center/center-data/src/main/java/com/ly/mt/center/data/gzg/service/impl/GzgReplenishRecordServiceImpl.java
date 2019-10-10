package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgReplenishRecordMapper;
import com.ly.mt.center.data.gzg.service.GzgReplenishRecordService;
import com.ly.mt.center.data.gzg.entity.GzgReplenishRecord;
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
public class GzgReplenishRecordServiceImpl extends BaseServiceImpl implements GzgReplenishRecordService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgReplenishRecordServiceImpl.class);
    @Resource
    GzgReplenishRecordMapper mapper;

    /**
     * @Description 保存GzgReplenishRecord
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgReplenishRecord(JSONObject jsonObject) {
        try {
            GzgReplenishRecord gzgReplenishRecord = JSONObject.toJavaObject(jsonObject, GzgReplenishRecord.class);
            if (StringUtil.isEmpty(gzgReplenishRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgReplenishRecord(gzgReplenishRecord);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishRecordServiceImpl.insertGzgReplenishRecord出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgReplenishRecord
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgReplenishRecord(JSONObject jsonObject) {
        try {
            GzgReplenishRecord gzgReplenishRecord = JSONObject.toJavaObject(jsonObject, GzgReplenishRecord.class);
            if (StringUtil.isEmpty(gzgReplenishRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgReplenishRecord(gzgReplenishRecord);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishRecordServiceImpl.deleteGzgReplenishRecord出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgReplenishRecord
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgReplenishRecord(JSONObject jsonObject) {
        try {
            GzgReplenishRecord gzgReplenishRecord = JSONObject.toJavaObject(jsonObject, GzgReplenishRecord.class);
            if (StringUtil.isEmpty(gzgReplenishRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgReplenishRecord(gzgReplenishRecord);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishRecordServiceImpl.updateGzgReplenishRecordById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgReplenishRecord
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgReplenishRecord(JSONObject jsonObject) {
        try {
            GzgReplenishRecord gzgReplenishRecord = JSONObject.toJavaObject(jsonObject, GzgReplenishRecord.class);
            if (StringUtil.isEmpty(gzgReplenishRecord.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgReplenishRecord = mapper.getGzgReplenishRecord(gzgReplenishRecord);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgReplenishRecord);
        } catch (Exception e) {
            LOGGER.error("GzgReplenishRecordServiceImpl.getGzgReplenishRecord出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}