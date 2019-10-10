package com.ly.mt.center.data.lode.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.lode.mapper.LodeRunnerTreesMapper;
import com.ly.mt.center.data.lode.service.LodeRunnerTreesService;
import com.ly.mt.center.data.lode.entity.LodeRunnerTrees;
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
public class LodeRunnerTreesServiceImpl extends BaseServiceImpl implements LodeRunnerTreesService {
    private final static Logger LOGGER = LoggerFactory.getLogger(LodeRunnerTreesServiceImpl.class);
    @Resource
    LodeRunnerTreesMapper mapper;

    /**
     * @Description 保存LodeRunnerTrees
     * @Author taoye
     */
    @Override
    public ResponseJson insertLodeRunnerTrees(JSONObject jsonObject) {
        try {
            LodeRunnerTrees lodeRunnerTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerTrees.class);
            if (StringUtil.isEmpty(lodeRunnerTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertLodeRunnerTrees(lodeRunnerTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerTreesServiceImpl.insertLodeRunnerTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除LodeRunnerTrees
     * @Author taoye
     */
    @Override
    public ResponseJson deleteLodeRunnerTrees(JSONObject jsonObject) {
        try {
            LodeRunnerTrees lodeRunnerTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerTrees.class);
            if (StringUtil.isEmpty(lodeRunnerTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteLodeRunnerTrees(lodeRunnerTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerTreesServiceImpl.deleteLodeRunnerTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新LodeRunnerTrees
     * @Author taoye
     */
    @Override
    public ResponseJson updateLodeRunnerTrees(JSONObject jsonObject) {
        try {
            LodeRunnerTrees lodeRunnerTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerTrees.class);
            if (StringUtil.isEmpty(lodeRunnerTrees.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateLodeRunnerTrees(lodeRunnerTrees);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerTreesServiceImpl.updateLodeRunnerTreesById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询LodeRunnerTrees
     * @Author taoye
     */
    @Override
    public ResponseJson getLodeRunnerTreesByUserId(JSONObject jsonObject) {
        try {
            LodeRunnerTrees lodeRunnerTrees = JSONObject.toJavaObject(jsonObject, LodeRunnerTrees.class);
            if (StringUtil.isEmpty(lodeRunnerTrees.getUser_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            lodeRunnerTrees = mapper.getLodeRunnerTrees(lodeRunnerTrees);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, lodeRunnerTrees);
        } catch (Exception e) {
            LOGGER.error("LodeRunnerTreesServiceImpl.getLodeRunnerTrees出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}