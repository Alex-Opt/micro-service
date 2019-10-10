package com.ly.mt.mall.h5.vote.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.feign.DataCenterMethod;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.mall.h5.vote.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @description
 * 投票操作
 * @author panjingtian
 * @date 2019/7/4 2:54 PM
 */
@Service
public class VoteServiceImpl extends BaseServiceImpl implements VoteService {

    private final static Logger LOGGER = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Override
    public ResponseJson ifVote(Long userId, Long areaId) {
        if ( userId == null || areaId == null){
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        JSONObject resultJson = new JSONObject(1);
        String result   = null;
        try {
            JSONObject jsonObject = new JSONObject(2);
            jsonObject.put("userId",userId);
            jsonObject.put("areaId",areaId);
            result = callDataCenter(DataCenterMethod.HOME_VOTE_CITY_HOUR_FIND, jsonObject);
            if (StringUtils.isEmpty(result)){
                //不可投票
                resultJson.put("status","0");
            }else {
                //可以投票
                resultJson.put("status","1");
            }
            return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,resultJson);
        } catch (Exception e) {
            LOGGER.error("一小时达投票查询,class={},method={},e={}","VoteServiceImpl","ifVote",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson vote(Long userId, Long areaId) {
        JSONObject target = new JSONObject(2);
        target.put("userId",userId);
        target.put("areaId",areaId);
        target.put("id",Long.valueOf(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_VOTE_CITY)));
        try {
            String result = callDataCenter(DataCenterMethod.HOME_VOTE_CITY_HOUR_INSERT, target);
        } catch (Exception e) {
            LOGGER.error("投票模块投票异常,class={},method={},e={}","VoteServiceImpl","vote",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson findCount(Long areaId) {
        if (areaId == null || areaId <0 ){
            areaId = 0l;
        }
        JSONObject target = new JSONObject(1);
        target.put("areaId",areaId);
        String result = null;
        try {
            result = callDataCenter(DataCenterMethod.HOME_VOTE_CITY_HOUR_FINDCOUNT, target);
        } catch (Exception e) {
            LOGGER.error("投票模块查询投票数异常,class={},method={},e={}","VoteServiceImpl","findCount",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
    }

    @Override
    public ResponseJson isOpenOneHourArea(Long areaId) {
        if (areaId == null || areaId < 0){
            areaId = 0l;
        }
        JSONObject target = new JSONObject(1);
        target.put("areaId",areaId);
        String result = null;
        try {
            result = callDataCenter(DataCenterMethod.CITY_ONE_HOUR_FINDBYAREA, target);
        } catch (Exception e) {
            LOGGER.error("投票模块异常,class={},method={},e={}","VoteServiceImpl","findOneHourArea",e);
            return ResponseUtil.getResponseCode(ResponseCode.RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
    }
}
