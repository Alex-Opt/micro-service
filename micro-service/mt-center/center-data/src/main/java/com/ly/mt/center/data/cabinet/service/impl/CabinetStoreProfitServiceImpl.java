package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProfit;
import com.ly.mt.center.data.cabinet.mapper.CabinetStoreMapper;
import com.ly.mt.center.data.cabinet.mapper.CabinetStoreProfitMapper;
import com.ly.mt.center.data.cabinet.requestdto.SquareDataStatisticsRequestDto;
import com.ly.mt.center.data.cabinet.response.*;
import com.ly.mt.center.data.cabinet.service.CabinetStoreProfitService;
import com.ly.mt.center.data.cabinet.service.CabinetStoreService;
import com.ly.mt.center.data.gzg.entity.GzgOrderPay;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

@Service
public class CabinetStoreProfitServiceImpl extends BaseServiceImpl implements CabinetStoreProfitService {

    private static final Logger log = LoggerFactory.getLogger(CabinetStoreProfitServiceImpl.class);

    @Resource
    private CabinetStoreProfitMapper cabinetStoreProfitMapper;



    @Override
    public ResponseJson getCabinetStoreProfitByStoreId(JSONObject jsonObject) {
        try {
            CabinetStoreProfit cabinetStoreProfit = JSONObject.toJavaObject(jsonObject, CabinetStoreProfit.class);
            if (StringUtil.isEmpty(cabinetStoreProfit.getStore_id())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询值 store_id 不能为空");
            }
            CabinetStoreProfit cabinetStoreProfit1 = cabinetStoreProfitMapper.getCabinetStoreProfitByStoreId(cabinetStoreProfit);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,cabinetStoreProfit1);
        } catch (Exception e) {
            log.error("CabinetStoreProfitServiceImpl.insert 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson insertCabinetStoreProfit(JSONObject jsonObject) {
        try {
            CabinetStoreProfit cabinetStoreProfit = JSONObject.toJavaObject(jsonObject, CabinetStoreProfit.class);
            if (StringUtil.isEmpty(cabinetStoreProfit.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetStoreProfitMapper.insert(cabinetStoreProfit);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetStoreProfitServiceImpl.insert 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateCabinetStoreProfit(JSONObject jsonObject) {
        try {
            CabinetStoreProfit cabinetStoreProfit = JSONObject.toJavaObject(jsonObject, CabinetStoreProfit.class);
            if (StringUtil.isEmpty(cabinetStoreProfit.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetStoreProfitMapper.updateByPrimaryKey(cabinetStoreProfit);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            log.error("CabinetStoreProfitServiceImpl.updateCabinetStoreProfit 出错:", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
