package com.ly.mt.cabinet.b.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.request.WithdrawalVO;
import com.ly.mt.cabinet.b.common.request.WxUserVo;
import com.ly.mt.cabinet.b.login.vo.TokenInfo;
import com.ly.mt.cabinet.b.service.WithdrawalService;
import com.ly.mt.cabinet.b.util.Resp;
import com.ly.mt.cabinet.base.config.YmlConfig;
import com.ly.mt.cabinet.base.service.impl.BaseServiceImpl;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.*;

@Service
public class WithdrawalServiceImpl extends BaseServiceImpl implements WithdrawalService {

    private static final Logger log = LoggerFactory.getLogger(WithdrawalServiceImpl.class);
    @Resource
    YmlConfig yml;

    //提现申请备注字段内容
    private static String desc = "奖励提现";
    //用户微信体现最小金额0.30
    private static BigDecimal amountMin = new BigDecimal(30);
    //用户微信体现最大金额5000.00
    private static BigDecimal amountMax = new BigDecimal(500000);
    //商户每日最大体现金额100000.00
    private static BigDecimal totalAmountMax = new BigDecimal(10000000);


    @Override
    public Resp  checkWxUser(String userId){
        try{
            String wxUser = redisService.get(REDIS_CABINET_CASH_RECORD_STRING_USER_ID, userId);
            if(StringUtil.isEmpty(wxUser)){
                return Resp.createByErrorMessage("用户没有绑定微信账号");
            }
            String openId = JSONObject.parseObject(wxUser).getString("openId");
            if(StringUtil.isEmpty(openId)){
                return Resp.createByErrorMessage("用户没有绑定微信账号");
            }
            return Resp.createBySuccess(wxUser);
        }catch (Exception e){
            log.info("checkWxUser 绑定微信用户信息出现异常", e);
            return Resp.createByError();
        }
    }

    @Override
    public Resp addWxUser(WxUserVo wxUserVo) {
        try {
            /*JSONObject json = new JSONObject();
            json.put("id", wxUserVo.getUserId());
            //json.put("id", tokenInfo.getUserId());
            json.put("wx_open_id", wxUserVo.getOpenId());
            json.put("nickname", wxUserVo.getNickname());
            //json.put("avatar_pic_src", wxUserVo.getHeadImgUrl());//微信头像
            if (StringUtil.isNotEmpty(wxUserVo.getSexDesc())) {
                if (wxUserVo.getSexDesc().equals("男")) {
                    json.put("sex", "0");
                } else if (wxUserVo.getSexDesc().equals("女")) {
                    json.put("sex", "1");
                }
            }
            callDataCenter(USER_UPDATE, json);*/
            String userJson = JSON.toJSONString(wxUserVo);
            //String clickToken = redisService.get(REDIS_REPEAT_CLICK_ACTION_APPLY_WITHDRAWAL, wxUserVo.getUserId());
            redisService.set(REDIS_CABINET_CASH_RECORD_STRING_USER_ID,wxUserVo.getUserId(), JSON.toJSONString(wxUserVo), 5256000L, TimeUnit.SECONDS);
            log.info("addWxUser 绑定微信用户信息", userJson);
            return Resp.createBySuccess();
        } catch (Exception e) {
            log.info("addWxUser 绑定微信用户信息出现异常", e);
            return Resp.createByError();
        }
    }

    @Override
    public Resp deleteWxUser(long userId) {
        try {

            /*JSONObject json = new JSONObject();
            json.put("id", String.valueOf(userId));
            json.put("wx_open_id", "");
            callDataCenter(USER_UPDATE, json);*/
            redisService.del(REDIS_CABINET_CASH_RECORD_STRING_USER_ID,String.valueOf(userId));
            log.info("deleteWxUser 解除绑定微信用户信息");
            return Resp.createBySuccess();
        } catch (Exception e) {
            log.info("deleteWxUser 解除绑定微信用户信息出现异常", e);
            return Resp.createByError();
        }
    }

