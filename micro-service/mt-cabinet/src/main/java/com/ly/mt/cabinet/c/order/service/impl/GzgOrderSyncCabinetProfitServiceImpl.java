package com.ly.mt.cabinet.c.order.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.entity.CabinetBussinessCoorperation;
import com.ly.mt.cabinet.b.entity.CabinetStoreProfit;
import com.ly.mt.cabinet.b.entity.CabinetStoreProfitLog;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.cabinet.c.Device.service.GzgExchangeService;
import com.ly.mt.cabinet.c.coupon.entity.GzgCouponTypeEnum;
import com.ly.mt.cabinet.c.coupon.service.GzgCouponCodeService;
import com.ly.mt.cabinet.c.entity.GzgInfoVo;
import com.ly.mt.cabinet.c.entity.GzgOrderItemVo;
import com.ly.mt.cabinet.c.good.entity.GoodsSkuInfo;
import com.ly.mt.cabinet.c.good.entity.GzgSkuPicture;
import com.ly.mt.cabinet.c.order.entity.*;
import com.ly.mt.cabinet.c.order.service.GzgOrderScheduledService;
import com.ly.mt.cabinet.c.order.service.GzgOrderSyncCabinetProfitService;
import com.ly.mt.cabinet.c.rujin.service.GzgRujinDeviceService;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.feign.service.impl.FeignServiceImpl;
import com.ly.mt.core.redis.RedisKey;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_GZG_ORDER;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_GZG_ORDER_ITEMS;
import static com.ly.mt.core.feign.DataCenterMethod.*;


@Service
public class GzgOrderSyncCabinetProfitServiceImpl extends BaseServiceImpl implements GzgOrderSyncCabinetProfitService {
    private final static Logger log = LoggerFactory.getLogger(GzgOrderSyncCabinetProfitServiceImpl.class);


