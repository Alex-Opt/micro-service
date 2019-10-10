package com.ly.mt.cabinet.b.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.BasePageRequestVo;
import com.ly.mt.cabinet.b.common.request.CabinetInfoBindStoreReqVo;
import com.ly.mt.cabinet.b.common.request.CabinetMenageListRequestVo;
import com.ly.mt.cabinet.b.common.request.WarehouseKeeperVo;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowCabinetByBdMsgVo;
import com.ly.mt.cabinet.b.common.response.SquareCabinetBdMsgVo;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 格子柜
 */
public interface CabinetCreateRecordService {

    /**
     *  添加格子柜上下架记录
     * @return
     */
    ResponseJson addCabinetCreateRecord(JSONObject jsonObject);


}
