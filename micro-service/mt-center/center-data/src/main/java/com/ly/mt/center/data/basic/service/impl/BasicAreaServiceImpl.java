package com.ly.mt.center.data.basic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.basic.mapper.BasicAreaMapper;
import com.ly.mt.center.data.basic.service.BasicAreaService;
import com.ly.mt.center.data.basic.entity.BasicArea;
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
public class BasicAreaServiceImpl extends BaseServiceImpl implements BasicAreaService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BasicAreaServiceImpl.class);
    @Resource
    BasicAreaMapper mapper;

    /**
     * @Description 保存BasicArea
     * @Author taoye
     */
    @Override
    public ResponseJson insertBasicArea(JSONObject jsonObject) {
        try {
            BasicArea basicArea = JSONObject.toJavaObject(jsonObject, BasicArea.class);
            if (StringUtil.isEmpty(basicArea.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertBasicArea(basicArea);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BasicAreaServiceImpl.insertBasicArea出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除BasicArea
     * @Author taoye
     */
    @Override
    public ResponseJson deleteBasicArea(JSONObject jsonObject) {
        try {
            BasicArea basicArea = JSONObject.toJavaObject(jsonObject, BasicArea.class);
            if (StringUtil.isEmpty(basicArea.getId()) && StringUtil.isEmpty(basicArea.getM_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteBasicArea(basicArea);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BasicAreaServiceImpl.deleteBasicArea出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新BasicArea
     * @Author taoye
     */
    @Override
    public ResponseJson updateBasicArea(JSONObject jsonObject) {
        try {
            BasicArea basicArea = JSONObject.toJavaObject(jsonObject, BasicArea.class);
            if (StringUtil.isEmpty(basicArea.getId()) && StringUtil.isEmpty(basicArea.getM_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateBasicArea(basicArea);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("BasicAreaServiceImpl.updateBasicAreaById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询BasicArea
     * @Author taoye
     */
    @Override
    public ResponseJson getBasicArea(JSONObject jsonObject) {
        try {
            BasicArea basicArea = JSONObject.toJavaObject(jsonObject, BasicArea.class);
            if (StringUtil.isEmpty(basicArea.getId())
                    && StringUtil.isEmpty(basicArea.getM_id())
                    && StringUtil.isEmpty(basicArea.getPid())
                    && StringUtil.isEmpty(basicArea.getName())
                    && StringUtil.isEmpty(basicArea.getM_name())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            basicArea = mapper.getBasicArea(basicArea);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, basicArea);
        } catch (Exception e) {
            LOGGER.error("BasicAreaServiceImpl.getBasicArea出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}