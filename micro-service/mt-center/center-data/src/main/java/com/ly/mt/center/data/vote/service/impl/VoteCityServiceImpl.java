package com.ly.mt.center.data.vote.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.vote.mapper.VoteCityMapper;
import com.ly.mt.center.data.vote.service.VoteCityService;
import com.ly.mt.center.data.vote.entity.VoteCity;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class VoteCityServiceImpl extends BaseServiceImpl implements VoteCityService {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoteCityServiceImpl.class);
    @Resource
    VoteCityMapper mapper;

    /**
     * @Description 保存VoteCity
     * @Author taoye
     */
    @Override
    public ResponseJson insertVoteCity(JSONObject jsonObject) {
        try {
            VoteCity voteCity = JSONObject.toJavaObject(jsonObject, VoteCity.class);
            if (StringUtil.isEmpty(voteCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            mapper.insertVoteCity(voteCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteCityServiceImpl.insertVoteCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 删除VoteCity
     * @Author taoye
     */
    @Override
    public ResponseJson deleteVoteCity(JSONObject jsonObject) {
        try {
            VoteCity voteCity = JSONObject.toJavaObject(jsonObject, VoteCity.class);
            if (StringUtil.isEmpty(voteCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "删除条件不能为空");
            }
            mapper.deleteVoteCity(voteCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteCityServiceImpl.deleteVoteCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 更新VoteCity
     * @Author taoye
     */
    @Override
    public ResponseJson updateVoteCity(JSONObject jsonObject) {
        try {
            VoteCity voteCity = JSONObject.toJavaObject(jsonObject, VoteCity.class);
            if (StringUtil.isEmpty(voteCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "更新条件不能为空");
            }
            mapper.updateVoteCity(voteCity);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("VoteCityServiceImpl.updateVoteCityById出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


    /**
     * @Description 查询VoteCity
     * @Author taoye
     */
    @Override
    public ResponseJson getVoteCity(JSONObject jsonObject) {
        try {
            VoteCity voteCity = JSONObject.toJavaObject(jsonObject, VoteCity.class);
            if (StringUtil.isEmpty(voteCity.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询条件不能为空");
            }
            voteCity = mapper.getVoteCity(voteCity);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, voteCity);
        } catch (Exception e) {
            LOGGER.error("VoteCityServiceImpl.getVoteCity出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 插入
     * @param  jsonObject  key=voteCity  {@link com.ly.mt.center.data.vote.entity.VoteCity}
     * @return
     */
    @Override
    public ResponseJson insert(JSONObject jsonObject) {

        try {
            VoteCity voteCity = JSONObject.toJavaObject(jsonObject, VoteCity.class);
            Long areaId = jsonObject.getLong("areaId");
            Long userId = jsonObject.getLong("userId");
            voteCity.setArea_id(String.valueOf(areaId));
            voteCity.setUser_id(String.valueOf(userId));
            voteCity.setCreate_time(DateUtil.getNowDateStr());
            mapper.insertVoteCity(voteCity);
        } catch (Exception e) {
            LOGGER.error("数据中心一小时达投票模块,class={},method={},e={}","VoteCityServiceImpl","insert",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    /**
     * 查询
     * @param jsonObject key="userId" {@link Long} 用户id
     *                   key="areaId" {@link Long} 区域id
     * @return
     */
    @Override
    public ResponseJson find(JSONObject jsonObject) {
        Long userId = jsonObject.getLong("userId");
        Long areaId = jsonObject.getLong("areaId");
        if (userId == null || areaId == null){
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        VoteCity voteCity = mapper.findByUserIdAndAreaId(userId,areaId);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,voteCity);
    }

    /**
     * 查询投票数
     * @param jsonObject  key="areaId" {@link Long} 区域id
     * @return
     */
    @Override
    public ResponseJson findCount(JSONObject jsonObject) {
        try {
            Long areaId = jsonObject.getLong("areaId");
            if (areaId == null){
                return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
            }
            List<Map<String,Object>> list = mapper.findCountArea(areaId);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,list);
        } catch (Exception e) {
            LOGGER.error("数据中心一小时达投票模块,class={},method={},e={}","VoteCityServiceImpl","findCount",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}