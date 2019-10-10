package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.JsonArray;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.StoreAdminOrdersRequestBody;
import com.ly.mt.cabinet.b.common.response.CabinetImeiRespVo;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.StoreAdminOrdersRespVo;
import com.ly.mt.cabinet.b.service.StoreAdminService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import org.slf4j.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class StoreAdminServiceImpl extends BaseServiceImpl implements StoreAdminService {

    private static final Logger log = LoggerFactory.getLogger(StoreAdminServiceImpl.class);

    @Override
    public List<CabinetImeiRespVo> findUserCabinetImeis(TokenUserMessage body) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        String s = callDataCenter(DataCenterMethod.CABINET_ADMIN_FIND_IMEIS, param);
//        List<CabinetImeiRespVo> vos = JSONObject.parseObject(s, new TypeReference<List<CabinetImeiRespVo>>() {
//        });
        List<CabinetImeiRespVo> vos = new ArrayList<>();
        if(StringUtil.isNotEmpty(s)) {
            JSONArray array = JSONArray.parseArray(s);
            for (int i = 0; i < array.size();i++ ) {
                CabinetImeiRespVo vo =new CabinetImeiRespVo();
                vo.setImei(String.valueOf(array.get(i)));
                vos.add(vo);
            }
        }
        return vos;
    }

    @Override
    public String findConditionsOrders(StoreAdminOrdersRequestBody body, TokenUserMessage user) {
        JSONObject param = JSONObject.parseObject(JSONObject.toJSONString(body));
        param.put("userId",user.getUserId());
        param.put("phoneNo",user.getPhoneNo());
        String s = callDataCenter(DataCenterMethod.CABINET_ADMIN_CONDITION_ORDERS, param);
        //PageInfoResponseVo<StoreAdminOrdersRespVo> vo= JSONObject.toJavaObject(JSONObject.parseObject(s),PageInfoResponseVo.class);
        return s;
    }
}
