package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.cache.RedisServer;
import com.ly.mt.cabinet.b.common.dict.RedisEnum;
import com.ly.mt.cabinet.b.service.CabinetOptionsService;
import com.ly.mt.cabinet.b.util.DateUtil;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.feign.DataCenterMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CabinetOptionsServiceImpl extends BaseServiceImpl implements CabinetOptionsService {

    private static final Logger log = LoggerFactory.getLogger(CabinetOptionsServiceImpl.class);

    @Resource
    private RedisServer redisServer;

    @Override
    public Resp options() {
        log.info("call mehtod options of CabinetOptionsServiceImpl at {}", DateUtil.currentDateTime());
        long start = System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        String s = callDataCenter(DataCenterMethod.CABINET_OPTIONS_GETOPTIONS, jsonObject);
        //redisServer.setNoExpirt(RedisEnum.OPTION_CACHE,null,s);
        return Resp.createBySuccess(JSON.parseObject(s));
    }
}
