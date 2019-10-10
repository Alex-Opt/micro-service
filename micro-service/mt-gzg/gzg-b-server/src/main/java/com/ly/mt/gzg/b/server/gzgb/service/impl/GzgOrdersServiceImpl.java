package com.ly.mt.gzg.b.server.gzgb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.gzg.GzgOrderEnum;
import com.ly.mt.core.common.util.DateUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.gzg.b.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgOrdersServiceMapper;
import com.ly.mt.gzg.b.server.gzgb.service.GzgOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GzgOrdersServiceImpl extends BaseServiceImpl implements GzgOrdersService {
    private final static org.slf4j.Logger Logger = LoggerFactory.getLogger(GzgOrdersServiceImpl.class);
    @Resource
    private GzgOrdersServiceMapper gzgOrdersServiceMapper;

    @Override
    public JSONObject geOrders(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Object> param = new HashMap<>();
        if (jsonObject.getLong("startTime") != null){
            param.put("startTime",new Date(jsonObject.getLong("startTime")));
        }
        if (jsonObject.getLong("endTime") != null){
            param.put("endTime",new Date(jsonObject.getLong("endTime")));
        }
        param.put("orderStatus",jsonObject.getInteger("orderStatus"));
        param.put("code",jsonObject.getString("code"));
        param.put("hotelId",jsonObject.getString("hotelId"));
        log.info("getOrders jsonParam:{}",jsonObject);
        List<Map<String,Object>> res = gzgOrdersServiceMapper.findOrders(param);
        res.forEach(x->{
            Date time = (Date)x.get("createTime");
            Integer status = (Integer)x.get("orderStatus");
            x.put("createTime", DateUtil.date2TimeStr(time));
            x.put("orderStatus", GzgOrderEnum.getMsg(status));
        });

        return JsonUtil.getSuccessJson(res);
    }
}
