package com.ly.mt.center.data.profits.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.profits.entity.ProfitsUserDetails;
import com.ly.mt.center.data.profits.entity.ProfitsUserParamVo;
import com.ly.mt.center.data.profits.mapper.ProfitsUserProfitsServiceMapper;
import com.ly.mt.center.data.profits.service.ProfitsUserProfitsService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description: 用户赚取-统计表service
* @Author:         zhuyh
*/
@Service
public class ProfitsUserProfitsServiceImpl extends BaseServiceImpl implements ProfitsUserProfitsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfitsUserProfitsServiceImpl.class);


    @Autowired
    private ProfitsUserProfitsServiceMapper mapper;

    @Override
    public ResponseJson getProfitsDetails(JSONObject jsonObject) {
        // 接收参数
        ProfitsUserParamVo paramVo = JSONObject.toJavaObject(jsonObject, ProfitsUserParamVo.class);
        // 查询数据
        ProfitsUserDetails details = mapper.selectDetailsByUId(paramVo);
        // 返回数据
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, details);
    }
}
