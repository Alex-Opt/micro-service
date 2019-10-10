package com.ly.mt.center.data.runnerprofit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.center.data.runnerprofit.entity.RLodeRunnerUserConfigs;
import com.ly.mt.center.data.runnerprofit.entity.RUserProfitLogs;
import com.ly.mt.center.data.runnerprofit.entity.RUserProfits;
import com.ly.mt.center.data.runnerprofit.entity.dto.RHomeOrderMasterDto;
import com.ly.mt.center.data.runnerprofit.entity.dto.RSimpRunnerTreesDto;
import com.ly.mt.center.data.runnerprofit.mapper.RLodeRunnerUserConfigsMapper;
import com.ly.mt.center.data.runnerprofit.mapper.RLodeRunnerUserTreesMapper;
import com.ly.mt.center.data.runnerprofit.mapper.RUserProfitLogsMapper;
import com.ly.mt.center.data.runnerprofit.mapper.RUserProfitsMapper;
import com.ly.mt.center.data.runnerprofit.service.RUserProfitsService;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.dict.PrimaryKey;

import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author panjingtian
 * @description 赚钱人受益日志操作
 * @date 2019/6/28 6:10 PM
 */
@Service
public class RUserProfitsServiceImpl implements RUserProfitsService {


    /**
     * 赚钱人收益日志默认状态
     * 状态 1：待结算，2正常，3：取消
     */
    private final byte PROFIT_LOGS_STATUS = 1;

    /**
     * 收益类型 此处为邀请
     * 1：邀请，2：通讯录，3：流量，4：专属订单，
     * 5：提现，6：专属订单提现，7：退货
     */
    private final byte INVITE_PROFIT_TYPE = 1;

    @Resource
    private RUserProfitLogsMapper logsMapper;
    @Resource
    private RUserProfitsMapper profitsMapper;
    @Resource
    private RLodeRunnerUserConfigsMapper configsMapper;
    @Resource
    private RLodeRunnerUserTreesMapper userTreesMapper;

    /**
     * 受益日志入库，更新受益总汇
     *
     * @param  jsonObject  orderDto   {@link RHomeOrderMasterDto} 到家c端订单dto对象
     * @return
     */
    @Override
    @Transactional
    public ResponseJson insterProfitsLogsAndProfits(JSONObject jsonObject) {

        /**
         * 1、根据传入的userid查询当前赚钱人的树
         * 2、根据orderid拿到订单金额
         * 3、根据configs配置对订单金额按比例分配给赚钱人树
         * 4、包装profitsLog对象入库
         * 5、计算赚钱人树中每个赚钱人受益总计入库
         * //TODO 后续优化总计加锁
         */
        RHomeOrderMasterDto orderDto = (RHomeOrderMasterDto) jsonObject.get("orderDto");
        if (orderDto == null) {
            throw new IllegalArgumentException("参数非法");
        }
        JSONObject result = new JSONObject(1);
        try {
            List<RSimpRunnerTreesDto> trees = userTreesMapper.findRunnerTressByUserId(orderDto.getUserId(), Thread.currentThread().getName());
            List<RUserProfitLogs> logsList = computeProfitsLogs(getRunnerUserConfig(), trees, orderDto);
            List<RUserProfits> profits = comProfits(logsList);
            profits.forEach(p->{
                profitsMapper.insertOrUpdateUserProfit(p);
            });
            logsMapper.inserts(logsList);
        } catch (Exception e) {
            result.put("error",null);
        }
        result.put("ok",null);
        return ResponseUtil.getResponseObj(ResponseCode.RESPONSE_CODE_SUCCESS,result);
    }


    /**
     * 计算赚钱人总汇
     *
     * @param logsList 受益日志集合
     *                 TODO 累加金额和加锁
     * @return
     */
    private List<RUserProfits> comProfits(List<RUserProfitLogs> logsList) {
        List<RUserProfits> profitsList = new ArrayList<>(logsList.size());

        logsList.forEach(l -> {
            RUserProfits userProfits = new RUserProfits();
            userProfits.setId(Long.valueOf(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_PROFITS)));
            userProfits.setUserId(l.getUserId());
            //TODO 当前金额加上之前金额
            userProfits.setTotalProfit(l.getProfit());
            //TODO 当前金额加上之前金额
            userProfits.setLodeFrozen(l.getProfit());
            userProfits.setCreateTime(new Date());
            userProfits.setModifyTime(new Date());
            profitsList.add(userProfits);
        });
        return profitsList;
    }

    /**
     * 计算赚钱人受益日志dto
     *
     * @param configs        赚钱人分成配置集合
     * @param trees          赚钱人树
     * @param orderMasterDto 订单dto
     * @return
     */
    private List<RUserProfitLogs> computeProfitsLogs(List<RLodeRunnerUserConfigs> configs, List<RSimpRunnerTreesDto> trees, RHomeOrderMasterDto orderMasterDto) {
        List<RUserProfitLogs> logsList = new ArrayList<>(trees.size());
        trees.forEach(t -> {
            RUserProfitLogs logs = new RUserProfitLogs();
            RLodeRunnerUserConfigs currentConfig = configs.stream().filter(c -> c.getLevel().intValue() == t.getRank().intValue()).findFirst().get();
            logs.setId(Long.valueOf(SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_PROFIT_LOGS)));
            logs.setOrderId(orderMasterDto.getOrderId());
            if (t.getFatherId() < 0) {
                logs.setUserId(t.getUserId());
            } else {
                logs.setUserId(t.getFatherId());
            }
            logs.setProfitType(INVITE_PROFIT_TYPE);
            logs.setProviderId(orderMasterDto.getUserId());
            logs.setProfit(orderMasterDto.getTotalPrice().multiply(currentConfig.getProportion()));
            logs.setStatus(PROFIT_LOGS_STATUS);
            logs.setCreateTime(new Date());
            logs.setModifyTime(new Date());
            logsList.add(logs);
        });
        return logsList;
    }


    /**
     * 赚钱人级别配置
     */
    private List<RLodeRunnerUserConfigs> getRunnerUserConfig() {
        List<RLodeRunnerUserConfigs> configs = configsMapper.findAll();
        return configs;
    }

}
