package com.ly.mt.marketing.server.profits.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.common.dict.ShopProfitsLogStatusEnum;
import com.ly.mt.core.common.dict.ShopProfitsLogTypeEnum;
import com.ly.mt.core.common.entity.marketing.*;
import com.ly.mt.core.common.util.DateUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.marketing.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.marketing.server.profits.mapper.ShopProfitsLogsServiceMapper;
import com.ly.mt.marketing.server.profits.service.ShopProfitsLogsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 *@Description 收益日志接口实现类
 *@Author  zhuyh
 */
@Service
public class ShopProfitsLogsServiceImpl extends BaseServiceImpl implements ShopProfitsLogsService {
    private static final String HAS_BEEN_SETTLEMENT = "has_been_settlement";

    private static final String WAIT_SETTLEMENT = "wait_settlement";

    @Resource
    private ShopProfitsLogsServiceMapper shopProfitsLogsServiceMapper;
    @Override
    public JSONObject getRewardOrders(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ShopProfitsLogs vo = JSONObject.parseObject(json, ShopProfitsLogs.class);
        PageHelper.startPage(vo.getPage(), vo.getRows());
        List<ShopProfitsLogsRewardOrderVo> orderVoList = shopProfitsLogsServiceMapper.getRewardOrder(vo);
        PageInfo<ShopProfitsLogsRewardOrderVo> pageInfo = new PageInfo<>(orderVoList);
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, orderVoList);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getRewards(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        PageHelper.startPage(vo.getPage(), vo.getRows());
        List<ShopProfitsLogsVo> rewardList = shopProfitsLogsServiceMapper.selectRewards(vo);
        getShopProfitsLogTypeEnumDetails(rewardList);
        PageInfo<ShopProfitsLogsVo> pageInfo = new PageInfo<>(rewardList);
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, rewardList);
        return JsonUtil.getSuccessJson(result);
    }


    @Override
    public JSONObject getRewardsTotal(String json) throws Exception {
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        ShopProfitsLogsVo logsVo = shopProfitsLogsServiceMapper.selectSumRewards(vo);
        return JsonUtil.getSuccessJson(logsVo.getProfit());
    }

    @Override
    public JSONObject getLodes(String json) throws Exception {
        Map<String, Object> result = new HashMap<>();
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        PageHelper.startPage(vo.getPage(), vo.getRows());
        List<ShopProfitsLogsVo> lodeList = shopProfitsLogsServiceMapper.selectLodes(vo);
        getShopProfitsLogTypeEnumDetails(lodeList);
        PageInfo<ShopProfitsLogsVo> pageInfo = new PageInfo<>(lodeList);
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, lodeList);
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 获取收益日志的类型
     *@Author  zhuyh
     */
    private void getShopProfitsLogTypeEnumDetails(List<ShopProfitsLogsVo> rewardList) {
        if (!CollectionUtils.isEmpty(rewardList)) {
            for (ShopProfitsLogsVo log : rewardList) {
                log.setTpName(ShopProfitsLogTypeEnum.getTxtById(log.getTp()));
                log.setSymbol(ShopProfitsLogTypeEnum.getSymbol(log.getTp()));
            }
        }
    }

    @Override
    public JSONObject getLodeTotal(String json) throws Exception {
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        ShopProfitsLogsVo logsVo = shopProfitsLogsServiceMapper.selectSumLodes(vo);
        return JsonUtil.getSuccessJson(logsVo.getProfit());
    }

    @Override
    public JSONObject getGrabCycleSum(String json) throws Exception {
        // 获取当前日期
        Date date = new Date();
        // 查询当前日期（2019.6.18）前的第七天第七天（2019.06.11未出）第八天(2019.06.10)、第九天2019.06.09、第十天2019.06.08每天的累计值
        Map<String, Object> result = new HashMap<>();
        // 查询最近已经结算的
        result.put(HAS_BEEN_SETTLEMENT, getGrabCycleSettlement(date));
        // 查询未结算的
        result.put(WAIT_SETTLEMENT, getGrabWaitSettlement(date));
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 查询待结算的最近一天
     *@Author  zhuyh
     */
    private ShopProfitsLogsCycleVo getGrabWaitSettlement(Date date) {
        Date resultDate = getBusinessDate(date, 2, 7);
        ShopProfitsLogsParamsVo paramsVo = getSettlementCycleParamVo(resultDate);
        List<Integer> list = new ArrayList<>();
        list.add(ShopProfitsLogTypeEnum.GRAB.getId());
        list.add(ShopProfitsLogTypeEnum.GRAB_CASH.getId());
        paramsVo.setTpList(list);
        List<ShopProfitsLogsCycleVo> cycleVos = shopProfitsLogsServiceMapper.selectCycleList(paramsVo);
        if (CollectionUtils.isEmpty(cycleVos)){
            ShopProfitsLogsCycleVo vo = new ShopProfitsLogsCycleVo();
            vo.setDate(resultDate);
            vo.setProfit(BigDecimal.valueOf(0.00));
            return vo;
        }
        return cycleVos.get(0);
    }
    /**
     *@Description  查询已经结算的抢单周期
     *@Author  zhuyh
     */
    private List<ShopProfitsLogsCycleVo> getGrabCycleSettlement(Date date) {
        ShopProfitsLogsParamsVo paramsVo = new ShopProfitsLogsParamsVo();
        List<Date> dateList = getCycleDate(date);
        paramsVo.setStartDate(dateList.get(dateList.size()-1));
        paramsVo.setEndDate(dateList.get(0));
        paramsVo.setStatus(ShopProfitsLogStatusEnum.SUC.getId());
        paramsVo.setUserId(getUserId());
        List<Integer> list = new ArrayList<>();
        list.add(ShopProfitsLogTypeEnum.GRAB.getId());
        list.add(ShopProfitsLogTypeEnum.GRAB_CASH.getId());
        paramsVo.setTpList(list);
        // 生成已结算周期
        return buildData(dateList, shopProfitsLogsServiceMapper.selectCycleList(paramsVo));
    }

    /**
     *@Description 生成最终已经结算数据
     *@Author  zhuyh
     */
    private List<ShopProfitsLogsCycleVo> buildData(List<Date> dateList, List<ShopProfitsLogsCycleVo> cycleVos) {
        Map<String, BigDecimal> map = getGrabCycleMap(cycleVos);
        List<ShopProfitsLogsCycleVo> returnList = new ArrayList<>(dateList.size());
        for (Date date : dateList){
            ShopProfitsLogsCycleVo vo = new ShopProfitsLogsCycleVo();
            vo.setDate(date);
            String dateStr = DateUtil.date2DateStr(date);
            if (map.containsKey(dateStr)){
                vo.setProfit(map.get(dateStr));
            } else {
                vo.setProfit(BigDecimal.valueOf(0.00));
            }
            returnList.add(vo);
        }
        return returnList;
    }

    /**
     *@Description  生成map
     *@Author  zhuyh
     */
    private Map<String, BigDecimal> getGrabCycleMap(List<ShopProfitsLogsCycleVo> cycleVos) {
        Map<String, BigDecimal> map = new HashMap<>();
        if (CollectionUtils.isEmpty(cycleVos)){
            return map;
        }
        cycleVos.forEach(c->{
            if (c != null && c.getDate() != null){
                map.put(DateUtil.date2DateStr(c.getDate()), c.getProfit());
            }
        });
        return map;
    }

    /**
     *@Description 获取抢单收益类型
     *@Author  zhuyh
     */
    private List<Integer> getGrabTpList() {
        List<Integer> list = new ArrayList<>();
        list.add(ShopProfitsLogTypeEnum.GRAB.getId());
        return list;
    }

    /**
     *@Description  生成周期日期
     *@Author  zhuyh
     */
    private List<Date> getCycleDate(Date date) {
        List<Date> dateList = new ArrayList<>(3);
        for (int i = 8; i < 11 ; i ++){
            Date resultDate = getBusinessDate(date, 2, i);
            dateList.add(resultDate);
        }
        return dateList;
    }

    /**
     *@Description 获取
     *@Author  zhuyh
     */
    private Date getBusinessDate(Date srcDate, Integer type, Integer num){
        Calendar c = Calendar.getInstance();
        c.setTime(srcDate);
        if (type == 1) {
            c.add(Calendar.DATE, num);
        } else if (type == 2) {
            c.add(Calendar.DATE, -num);
        }
        return c.getTime();
    }

    @Override
    public JSONObject getGrabs(String json) throws Exception {
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        List<Integer> tpList = new ArrayList<>();
        tpList.add(ShopProfitsLogTypeEnum.GRAB.getId());
        tpList.add(ShopProfitsLogTypeEnum.GRAB_CASH.getId());
        vo.setTpList(tpList);
        return getLogsList(vo);
    }

    @Override
    public JSONObject getOrderCycleSum(String json) throws Exception {
        // 获取当前日期
        Date date = new Date();
        // 查询当前日期（2019.6.18）前的第七天第七天（2019.06.11未出）第八天(2019.06.10)、第九天2019.06.09、第十天2019.06.08每天的累计值
        Map<String, Object> result = new HashMap<>();
        // 查询最近已经结算的
        result.put(HAS_BEEN_SETTLEMENT, getOrderCycleSettlement(date));
        // 查询未结算的
        result.put(WAIT_SETTLEMENT, getOrderWaitSettlement(date));
        return JsonUtil.getSuccessJson(result);
    }

    /**
     *@Description 查询待结算的最近一天
     *@Author  zhuyh
     */
    private ShopProfitsLogsCycleVo getOrderWaitSettlement(Date date) {
        Date resultDate = getBusinessDate(date, 2, 7);
        ShopProfitsLogsParamsVo paramsVo = getSettlementCycleParamVo(resultDate);
        List<Integer> list = new ArrayList<>();
        list.add(ShopProfitsLogTypeEnum.ORDER.getId());
        list.add(ShopProfitsLogTypeEnum.ORDER_CASH.getId());
        paramsVo.setTpList(list);
        List<ShopProfitsLogsCycleVo> cycleVos = shopProfitsLogsServiceMapper.selectCycleList(paramsVo);
        if (CollectionUtils.isEmpty(cycleVos)){
            ShopProfitsLogsCycleVo vo = new ShopProfitsLogsCycleVo();
            vo.setDate(resultDate);
            vo.setProfit(BigDecimal.valueOf(0.00));
            return vo;
        }
        return cycleVos.get(0);
    }

    /**
     *@Description 获取结算周期的参数
     *@Author  zhuyh
     */
    private ShopProfitsLogsParamsVo getSettlementCycleParamVo(Date resultDate) {
        ShopProfitsLogsParamsVo paramsVo = new ShopProfitsLogsParamsVo();
        paramsVo.setStartDate(resultDate);
        paramsVo.setEndDate(resultDate);
        paramsVo.setStatus(ShopProfitsLogStatusEnum.NORMAL.getId());
        paramsVo.setUserId(getUserId());
        return paramsVo;
    }

    /**
     *@Description 查询专属订单已结算的统计
     *@Author  zhuyh
     */
    private Object getOrderCycleSettlement(Date date) {
        ShopProfitsLogsParamsVo paramsVo = new ShopProfitsLogsParamsVo();
        List<Date> dateList = getCycleDate(date);
        List<Integer> list = new ArrayList<>();
        list.add(ShopProfitsLogTypeEnum.ORDER.getId());
        list.add(ShopProfitsLogTypeEnum.ORDER_CASH.getId());
        paramsVo.setTpList(list);
        paramsVo.setStartDate(dateList.get(dateList.size()-1));
        paramsVo.setEndDate(dateList.get(0));
        paramsVo.setStatus(ShopProfitsLogStatusEnum.SUC.getId());
        paramsVo.setUserId(getUserId());
        // 生成已结算周期
        return buildData(dateList, shopProfitsLogsServiceMapper.selectCycleList(paramsVo));
    }

    @Override
    public JSONObject getOrders(String json) throws Exception {
        ShopProfitsLogsParamsVo vo = JSONObject.parseObject(json, ShopProfitsLogsParamsVo.class);
        vo.setUserId(getUserId());
        List<Integer> tpList = new ArrayList<>();
        tpList.add(ShopProfitsLogTypeEnum.ORDER.getId());
        tpList.add(ShopProfitsLogTypeEnum.ORDER_CASH.getId());
        vo.setTpList(tpList);
        return getLogsList(vo);
    }

    /**
     *@Description  获取日志记录
     *@Author  zhuyh
     */
    private JSONObject getLogsList(ShopProfitsLogsParamsVo vo) {
        PageHelper.startPage(vo.getPage(), vo.getRows());
        List<ShopProfitsLogsVo> logsVo = shopProfitsLogsServiceMapper.selectList(vo);
        getShopProfitsLogTypeEnumDetails(logsVo);
        PageInfo<ShopProfitsLogsVo> pageInfo = new PageInfo<>(logsVo);
        Map<String, Object> result = new HashMap<>();
        result.put(TOTAL, pageInfo.getTotal());
        result.put(ROWS, logsVo);
        return JsonUtil.getSuccessJson(result);
    }
}
