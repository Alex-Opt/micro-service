package com.ly.mt.center.data.point.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.point.mapper.PointMallRuleDetailMapper;
import com.ly.mt.center.data.point.service.PointMallRuleDetailService;
import com.ly.mt.center.data.point.entity.PointMallRuleDetail;
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
public class PointMallRuleDetailServiceImpl extends BaseServiceImpl implements PointMallRuleDetailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PointMallRuleDetailServiceImpl.class);
    @Resource
    PointMallRuleDetailMapper mapper;

    /**
     * @Description 保存PointMallRuleDetail
     * @Author taoye
     */
    @Override
    public ResponseJson insertPointMallRuleDetail(JSONObject jsonObject) {
        try {
            PointMallRuleDetail pointMallRuleDetail = JSONObject.toJavaObject(jsonObject, PointMallRuleDetail.class);
            if (StringUtil.isEmpty(pointMallRuleDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertPointMallRuleDetail(pointMallRuleDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleDetailServiceImpl.insertPointMallRuleDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除PointMallRuleDetail
     * @Author taoye
     */
    @Override
    public ResponseJson deletePointMallRuleDetail(JSONObject jsonObject) {
        try {
            PointMallRuleDetail pointMallRuleDetail = JSONObject.toJavaObject(jsonObject, PointMallRuleDetail.class);
            if (StringUtil.isEmpty(pointMallRuleDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deletePointMallRuleDetail(pointMallRuleDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleDetailServiceImpl.deletePointMallRuleDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新PointMallRuleDetail
     * @Author taoye
     */
    @Override
    public ResponseJson updatePointMallRuleDetail(JSONObject jsonObject) {
        try {
            PointMallRuleDetail pointMallRuleDetail = JSONObject.toJavaObject(jsonObject, PointMallRuleDetail.class);
            if (StringUtil.isEmpty(pointMallRuleDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updatePointMallRuleDetail(pointMallRuleDetail);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleDetailServiceImpl.updatePointMallRuleDetailById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询PointMallRuleDetail
     * @Author taoye
     */
    @Override
    public ResponseJson getPointMallRuleDetail(JSONObject jsonObject) {
        try {
            PointMallRuleDetail pointMallRuleDetail = JSONObject.toJavaObject(jsonObject, PointMallRuleDetail.class);
            if (StringUtil.isEmpty(pointMallRuleDetail.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            pointMallRuleDetail = mapper.getPointMallRuleDetail(pointMallRuleDetail);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, pointMallRuleDetail);
        } catch (Exception e) {
            LOGGER.error("PointMallRuleDetailServiceImpl.getPointMallRuleDetail出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}