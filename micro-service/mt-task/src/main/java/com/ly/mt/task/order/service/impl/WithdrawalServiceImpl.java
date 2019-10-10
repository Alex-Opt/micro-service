package com.ly.mt.task.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.cabinet.dao.CabinetCashRecordDao;
import com.ly.mt.core.data.cabinet.dao.CabinetReplenishRewardDao;
import com.ly.mt.core.data.cabinet.dao.CabinetRewardRecordDao;
import com.ly.mt.core.data.cabinet.entity.CabinetCashRecord;
import com.ly.mt.core.data.cabinet.entity.CabinetReplenishReward;
import com.ly.mt.core.data.cabinet.entity.CabinetRewardRecord;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.order.service.WithdrawalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.ly.mt.core.base.method.ThirdServerMethodEnum.FN_ORDER_CREATE;
import static com.ly.mt.core.base.method.ThirdServerMethodEnum.WX_PAY_WITHDRAWAL_QUERY;

@Service
public class WithdrawalServiceImpl extends BaseServiceImpl implements WithdrawalService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WithdrawalServiceImpl.class);

    //微信公共appid
    private static String wxPublicAccountId = "wx80a7401a02e0f8ec";

    @Resource
    private CabinetCashRecordDao cabinetCashRecordDao;

    @Resource
    private CabinetReplenishRewardDao cabinetReplenishRewardDao;

    @Resource
    private CabinetRewardRecordDao cabinetRewardRecordDao;


    /**
     * 根据提现申请成功的数据，查询微信提现处理结果，维护提现流水表
     *
     * @throws Exception
     */
    @Transactional
    @Override
    public void processQueryWithdrawal() throws Exception {
        //查询所有的提现申请成功数据
        JSONObject json = new JSONObject();
        json.put("status", "1");
        List<CabinetCashRecord> recordList = cabinetCashRecordDao.getRecordByStatus(json);

        for (CabinetCashRecord record : recordList) {
            json.put("appid", wxPublicAccountId);//
            json.put("tradeNo", record.getId());
            LOGGER.info("processQueryWithdrawal 查询微信提现结果参数", json.toJSONString());
            JSONObject returnJson = callFNService(WX_PAY_WITHDRAWAL_QUERY, json);
            //String result = callThirdCenter(WX_PAY_WITHDRAWAL_QUERY, json);
            LOGGER.info("processQueryWithdrawal 微信提现结果", returnJson);
            if (returnJson.getString("code").equals("0")) {
                String result = returnJson.getString("result");
                if (StringUtil.isNotEmpty(result)) {
                    JSONObject resultJson = JSONObject.parseObject(result);
                    //更新提现记录表数据，保存微信提现结果
                    json = new JSONObject();
                    json.put("id", record.getId());//订单号
                    String returnCode = resultJson.getString("return_code");
                    String resultCode = resultJson.getString("result_code");
                    String status = resultJson.getString("status");
                    if (returnCode.equals("SUCCESS")) {
                        if (resultCode.equals("SUCCESS")) {
                            if (status.equals("SUCCESS")) {
                                json.put("status", "2");  //转账成功
                                json.put("reason", resultJson.getString("reason"));
                            }
                        } else {
                            json.put("status", "3");  //转账失败
                            json.put("reason", resultJson.getString("return_msg"));
                            //转账失败，用户奖励可提现金额和累计提现金额进行回滚
                            CabinetReplenishReward reward = cabinetReplenishRewardDao.getUserCabinetRewardByUserId(record.getUserId(), record.getType());
                            if (null != reward) {
                                reward.setMayWithdrawalAmount(String.valueOf(new BigDecimal(reward.getMayWithdrawalAmount()).add(new BigDecimal(record.getWithdrawalAmount()))));
                                reward.setCumulativeWithdrawalAmount(String.valueOf(new BigDecimal(reward.getCumulativeWithdrawalAmount()).subtract(new BigDecimal(record.getWithdrawalAmount()))));
                                reward.setUpdateTime(DateUtil.getNowTimeStr());
                                cabinetReplenishRewardDao.updateRewardById(reward);
                            }
                        }
                    }
                    json.put("detail_id", resultJson.getString("detail_id"));
                    json.put("payment_time", resultJson.getString("payment_time"));
                    json.put("update_time", DateUtil.getNowTimeStr());
                    LOGGER.info("processQueryWithdrawal 保存微信提现结果", json.toJSONString());
                    //保存微信提现结果
                    cabinetCashRecordDao.updateById(json);
                }
            }
        }
    }

    /**
     * 查询7天前的未结算状态奖励明细记录数据，进行结算（status）
     * 根据userId和type，更新奖励金额表的待结算金额和可提现金额
     *
     * @throws Exception
     */
    @Transactional
    @Override
    public void processRefreshRewardRecord() throws Exception {
        String update_time = DateUtil.getNowTimeStr();
        //查询7天前的未结算状态奖励明细记录数据
        List<CabinetRewardRecord> recordList = cabinetRewardRecordDao.getRecordList(getPastDate(7));
        Map<String, List<CabinetRewardRecord>> recordMap = new HashMap<>();
        //遍历奖励明细数据，按照userId进行分组
        for (CabinetRewardRecord record : recordList) {
            List userRecordList = recordMap.get(record.getOwnerUserId());
            if (null == userRecordList) {
                userRecordList = new ArrayList();
            }
            userRecordList.add(record);
            recordMap.put(record.getOwnerUserId(), userRecordList);
        }
        //遍历recordMap，进行业务处理
        //BigDecimal bigDecimalBase = new BigDecimal(0);
        for (List<CabinetRewardRecord> userRecordList : recordMap.values()) {
            BigDecimal bigDecimal1 = new BigDecimal(0);
            BigDecimal bigDecimal2 = new BigDecimal(0);
            String userId = userRecordList.get(0).getOwnerUserId();
            List<String> idList = new ArrayList();
            for (CabinetRewardRecord record : userRecordList) {
                //计算补货奖励此次结算金额
                if ("1".equals(record.getType())) {
                    bigDecimal1 = bigDecimal1.add(new BigDecimal(record.getReward()));
                }
                //计算分成奖励此次结算金额
                if ("2".equals(record.getType())) {
                    bigDecimal2 = bigDecimal2.add(new BigDecimal(record.getReward()));
                }
                idList.add(record.getId());
            }
            //更新奖励明细记录为已结算状态，
            cabinetRewardRecordDao.updateCabinetRewardRecordByIdList(idList, update_time);
            //更新奖励表待结算和可提现金额
            updateReward(userId, bigDecimal1, "1", update_time);
            updateReward(userId, bigDecimal2, "2", update_time);
        }
    }

    //结算时，更新奖励表待结算和可提现金额
    public void updateReward(String userId, BigDecimal decimal, String type, String update_time) {
        BigDecimal bigDecimalBase = new BigDecimal(0);
        if (decimal.compareTo(bigDecimalBase) == 1) {
            CabinetReplenishReward reward = cabinetReplenishRewardDao.getUserCabinetRewardByUserId(userId, type);
            reward.setMayWithdrawalAmount(String.valueOf(new BigDecimal(reward.getMayWithdrawalAmount()).add(decimal)));
            reward.setPendingSettlementAmount(String.valueOf(new BigDecimal(reward.getPendingSettlementAmount()).subtract(decimal)));
            reward.setUpdateTime(update_time);
            cabinetReplenishRewardDao.updateRewardById(reward);
        }
    }


    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(today);
    }
}