    @Override
    public Resp queryReplenishReward(WithdrawalVO withdrawalVO) {
        try {
            //查询用户的奖励数据，type为空，查询结果包含分成奖励和补货奖励
            JSONObject json = new JSONObject();
            json.put("user_id", withdrawalVO.getUserId());
            json.put("type", withdrawalVO.getType());
            log.info("queryReplenishReward查询用户奖励金额", json.toJSONString());
            String result = callDataCenter(CABINET_REPLENISH_REWARD_BY_USERID, json);
            JSONArray array = null;
            if (StringUtil.isNotEmpty(result)) {
                array = JSONArray.parseArray(result);
                BigDecimal bigDecimal1 = new BigDecimal(100);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject node = array.getJSONObject(i);
                    BigDecimal cumulative_reward_amount = new BigDecimal(node.getString("cumulative_reward_amount")).divide(bigDecimal1,2, RoundingMode.HALF_UP);
                    BigDecimal may_withdrawal_amount = new BigDecimal(node.getString("may_withdrawal_amount")).divide(bigDecimal1,2, RoundingMode.HALF_UP);
                    BigDecimal pending_settlement_amount = new BigDecimal(node.getString("pending_settlement_amount")).divide(bigDecimal1,2, RoundingMode.HALF_UP);
                    BigDecimal cumulative_withdrawal_amount = new BigDecimal(node.getString("cumulative_withdrawal_amount")).divide(bigDecimal1,2, RoundingMode.HALF_UP);
                    node.put("cumulative_reward_amount",cumulative_reward_amount);
                    node.put("may_withdrawal_amount",may_withdrawal_amount);
                    node.put("pending_settlement_amount",pending_settlement_amount);
                    node.put("cumulative_withdrawal_amount",cumulative_withdrawal_amount);
                    node.put("balance_amount",may_withdrawal_amount.add(pending_settlement_amount));
                }
            }
            return Resp.createBySuccess(array);
        } catch (Exception e) {
            log.info("queryReplenishReward查询用户奖励金额出现异常", e);
            return Resp.createByError();
        }
    }

    @TxcTransaction(appName = "applyWithdrawal")
    @Override
    public Resp applyWithdrawal(String withdrawalAmount, String userId, String ip, String type) {
        try {
            //防止重复提交
            String clickToken = redisService.get(REDIS_REPEAT_CLICK_ACTION_APPLY_WITHDRAWAL, userId);
            if (StringUtil.isEmpty(clickToken)) {
                redisService.set(REDIS_REPEAT_CLICK_ACTION_APPLY_WITHDRAWAL,userId, userId, 10L, TimeUnit.SECONDS);
            } else {
                return Resp.createByErrorMessage("操作过快，停几秒再试试吧");
            }
            //查询登录用户的openId
          /*  JSONObject json = new JSONObject();
            json.put("id", userId);
            String returnJson = callDataCenter(USER_GET_USER_BY_ID, json);
            JSONObject userJson = JSONObject.parseObject(returnJson);
            String openId = userJson.getString("wx_open_id");
            if (StringUtil.isEmpty(openId)) {
                return Resp.createByErrorMessage("用户没有绑定微信账号");
            }*/
            String wxUser = redisService.get(REDIS_CABINET_CASH_RECORD_STRING_USER_ID, userId);
            if(StringUtil.isEmpty(wxUser)){
                return Resp.createByErrorMessage("用户没有绑定微信账号");
            }
            String openId = JSONObject.parseObject(wxUser).getString("openId");
            if(StringUtil.isEmpty(openId)){
                return Resp.createByErrorMessage("用户没有绑定微信账号");
            }
            //查询用户的奖励金额
            //校验用户的申请提现金额是否大于此用户的可提现金额
            JSONObject json = new JSONObject();
            BigDecimal amount = new BigDecimal(Double.valueOf(withdrawalAmount) * 100);
            json = new JSONObject();
            json.put("user_id", userId);
            json.put("type", type);
            String replenishReward = callDataCenter(CABINET_REPLENISH_REWARD_BY_USERID, json);
            if (StringUtil.isEmpty(replenishReward)) {
                return Resp.createByErrorMessage("用户的奖励数据为空，不能申请提现");
            }
            JSONArray replenishRewardArray = JSONArray.parseArray(replenishReward);
            JSONObject replenishRewardJson = replenishRewardArray.getJSONObject(0);
            BigDecimal mayWithdrawalAmount = new BigDecimal(replenishRewardJson.getString("may_withdrawal_amount"));
            if (amount.compareTo(mayWithdrawalAmount) == 1) {
                return Resp.createByErrorMessage("申请提现失败，申请提现金额大于用户的可提现金额");
            }
            //校验提现申请金额
            Resp checkResp = checkAmount(amount, userId);
            if (checkResp.getCode() == 0) {
                String xid = TxcContext.getCurrentXid();
                //添加提现申请记录（状态status=0,初始状态，未向微信发起申请）
                json = new JSONObject();
                json.put("xid", xid);
                String cashRecordId = SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_CASH_RECORD);
                json.put("id", cashRecordId);
                json.put("user_id", userId);
                json.put("open_id", openId);
                json.put("withdrawal_amount", amount);
                json.put("status", "0");
                json.put("type", type);//提现金额类型
                json.put("remark", desc);
                json.put("create_time", DateUtil.getNowTimeStr());
                log.info("applyWithdrawal 添加微信提现申请记录数据", json.toJSONString());
                callDataCenter(CABINET_CASH_RECORD_INSERT, json);
                //发起微信体现申请
                json = new JSONObject();
                //json.put("appid", "wxa536d96a4991eda6");//
                json.put("appid",yml.getWxGzgbAccount());
                json.put("amount", amount);//提现金额
                json.put("tradeNo", cashRecordId);//订单号
                json.put("openid", openId);
                json.put("desc", "提现申请");
                json.put("ip", ip);
                log.info("applyWithdrawal 微信申请提现参数", json.toJSONString());
                callThirdCenter(WX_PAY_WITHDRAWAL, json);
                //申请提现成功，扣除用户的可提现金额，增加累计提现金额
                BigDecimal cumulativeWithdrawalAmount = new BigDecimal(replenishRewardJson.getString("cumulative_withdrawal_amount"));
                json = new JSONObject();
                json.put("xid", xid);
                json.put("id", replenishRewardJson.getString("id"));
                json.put("may_withdrawal_amount", mayWithdrawalAmount.subtract(amount));
                json.put("cumulative_withdrawal_amount", cumulativeWithdrawalAmount.add(amount));
                json.put("update_time", DateUtil.getNowTimeStr());
                json.put("version", replenishRewardJson.getString("version"));
                callDataCenter(UPDATE_REPLENISH_REWARD_BYID, json);
                //修改提现申请记录（状态status=1,向微信申请成功）
                json = new JSONObject();
                json.put("xid", xid);
                json.put("id", cashRecordId);
                json.put("status", "1");
                log.info("applyWithdrawal 更新微信提现申请记录数据", json.toJSONString());
                callDataCenter(CABINET_CASH_RECORD_UPDATE, json);
                return Resp.createBySuccess();
            } else {
                return checkResp;
            }
        } catch (Exception e) {
            log.info("applyWithdrawal申请提现出现异常", e);
            return Resp.createByError();
        }
    }

    @Override
    public Resp alWithdrawal() {
        try {
            JSONObject json = new JSONObject();
            json.put("out_biz_no", StringUtil.getRandomIntByLength(15));
            log.info("alWithdrawal zhifubao参数", json.toJSONString());
            //String result = callThirdCenter(AL_PAY_WITHDRAWAL, json);
            //log.info("alWithdrawal zhifubao参数结果", result);
            return Resp.createBySuccess();
        }catch (Exception e) {
            log.info("queryWithdrawal查询微信提现结果出现异常", e);
            return Resp.createByError();
        }
    }



    @Override
    public Resp queryWithdrawal(String tradeNo) {
        try {
            //查询所有的提现申请成功数据
            JSONObject json = new JSONObject();
            json.put("status", "1");
            String recordList = callDataCenter(CABINET_CASH_RECORD_BY_STATUS, json);
            if (StringUtil.isNotEmpty(recordList)) {
                JSONArray recordListArray = JSONObject.parseArray(recordList);
                for (int i = 0; i < recordListArray.size(); i++) {
                    JSONObject node = recordListArray.getJSONObject(i);
                    json.put("appid", yml.getWxPublicAccountId());//
                    //json.put("tradeNo", tradeNo);//订单号
                    json.put("tradeNo", node.getString("id"));
                    log.info("queryWithdrawal 查询微信提现结果参数", json.toJSONString());
                    String result = callThirdCenter(WX_PAY_WITHDRAWAL_QUERY, json);
                    log.info("queryWithdrawal 微信提现结果", result);
                    if (StringUtil.isNotEmpty(result)) {
                        JSONObject resultJson = JSONObject.parseObject(result);
                        //更新提现记录表数据，保存微信提现结果
                        json = new JSONObject();
                        json.put("id", node.getString("id"));//订单号
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
                            }
                        }
                        json.put("detail_id", resultJson.getString("detail_id"));
                        json.put("payment_time", resultJson.getString("payment_time"));
                        // json.put("reason", resultJson.getString("reason"));
                        json.put("update_time", DateUtil.getNowTimeStr());
                        log.info("queryWithdrawal 保存微信提现结果", json.toJSONString());
                        callDataCenter(CABINET_CASH_RECORD_UPDATE, json);
                    }
                }
            }
            return Resp.createBySuccess();
        } catch (Exception e) {
            log.info("queryWithdrawal查询微信提现结果出现异常", e);
            return Resp.createByError();
        }
    }

    /**
     * 校验提现金额和次数
     *
     * @param amount
     * @param userId
     * @return
     */
    public Resp checkAmount(BigDecimal amount, String userId) throws Exception {
        //BigDecimal amount = new BigDecimal(withdrawalAmount);
        if (amount.compareTo(amountMin) == -1 || amount.compareTo(amountMax) == 1) {
            return Resp.createByErrorMessage("申请提现失败，低于最小金额0.30元或高于5000.00元");
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("today_start_time", getTodayStartTime());
        json.put("today_end_time", getTodayEndTime());
        String userRecord = callDataCenter(CASH_RECORD_BY_USERID_TODAY, json);
        if (StringUtil.isNotEmpty(userRecord)) {
            JSONArray userRecordArray = JSONObject.parseArray(userRecord);
            //校验此用户今日体现次数
            if (userRecordArray.size() >= 10) {
                return Resp.createByErrorMessage("该用户当天提现次数上限：微信企业付款每天最多可向同一个用户付款10次");
            }
            //校验此用户今日累计提现金额
            BigDecimal userTotalAmount = new BigDecimal(0.00);
            for (int i = 0; i < userRecordArray.size(); i++) {
                JSONObject node = userRecordArray.getJSONObject(i);
                userTotalAmount = userTotalAmount.add(new BigDecimal(node.getString("withdrawal_amount")));
            }
            userTotalAmount = userTotalAmount.add(amount);
            if (userTotalAmount.compareTo(amountMax) == 1) {
                return Resp.createByErrorMessage("申请提现金额大于今日可提现金额");
            }
        }
        //校验商户今日体现总金额
        json = new JSONObject();
        json.put("today_start_time", getTodayStartTime());
        json.put("today_end_time", getTodayEndTime());
        String record = callDataCenter(CASH_RECORD_BY_USERID_TODAY, json);
        if (StringUtil.isNotEmpty(record)) {
            BigDecimal totalAmount = new BigDecimal(0.00);
            JSONArray recordArray = JSONObject.parseArray(record);
            for (int j = 0; j < recordArray.size(); j++) {
                JSONObject node = recordArray.getJSONObject(j);
                totalAmount = totalAmount.add(new BigDecimal(node.getString("withdrawal_amount")));
            }
            totalAmount = totalAmount.add(amount);
            if (totalAmount.compareTo(totalAmountMax) == 1) {
                return Resp.createByErrorMessage("超出今日商户付款总额限额，请明天再试");
            }
        }
        return Resp.createBySuccess();
    }


    public static String getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        return DateUtil.date2TimeStr(todayStart.getTime());
    }

    public static String getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        return DateUtil.date2TimeStr(todayEnd.getTime());
    }
}


