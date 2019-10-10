package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.center.data.cabinet.mapper.CabinetInfoMapper;
import com.ly.mt.center.data.cabinet.requestdto.CabinetStoreAdminRestDto;
import com.ly.mt.center.data.cabinet.requestdto.SquareCabinatRequestDto;
import com.ly.mt.center.data.cabinet.response.Orders;
import com.ly.mt.center.data.cabinet.response.PageInfoResponseVo;
import com.ly.mt.center.data.cabinet.service.CabinetAdminService;
import com.ly.mt.center.data.gzg.mapper.GzgOrdersMapper;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CabinetAdminServiceImpl implements CabinetAdminService {
    private static final Logger log = LoggerFactory.getLogger(CabinetAdminServiceImpl.class);

    @Resource
    CabinetInfoMapper cabinetInfoMapper;
    @Resource
    GzgOrdersMapper ordersMapper;

    @Override
    public ResponseJson findCabinetImeis(JSONObject jsonObject) {
        SquareCabinatRequestDto param = JSONObject.toJavaObject(jsonObject, SquareCabinatRequestDto.class);
        List<String> list = cabinetInfoMapper.findsByStoreOwner(param.getPhoneNo());
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,list);
    }

    @Override
    public ResponseJson findConditionsOrders(JSONObject jsonObject) {
        CabinetStoreAdminRestDto param = JSONObject.toJavaObject(jsonObject, CabinetStoreAdminRestDto.class);
        Map result = new HashMap();
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        if(StringUtil.isEmpty(param.getStart_time())){
            param.setStart_time("1970-01-01");
        }
        List<Orders>  list =  ordersMapper.findOrdersByOwner(param.getImei(),param.getPhoneNo(),param.getIntStatus(),param.getFullStartTime(),param.getFullEndTime());
        com.github.pagehelper.PageInfo pageInfo = new PageInfo(list);
        result.put("total", pageInfo.getTotal());
        result.put("rows", list);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
    }
}
