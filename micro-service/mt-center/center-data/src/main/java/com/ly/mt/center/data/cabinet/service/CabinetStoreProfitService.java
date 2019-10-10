package com.ly.mt.center.data.cabinet.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfit;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

public interface CabinetStoreProfitService {

     ResponseJson getCabinetStoreProfitByStoreId(JSONObject jsonObject) ;
     ResponseJson insertCabinetStoreProfit(JSONObject jsonObject) ;

     ResponseJson updateCabinetStoreProfit(JSONObject jsonObject) ;


}
