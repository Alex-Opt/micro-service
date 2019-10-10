package com.ly.mt.center.data.vote.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.vote.mapper.VoteConfigMapper;
import com.ly.mt.center.data.vote.service.VoteConfigService;
import com.ly.mt.center.data.vote.entity.VoteConfig;
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
public class VoteConfigServiceImpl extends BaseServiceImpl implements VoteConfigService {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoteConfigServiceImpl.class);
    @Resource
    VoteConfigMapper mapper;

    /**
     * @Description 保存VoteConfig
     * @Author taoye
     */
    @Override
    public ResponseJson insertVoteConfig(JSONObject jsonObject) {
        try {
            VoteConfig voteConfig = JSONObject.toJavaObject(jsonObject, VoteConfig.class);
            if (StringUtil.isEmpty(voteConfig.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertVoteConfig(voteConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteConfigServiceImpl.insertVoteConfig出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除VoteConfig
     * @Author taoye
     */
    @Override
    public ResponseJson deleteVoteConfig(JSONObject jsonObject) {
        try {
            VoteConfig voteConfig = JSONObject.toJavaObject(jsonObject, VoteConfig.class);
            if (StringUtil.isEmpty(voteConfig.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteVoteConfig(voteConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteConfigServiceImpl.deleteVoteConfig出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新VoteConfig
     * @Author taoye
     */
    @Override
    public ResponseJson updateVoteConfig(JSONObject jsonObject) {
        try {
            VoteConfig voteConfig = JSONObject.toJavaObject(jsonObject, VoteConfig.class);
            if (StringUtil.isEmpty(voteConfig.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateVoteConfig(voteConfig);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteConfigServiceImpl.updateVoteConfigById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询VoteConfig
     * @Author taoye
     */
    @Override
    public ResponseJson getVoteConfig(JSONObject jsonObject) {
        try {
            VoteConfig voteConfig = JSONObject.toJavaObject(jsonObject, VoteConfig.class);
            if (StringUtil.isEmpty(voteConfig.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            voteConfig = mapper.getVoteConfig(voteConfig);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, voteConfig);
        } catch (Exception e) {
            LOGGER.error("VoteConfigServiceImpl.getVoteConfig出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}