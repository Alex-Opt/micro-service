package com.ly.mt.center.data.profits.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.profits.entity.ProfitsUserTask;
import com.ly.mt.center.data.profits.entity.ProfitsUserTaskParamVo;
import com.ly.mt.center.data.profits.mapper.ProfitsUserTaskServiceMapper;
import com.ly.mt.center.data.profits.service.ProfitsUserTaskService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: 用户赚取-任务表service
* @Author:         zhuyh
*/
@Service
public class ProfitsUserTaskServiceImpl extends BaseServiceImpl implements ProfitsUserTaskService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProfitsUserTaskServiceImpl.class);

    @Autowired
    private ProfitsUserTaskServiceMapper mapper;
    @Override
    public ResponseJson getNotFinishTaskByUId(JSONObject jsonObject) {
        ProfitsUserTaskParamVo paramVo = JSONObject.toJavaObject(jsonObject, ProfitsUserTaskParamVo.class);
        List<ProfitsUserTask> tasks = mapper.selectNotFinishTaskByUId(paramVo);
        if (CollectionUtils.isEmpty(tasks)){
            tasks = new ArrayList<>(0);
        }
        Map result = new HashMap();
        result.put("rows", tasks);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS, result);
    }
}