    static final int nThreads = Runtime.getRuntime().availableProcessors();//availableProcessors()获取当前电脑CPU数量

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads * 2);


    @Override
    public void syncCabinetStoreProfit(String orderId) {
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    syncCabinetStoreProfitData(orderId);
                } catch (Exception e) {
                log.error("订单完成后更新商铺收益信息GzgOrderSyncCabinetProfitServiceImpl.syncCabinetStoreProfit 报错，报错信息为 {}",e);
                }
            }
        });
    }


    public void syncCabinetStoreProfitData(String orderId) {
        CabinetStoreProfitLog cabinetStoreProfitLog1 = getCabinetStoreProfitLog(orderId);
        if(cabinetStoreProfitLog1 != null){
            log.info("订单信息已更新，请勿重复操作");
            return;
        }
        GzgOrder gzgOrder = getGzgOrderById(orderId);
        String imei = gzgOrder.getImei();
        CabinetBussinessCoorperation cabinetBussinessCoorperation = getDivideScale(imei);
        String divide_scale = cabinetBussinessCoorperation.getDivide_scale();
        String store_id = cabinetBussinessCoorperation.getStore_id();

        CabinetStoreProfit cabinetStoreProfit = new CabinetStoreProfit();//店铺总收益
        cabinetStoreProfit.setStore_id(store_id);
        cabinetStoreProfit.setOrder_amount(gzgOrder.getOrder_old_money());

        BigDecimal orderMoney = new BigDecimal(gzgOrder.getOrder_old_money());
        BigDecimal divideScale = new BigDecimal(divide_scale);//店家分成比例
        BigDecimal storeProfit = orderMoney.multiply(divideScale);

        cabinetStoreProfit.setProfit_amount(storeProfit+"");//分成收益总汇
        cabinetStoreProfit.setUpdate_time(DateUtil.getNowTimeStr());

        CabinetStoreProfitLog cabinetStoreProfitLog = new CabinetStoreProfitLog();//店铺收益日志
        cabinetStoreProfitLog.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_STORE_PROFIT_LOG));
        cabinetStoreProfitLog.setGzg_order_id(orderId);
        cabinetStoreProfitLog.setImei(imei);
        cabinetStoreProfitLog.setStore_id(store_id);

        cabinetStoreProfitLog.setOrder_amount(gzgOrder.getOrder_old_money());
        cabinetStoreProfitLog.setProfit_amount(storeProfit+"");
        cabinetStoreProfitLog.setCreate_time(DateUtil.getNowTimeStr());

        CabinetStoreProfit cabinetStoreProfitByStoreId = getCabinetStoreProfitByStoreId(store_id);
        if(cabinetStoreProfitByStoreId != null){
            //更新店铺总收益和添加此单收益日志
            cabinetStoreProfit.setId(cabinetStoreProfitByStoreId.getId());
            BigDecimal orderOldMoney = new BigDecimal(cabinetStoreProfitByStoreId.getOrder_amount());//该商户以前总共卖多少钱
            BigDecimal orderOldProfit = new BigDecimal(cabinetStoreProfitByStoreId.getProfit_amount());//该商户以前总共多少收益
            cabinetStoreProfit.setProfit_amount(orderOldProfit.add(storeProfit)+"");
            cabinetStoreProfit.setOrder_amount(orderOldMoney.add(orderMoney)+"");
            cabinetStoreProfit.setOrder_count(new BigDecimal(cabinetStoreProfitByStoreId.getOrder_count()).add(new BigDecimal(1))+"");
            updateCabinetStoreProfitData(cabinetStoreProfit,cabinetStoreProfitLog);
        }else {
            //添加店铺总收益和添加此单收益日志
            cabinetStoreProfit.setId(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CABINET_STORE_PROFIT));
            cabinetStoreProfit.setCreate_time(DateUtil.getNowTimeStr());
            cabinetStoreProfit.setOrder_count("1");//开始有收益
            addCabinetStoreProfitData(cabinetStoreProfit,cabinetStoreProfitLog);
        }


    }


    /**
     * 通过id查询订单信息
     * @param orderId
     */
    public GzgOrder getGzgOrderById(String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", orderId);
        String result = callDataCenter(GZG_ORDER_GET, jsonObject);
        GzgOrder gzgOrder = JSONObject.toJavaObject(JSON.parseObject(result), GzgOrder.class);
        return gzgOrder;
    }

    /**
     * 根据柜子imei查询分成比例divide_scale
     * @param imei
     */
    public CabinetBussinessCoorperation getDivideScale(String imei) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imei", imei);
        String result = callDataCenter(GZG_ORDER_GET_DIVIDE_SCALE, jsonObject);

        CabinetBussinessCoorperation cabinetBussinessCoorperation = JSONObject.toJavaObject(JSONObject.parseObject(result), CabinetBussinessCoorperation.class);
        return cabinetBussinessCoorperation;
    }


    /**
     * 根据柜子store_id查询该店铺收益汇总
     * @param store_id
     */
    public CabinetStoreProfit getCabinetStoreProfitByStoreId(String store_id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("store_id", store_id);
        String result = callDataCenter(CABINET_STORE_PROFIT_GET_BY_STORE_ID, jsonObject);

        CabinetStoreProfit cabinetStoreProfit = JSONObject.toJavaObject(JSONObject.parseObject(result), CabinetStoreProfit.class);
        return cabinetStoreProfit;
    }

    /**
     * 更新店家收益
     * @param cabinetStoreProfit
     * @param cabinetStoreProfitLog
     */
    @TxcTransaction(appName = "gzg")
    public  void updateCabinetStoreProfitData(CabinetStoreProfit cabinetStoreProfit,CabinetStoreProfitLog cabinetStoreProfitLog){
        String xid = TxcContext.getCurrentXid();
        JSONObject jsonCabinetStoreProfit = JSONObject.parseObject(JSONObject.toJSONString(cabinetStoreProfit));
        jsonCabinetStoreProfit.put("xid", xid);

        JSONObject jsonCabinetStoreProfitLog = JSONObject.parseObject(JSONObject.toJSONString(cabinetStoreProfitLog));
        jsonCabinetStoreProfitLog.put("xid", xid);

        callDataCenter(CABINET_STORE_PROFIT_UPDATE, jsonCabinetStoreProfit);
        callDataCenter(CABINET_STORE_PROFIT_LOG_INSERT, jsonCabinetStoreProfitLog);
    }
    /**
     * 新增店家收益
     * @param cabinetStoreProfit
     * @param cabinetStoreProfitLog
     */
    @TxcTransaction(appName = "gzg")
    public  void addCabinetStoreProfitData(CabinetStoreProfit cabinetStoreProfit,CabinetStoreProfitLog cabinetStoreProfitLog){
        String xid = TxcContext.getCurrentXid();
        JSONObject jsonCabinetStoreProfit = JSONObject.parseObject(JSONObject.toJSONString(cabinetStoreProfit));
        jsonCabinetStoreProfit.put("xid", xid);
        JSONObject jsonCabinetStoreProfitLog = JSONObject.parseObject(JSONObject.toJSONString(cabinetStoreProfitLog));
        jsonCabinetStoreProfitLog.put("xid", xid);
        callDataCenter(CABINET_STORE_PROFIT_INSERT,jsonCabinetStoreProfit );
        callDataCenter(CABINET_STORE_PROFIT_LOG_INSERT,jsonCabinetStoreProfitLog);
    }



    /**
     * 更改用户订单信息至超时状态
     * @param orderId
     */
    public CabinetStoreProfitLog getCabinetStoreProfitLog(String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gzg_order_id", orderId);
        String result = callDataCenter(CABINET_STORE_PROFIT_LOG_GET, jsonObject);
        CabinetStoreProfitLog cabinetStoreProfitLog = JSONObject.toJavaObject(JSON.parseObject(result), CabinetStoreProfitLog.class);
        return cabinetStoreProfitLog;
    }
}
