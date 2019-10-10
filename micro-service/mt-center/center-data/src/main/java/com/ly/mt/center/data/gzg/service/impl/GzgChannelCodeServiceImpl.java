package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgChannelCodeMapper;
import com.ly.mt.center.data.gzg.service.GzgChannelCodeService;
import com.ly.mt.center.data.gzg.entity.GzgChannelCode;
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
public class GzgChannelCodeServiceImpl extends BaseServiceImpl implements GzgChannelCodeService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgChannelCodeServiceImpl.class);
    @Resource
    GzgChannelCodeMapper mapper;

    /**
     * @Description 保存GzgChannelCode
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgChannelCode(JSONObject jsonObject) {
        try {
            GzgChannelCode gzgChannelCode = JSONObject.toJavaObject(jsonObject, GzgChannelCode.class);
            if (StringUtil.isEmpty(gzgChannelCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgChannelCode(gzgChannelCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgChannelCodeServiceImpl.insertGzgChannelCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgChannelCode
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgChannelCode(JSONObject jsonObject) {
        try {
            GzgChannelCode gzgChannelCode = JSONObject.toJavaObject(jsonObject, GzgChannelCode.class);
            if (StringUtil.isEmpty(gzgChannelCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgChannelCode(gzgChannelCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgChannelCodeServiceImpl.deleteGzgChannelCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgChannelCode
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgChannelCode(JSONObject jsonObject) {
        try {
            GzgChannelCode gzgChannelCode = JSONObject.toJavaObject(jsonObject, GzgChannelCode.class);
            if (StringUtil.isEmpty(gzgChannelCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgChannelCode(gzgChannelCode);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgChannelCodeServiceImpl.updateGzgChannelCodeById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgChannelCode
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgChannelCode(JSONObject jsonObject) {
        try {
            GzgChannelCode gzgChannelCode = JSONObject.toJavaObject(jsonObject, GzgChannelCode.class);
            if (StringUtil.isEmpty(gzgChannelCode.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgChannelCode = mapper.getGzgChannelCode(gzgChannelCode);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgChannelCode);
        } catch (Exception e) {
            LOGGER.error("GzgChannelCodeServiceImpl.getGzgChannelCode出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}