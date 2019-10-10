package com.ly.mt.center.data.gzg.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.gzg.mapper.GzgOrderPayMapper;
import com.ly.mt.center.data.gzg.service.GzgOrderPayService;
import com.ly.mt.center.data.gzg.entity.GzgOrderPay;
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
public class GzgOrderPayServiceImpl extends BaseServiceImpl implements GzgOrderPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(GzgOrderPayServiceImpl.class);
    @Resource
    GzgOrderPayMapper mapper;

    /**
     * @Description 保存GzgOrderPay
     * @Author taoye
     */
    @Override
    public ResponseJson insertGzgOrderPay(JSONObject jsonObject) {
        try {
            GzgOrderPay gzgOrderPay = JSONObject.toJavaObject(jsonObject, GzgOrderPay.class);
            if (StringUtil.isEmpty(gzgOrderPay.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertGzgOrderPay(gzgOrderPay);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderPayServiceImpl.insertGzgOrderPay出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除GzgOrderPay
     * @Author taoye
     */
    @Override
    public ResponseJson deleteGzgOrderPay(JSONObject jsonObject) {
        try {
            GzgOrderPay gzgOrderPay = JSONObject.toJavaObject(jsonObject, GzgOrderPay.class);
            if (StringUtil.isEmpty(gzgOrderPay.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteGzgOrderPay(gzgOrderPay);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderPayServiceImpl.deleteGzgOrderPay出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新GzgOrderPay
     * @Author taoye
     */
    @Override
    public ResponseJson updateGzgOrderPay(JSONObject jsonObject) {
        try {
            GzgOrderPay gzgOrderPay = JSONObject.toJavaObject(jsonObject, GzgOrderPay.class);
            if (StringUtil.isEmpty(gzgOrderPay.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateGzgOrderPay(gzgOrderPay);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("GzgOrderPayServiceImpl.updateGzgOrderPayById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询GzgOrderPay
     * @Author taoye
     */
    @Override
    public ResponseJson getGzgOrderPay(JSONObject jsonObject) {
        try {
            GzgOrderPay gzgOrderPay = JSONObject.toJavaObject(jsonObject, GzgOrderPay.class);
            if (StringUtil.isEmpty(gzgOrderPay.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            gzgOrderPay = mapper.getGzgOrderPay(gzgOrderPay);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, gzgOrderPay);
        } catch (Exception e) {
            LOGGER.error("GzgOrderPayServiceImpl.getGzgOrderPay出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}