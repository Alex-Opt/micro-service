package com.ly.mt.center.data.load.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.load.mapper.LoadRunnerPersonnelsMapper;
import com.ly.mt.center.data.load.service.LoadRunnerPersonnelsService;
import com.ly.mt.center.data.load.entity.LoadRunnerPersonnels;
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
public class LoadRunnerPersonnelsServiceImpl extends BaseServiceImpl implements LoadRunnerPersonnelsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoadRunnerPersonnelsServiceImpl.class);
    @Resource
    LoadRunnerPersonnelsMapper mapper;

    /**
     * @Description 保存LoadRunnerPersonnels
     * @Author taoye
     */
    @Override
    public ResponseJson insertLoadRunnerPersonnels(JSONObject jsonObject) {
        try {
            LoadRunnerPersonnels loadRunnerPersonnels = JSONObject.toJavaObject(jsonObject, LoadRunnerPersonnels.class);
            if (StringUtil.isEmpty(loadRunnerPersonnels.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLoadRunnerPersonnels(loadRunnerPersonnels);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LoadRunnerPersonnelsServiceImpl.insertLoadRunnerPersonnels出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LoadRunnerPersonnels
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLoadRunnerPersonnels(JSONObject jsonObject) {
        try {
            LoadRunnerPersonnels loadRunnerPersonnels = JSONObject.toJavaObject(jsonObject, LoadRunnerPersonnels.class);
            if (StringUtil.isEmpty(loadRunnerPersonnels.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLoadRunnerPersonnels(loadRunnerPersonnels);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LoadRunnerPersonnelsServiceImpl.deleteLoadRunnerPersonnels出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LoadRunnerPersonnels
     * @Author taoye
     */
    @Override
    public ResponseJson updateLoadRunnerPersonnels(JSONObject jsonObject) {
        try {
            LoadRunnerPersonnels loadRunnerPersonnels = JSONObject.toJavaObject(jsonObject, LoadRunnerPersonnels.class);
            if (StringUtil.isEmpty(loadRunnerPersonnels.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLoadRunnerPersonnels(loadRunnerPersonnels);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LoadRunnerPersonnelsServiceImpl.updateLoadRunnerPersonnelsById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LoadRunnerPersonnels
     * @Author taoye
     */
    @Override
    public ResponseJson getLoadRunnerPersonnels(JSONObject jsonObject) {
        try {
            LoadRunnerPersonnels loadRunnerPersonnels = JSONObject.toJavaObject(jsonObject, LoadRunnerPersonnels.class);
            if (StringUtil.isEmpty(loadRunnerPersonnels.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            loadRunnerPersonnels = mapper.getLoadRunnerPersonnels(loadRunnerPersonnels);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, loadRunnerPersonnels);
        } catch (Exception e) {
            LOGGER.error("LoadRunnerPersonnelsServiceImpl.getLoadRunnerPersonnels出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}