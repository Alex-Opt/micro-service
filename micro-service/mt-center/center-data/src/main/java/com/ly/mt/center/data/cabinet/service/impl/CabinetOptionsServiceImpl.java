package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetOptions;
import com.ly.mt.center.data.cabinet.mapper.CabinetAreaMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetOptionsMapper;
import com.ly.mt.center.data.cabinet.response.Option;
import com.ly.mt.center.data.cabinet.response.ResponseOptions;
import com.ly.mt.center.data.cabinet.service.CabinetAreaService;
import com.ly.mt.center.data.cabinet.service.CabinetOptionsService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CabinetOptionsServiceImpl implements CabinetOptionsService {

    private static final Logger log = LoggerFactory.getLogger(CabinetOptionsServiceImpl.class);

    @Resource
    private CabinetOptionsMapper cabinetOptionsMapper;
    @Resource
    private CabinetAreaMapper cabinetAreaMapper;
    @Override
    public ResponseJson getOptions(JSONObject parameter) {
        List<CabinetOptions> options = cabinetOptionsMapper.getOptions();
        JSONObject result = new JSONObject();
        ResponseOptions responseOptions = new ResponseOptions();
        Map<String,Object> optionMap = new HashMap<>();
        options.forEach(x ->{
            int id = x.getId();
            int type = x.getType();
            String value = x.getOption_value();
            Option option = new Option();
            option.setId(id);
            option.setValue(value);
            switch (type){
                case 0:
                    responseOptions.getStore_type().add(option);
                    break;
                case 1:
                    responseOptions.getForecast_flow().add(option);
                    break;
                case 2:
                    responseOptions.getDecorate_type().add(option);
                    break;
                case 3:
                    responseOptions.getIs_chain().add(option);
                    break;
                case 4:
                    responseOptions.getPer_capita_expense().add(option);
                    break;
                case 5:
                    responseOptions.getUse_area().add(option);
                    break;
                case 6:
                    responseOptions.getIs_deposit().add(option);
                    break;
                default:
                    responseOptions.getIs_sign_contract().add(option);
                    break;
            }
        });
        optionMap.put("store_type",responseOptions.getStore_type());
        optionMap.put("forcast_flow",responseOptions.getForecast_flow());
        optionMap.put("decorate_type",responseOptions.getDecorate_type());
        optionMap.put("is_chain",responseOptions.getIs_chain());
        optionMap.put("is_deposit",responseOptions.getIs_deposit());
        optionMap.put("use_area",responseOptions.getUse_area());
        optionMap.put("is_sign_contract",responseOptions.getIs_sign_contract());
        optionMap.put("per_capita_expense",responseOptions.getPer_capita_expense());
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,optionMap);
    }
}
