package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.dict.CabinetInfoStatusEnum;
import com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum;
import com.ly.mt.cabinet.b.common.dict.StoreCreateStatusEnum;
import com.ly.mt.cabinet.b.common.dto.TokenUserMessage;
import com.ly.mt.cabinet.b.common.request.BasePageRequestVo;
import com.ly.mt.cabinet.b.common.request.CabinetInfoBindStoreReqVo;
import com.ly.mt.cabinet.b.common.request.CabinetMenageListRequestVo;
import com.ly.mt.cabinet.b.common.request.WarehouseKeeperVo;
import com.ly.mt.cabinet.b.common.response.PageInfoResponseVo;
import com.ly.mt.cabinet.b.common.response.ShowCabinetByBdMsgVo;
import com.ly.mt.cabinet.b.common.response.SquareCabinetBdMsgVo;
import com.ly.mt.cabinet.b.entity.CabinetCreateRecord;
import com.ly.mt.cabinet.b.entity.CabinetInfo;
import com.ly.mt.cabinet.b.entity.CabinetStore;
import com.ly.mt.cabinet.b.service.CabinetCreateRecordService;
import com.ly.mt.cabinet.b.service.CabinetInfoService;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.good.entity.GzgGoodsPlan;
import com.ly.mt.cabinet.c.programme.entity.GzgProgramme;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.feign.DataCenterMethod;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_RU_JIN;
import static com.ly.mt.cabinet.b.common.dict.CabinetTypeEnum.CABINET_TYPE_YI_LIAN;
import static com.ly.mt.core.feign.DataCenterMethod.*;

@Service
public class CabinetCreateRecordServiceImpl extends BaseServiceImpl implements CabinetCreateRecordService {

    private static final Logger log = LoggerFactory.getLogger(CabinetCreateRecordServiceImpl.class);


    @Override
    public ResponseJson addCabinetCreateRecord(JSONObject jsonObject) {
        return null;
    }
}
