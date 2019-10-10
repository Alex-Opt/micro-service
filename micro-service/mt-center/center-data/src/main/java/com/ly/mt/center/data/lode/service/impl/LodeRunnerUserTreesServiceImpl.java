package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerUserTreesMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerUserTreesService;
import com.ly.mt.center.data.lode.entity.LodeRunnerUserTrees;
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
public class LodeRunnerUserTreesServiceImpl extends BaseServiceImpl implements LodeRunnerUserTreesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerUserTreesServiceImpl.class);
    @Resource
    LodeRunnerUserTreesMapper mapper;

    /**
     * @Description 保存LodeRunnerUserTrees
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerUserTrees(JSONObject jsonObject) {
        try {
            LodeRunnerUserTrees lodeRunnerUserTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerUserTrees.class);
            if (StringUtil.isEmpty(lodeRunnerUserTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerUserTrees(lodeRunnerUserTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserTreesServiceImpl.insertLodeRunnerUserTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerUserTrees
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerUserTrees(JSONObject jsonObject) {
        try {
            LodeRunnerUserTrees lodeRunnerUserTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerUserTrees.class);
            if (StringUtil.isEmpty(lodeRunnerUserTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerUserTrees(lodeRunnerUserTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserTreesServiceImpl.deleteLodeRunnerUserTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerUserTrees
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerUserTrees(JSONObject jsonObject) {
        try {
            LodeRunnerUserTrees lodeRunnerUserTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerUserTrees.class);
            if (StringUtil.isEmpty(lodeRunnerUserTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerUserTrees(lodeRunnerUserTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserTreesServiceImpl.updateLodeRunnerUserTreesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerUserTrees
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerUserTrees(JSONObject jsonObject) {
        try {
            LodeRunnerUserTrees lodeRunnerUserTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerUserTrees.class);
            if (StringUtil.isEmpty(lodeRunnerUserTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerUserTrees = mapper.getLodeRunnerUserTrees(lodeRunnerUserTrees);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerUserTrees);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerUserTreesServiceImpl.getLodeRunnerUserTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}