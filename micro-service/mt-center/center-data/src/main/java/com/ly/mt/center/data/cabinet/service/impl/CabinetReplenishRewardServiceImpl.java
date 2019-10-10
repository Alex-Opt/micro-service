package com.ly.mt.center.data.cabinet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.base.service.impl.BaseServiceImpl;
import com.ly.mt.center.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.center.data.cabinet.mapper.CabinetReplenishRewardMapper;
import com.ly.mt.center.data.cabinet.service.CabinetReplenishRewardService;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * @author zhanglifeng
 * @date 2019-08-28
 * 奖励金操作数据中心层
 */
@Service
public class CabinetReplenishRewardServiceImpl extends BaseServiceImpl implements CabinetReplenishRewardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CabinetReplenishRewardServiceImpl.class);
    @Resource
    private CabinetReplenishRewardMapper cabinetReplenishRewardMapper;

    @Override
    public ResponseJson insertCabinetReplenishReward(JSONObject jsonObject) {
        try {
            CabinetReplenishReward cabinetReplenishReward = JSONObject.toJavaObject(jsonObject, CabinetReplenishReward.class);
            if (StringUtil.isEmpty(cabinetReplenishReward.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetReplenishRewardMapper.insertOne(cabinetReplenishReward);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishRewardServiceImpl.insertCabinetReplenishReward:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryCabinetReplenishRewardByUserId(JSONObject jsonObject) {
        try {
            Integer ty = null;
            Long user_id = jsonObject.getLong("user_id");
            String type = jsonObject.getString("type");
            if (StringUtil.isNotEmpty(type)) {
                ty = Integer.parseInt(type);
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetReplenishRewardMapper.queryUserCabinetRewardByUserId(user_id, ty));
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishRewardServiceImpl.queryCabinetReplenishRewardByUserId:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryCabinetReplenishRewardById(JSONObject jsonObject) {
        try {
            Long id = jsonObject.getLong("id");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, cabinetReplenishRewardMapper.queryCabinetRewardById(id));
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishRewardServiceImpl.queryCabinetReplenishRewardByUserId:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     *
     * 此方法可用于更新奖励的累积奖励金额，累积提现金额，可提现金额，带结算金额。
     * 使用方法为：传过来的入参是新增的金额即可。这里会查库得到原来的，累加上你穿过来的，更新进去。并通过版本号控制脏数据的问题。
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson updateCabinetReplenishRewardById(JSONObject jsonObject) {
        try {
            CabinetReplenishReward cabinetReplenishReward = JSONObject.toJavaObject(jsonObject, CabinetReplenishReward.class);
            String id = cabinetReplenishReward.getId();
            if (StringUtil.isEmpty(cabinetReplenishReward.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            //查询库的数据值
            CabinetReplenishReward reward = cabinetReplenishRewardMapper.queryCabinetRewardById(Long.valueOf(id));
            //-------------start------------------------奖励的求和
            //累计奖励金额
            String cumulativeRewardAmountOld = reward.getCumulative_reward_amount()==null?"0":reward.getCumulative_reward_amount();
            String cumulativeRewardAmountAdd = cabinetReplenishReward.getCumulative_reward_amount()==null?"0":cabinetReplenishReward.getCumulative_reward_amount();
            BigDecimal cumulativeRewardAmount = new BigDecimal(cumulativeRewardAmountOld).add(new BigDecimal(cumulativeRewardAmountAdd));
            //累计提现金额
            String cumulativeWithdrawalAmountOld = reward.getCumulative_withdrawal_amount()==null?"0":reward.getCumulative_withdrawal_amount();
            String cumulativeWithdrawalAmountAdd = cabinetReplenishReward.getCumulative_withdrawal_amount()==null?"0":cabinetReplenishReward.getCumulative_withdrawal_amount();
            BigDecimal cumulativeWithdrawalAmount = new BigDecimal(cumulativeWithdrawalAmountOld).add(new BigDecimal(cumulativeWithdrawalAmountAdd));
            //可提现金额
            String mayWithdrawalAmountOld = reward.getMay_withdrawal_amount()==null?"0":reward.getMay_withdrawal_amount();
            String mayWithdrawalAmountAdd = cabinetReplenishReward.getMay_withdrawal_amount()==null?"0":cabinetReplenishReward.getMay_withdrawal_amount();
            BigDecimal mayWithdrawalAmount = new BigDecimal(mayWithdrawalAmountOld).add(new BigDecimal(mayWithdrawalAmountAdd));
            //待结算金额
            String pendingSettlementAmountOld = reward.getPending_settlement_amount()==null?"0":reward.getPending_settlement_amount();
            String pendingSettlementAmountAdd = cabinetReplenishReward.getPending_settlement_amount()==null?"0":cabinetReplenishReward.getPending_settlement_amount();
            BigDecimal pendingSettlementAmount = new BigDecimal(pendingSettlementAmountOld).add(new BigDecimal(pendingSettlementAmountAdd));
            //-------------end------------------------奖励的求和
            CabinetReplenishReward userReward = new CabinetReplenishReward();
            userReward.setId(reward.getId());
            userReward.setVersion(reward.getVersion());
            userReward.setCumulative_reward_amount(cumulativeRewardAmount.toString());
            userReward.setCumulative_withdrawal_amount(cumulativeWithdrawalAmount.toString());
            userReward.setMay_withdrawal_amount(mayWithdrawalAmount.toString());
            userReward.setPending_settlement_amount(pendingSettlementAmount.toString());
            userReward.setUpdate_time(DateUtil.getNowTimeStr());
            int i = cabinetReplenishRewardMapper.updateRewardById(userReward);
            if(i>0){
                return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
            }else{
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR,"更新数据id="+reward.getId()+"失败，因为当前版本号过低！");
            }
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishRewardServiceImpl.insertCabinetReplenishReward:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson updateReplenishRewardById(JSONObject jsonObject) {
        try {
            CabinetReplenishReward cabinetReplenishReward = JSONObject.toJavaObject(jsonObject, CabinetReplenishReward.class);
            if (StringUtil.isEmpty(cabinetReplenishReward.getId())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "主键id不能为空");
            }
            cabinetReplenishRewardMapper.updateRewardById(cabinetReplenishReward);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("CabinetReplenishRewardServiceImpl.insertCabinetReplenishReward:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }


}
