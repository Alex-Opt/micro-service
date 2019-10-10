package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.mapper.CabinetAreaMapper;
import com.ly.mt.center.data.cabinet.service.CabinetAreaService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CabinetAreaServiceImpl implements CabinetAreaService {

    private static final Logger log = LoggerFactory.getLogger(CabinetAreaServiceImpl.class);

    @Resource
    private CabinetAreaMapper cabinetAreaMapper;

    @Override
    public ResponseJson getByMPid(JSONObject data) {
        long start = System.currentTimeMillis();
        log.info("centData getByPid jsonParam={}",data.toJSONString());
        Integer mPid = data.getInteger("mPid");
        List<Map<String, Integer>> maps = null;//cabinetAreaMapper.selectByMId(mPid);
        long end = System.currentTimeMillis();
        log.info("call centData method getByPid cost {} mills",(end-start));
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,maps);
    }
}
