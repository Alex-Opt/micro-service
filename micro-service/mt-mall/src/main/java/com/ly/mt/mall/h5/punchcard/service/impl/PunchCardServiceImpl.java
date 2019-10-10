package com.ly.mt.mall.h5.punchcard.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.punchcard.service.PunchCardService;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import com.taobao.txc.common.TxcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import static com.ly.mt.core.base.dict.CouponCodePull.COUPON_CODE_PULL_STATUS_PULLED;
import static com.ly.mt.core.base.dict.CouponCodeUse.COUPON_CODE_USE_STATUS_NOT_USE;
import static com.ly.mt.core.base.dict.PrimaryKey.*;
import static com.ly.mt.core.base.dict.PunchCardCoupon.PUNCH_CARD_COUPON_STATUS_ENABLE;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_NOT_LOGIN;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;


/**
 * 用户打卡操作
 *
 * @author ypmu
 * @date 20190530
 */
@Service
public class PunchCardServiceImpl extends BaseServiceImpl implements PunchCardService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PunchCardServiceImpl.class);


    @TxcTransaction(appName = "moti")
    @Override
    public ResponseJson punchCard() throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalScore", "0");
        jsonObject.put("score", null);
        jsonObject.put("couponId", null);
        jsonObject.put("couponName", null);
        jsonObject.put("denomination", null);
        jsonObject.put("discountRate", null);
        jsonObject.put("startTime", null);
        jsonObject.put("endTime", null);

        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("today_start_time", getTodayStartTime());
        json.put("today_end_time", getTodayEndTime());
        //校验用户今天是否已打卡
        String result = callDataCenter(PUNCH_PIONT_DATA_BY_USERID_TODAY, json);
        if (StringUtil.isNotEmpty(result)) {
            jsonObject.put("punchCardFlag", false);
            json = new JSONObject();
            json.put("user_id", userId);
            String resultUserGrade = callDataCenter(PUNCH_PIONT_GRADE_BY_USERID, json);
            if (StringUtil.isNotEmpty(resultUserGrade)) {
                //用户打卡总积分
                jsonObject.put("totalScore", JSONObject.parseObject(resultUserGrade).getString("user_score"));
            }
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
        }

        String xid = TxcContext.getCurrentXid();
        json = new JSONObject();
        json.put("status", 1);
        //查找打卡是否有积分
        String resultPunchCard = callDataCenter(PUNCH_CARD_POINT_BY_STATUS, json);
        //如果有打卡积分，则操作
        if(StringUtil.isNotEmpty(resultPunchCard)) {
            JSONArray array = JSONObject.parseArray(resultPunchCard);
            if (array.size() > 0) {
                JSONObject node = array.getJSONObject(0);
                String resultPointConfig = punchCardPointDo(node.getString("point_config_id"), userId, xid);
                if (StringUtil.isNotEmpty(resultPointConfig)) {
                    jsonObject.put("score", JSONObject.parseObject(resultPointConfig).getString("score"));
                }
            }
        }
        json = new JSONObject();
        json.put("user_id", userId);
        String resultUserPointGrade = callDataCenter(PUNCH_PIONT_GRADE_BY_USERID, json);
        if (StringUtil.isNotEmpty(resultUserPointGrade)) {
            jsonObject.put("totalScore", JSONObject.parseObject(resultUserPointGrade).getString("user_score"));
        }

        json = new JSONObject();
        json.put("status", PUNCH_CARD_COUPON_STATUS_ENABLE.getCode());
        String resultPunchCardCoupon = callDataCenter(PUNCH_CARD_COUPON_BY_STATUS, json);
        //如果有打卡优惠，则操作
        if (StringUtil.isNotEmpty(resultPunchCardCoupon)) {
            JSONArray punchCardCouponArray = JSONObject.parseArray(resultPunchCardCoupon);
            if (punchCardCouponArray.size() > 0) {
                JSONObject punchCardCoupon = punchCardCouponArray.getJSONObject(0);
                String resultCouponInfo = punchCardCouponDo(punchCardCoupon.getString("coupon_id"), userId,xid);
                if (StringUtil.isNotEmpty(resultCouponInfo)) {
                    JSONObject couponInfoJson = JSONObject.parseObject(resultCouponInfo);
                    jsonObject.put("couponId", couponInfoJson.getString("id"));
                    jsonObject.put("couponName", couponInfoJson.getString("coupon_name"));
                    jsonObject.put("denomination", couponInfoJson.getString("denomination"));
                    jsonObject.put("discountRate", couponInfoJson.getString("discount_rate"));
                    jsonObject.put("startTime", couponInfoJson.getString("start_time"));
                    jsonObject.put("endTime", couponInfoJson.getString("end_time"));
                }
            }
        }

        String now = DateUtil.getNowTimeStr();
        json = new JSONObject();
        json.put("xid", xid);
        jsonObject.put("xid", xid);
        json.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER_PUNCH_CARD));
        json.put("user_id", userId);
        json.put("user_name", getLoginUserName());
        json.put("punch_time", now);
        json.put("create_time", now);
        callDataCenter(USER_PUNCH_CARD_INSERT, json);

        jsonObject.put("punchCardFlag", true);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, jsonObject);
    }

    @Override
    public ResponseJson queryPointDataByUserId() throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        String result = callDataCenter(PUNCH_PIONT_DATA_BY_USERID, json);
        JSONArray array = JSONObject.parseArray(result);
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            node.put("createTime", DateUtil.timeFormat(node.getString("create_time")));
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, array);
    }

    /*
     */

    /**
     * 打卡 积分操作
     *
     * @param pointConfigId
     * @param userId
     * @throws Exception
     */
    private String punchCardPointDo(String pointConfigId, String userId,String xid) throws Exception {
        JSONObject json = new JSONObject();
        //查找积分规则
        json.put("id", pointConfigId);
        String resultPointConfig = callDataCenter(POINT_CONFIG_BY_ID, json);
        //判断积分规则是否过期
        String now = DateUtil.getNowTimeStr();
        JSONObject pointConfigJson = JSONObject.parseObject(resultPointConfig);
        String bDate = pointConfigJson.getString("start_time");
        String eDate = pointConfigJson.getString("end_time");
        if (compareDate(bDate, now) || compareDate(now, eDate)) {
            return null;
        }
        //插入或修改用户积分等级表
        //1、先查找用户积分等级表 user_point_grade
        //2、将积分加上
        //3、在查找积分等级表，算出等级
        //4、如果用户积分等级表没有数据，则插入输入，如有，则修改数据
        json = new JSONObject();
        json.put("user_id", userId);
        String resultUserPointGrade = callDataCenter(PUNCH_PIONT_GRADE_BY_USERID, json);
        String userPointGradeId = null;
        int score = 0;
        if (StringUtil.isNotEmpty(resultUserPointGrade)) {
            JSONObject userPointConfigJson = JSONObject.parseObject(resultUserPointGrade);
            userPointGradeId = userPointConfigJson.getString("id");
            score = pointConfigJson.getString("score") == null ? 0 : Integer.parseInt(pointConfigJson.getString("score")) + (userPointConfigJson.getString("user_score") == null ? 0 : Integer.parseInt(userPointConfigJson.getString("user_score")));
        } else {
            score = pointConfigJson.getString("score") == null ? 0 : Integer.parseInt(pointConfigJson.getString("score"));
        }

        json = new JSONObject();
        json.put("score", score);
        String resultPointGrade = callDataCenter(POINT_GRAD_BY_SCORE, json);

        String gradeId = null;
        JSONObject punchCardPointGrade = null;
        if (StringUtil.isNotEmpty(resultPointGrade)) {
            JSONArray pointGradeArray = JSONObject.parseArray(resultPointGrade);
            if (pointGradeArray.size() > 0) {
                punchCardPointGrade = pointGradeArray.getJSONObject(0);
                gradeId = punchCardPointGrade.getString("id");
            }
        }

        if (StringUtil.isNotEmpty(resultUserPointGrade)) {
            if (StringUtil.isNotEmpty(gradeId)) {
                json = new JSONObject();
                json.put("xid", xid);
                json.put("id", userPointGradeId);
                json.put("point_grade_id", gradeId);
                json.put("user_score", score);
                json.put("modify_time", now);
                callDataCenter(PUNCH_PIONT_GRADE_UPDATE_BY_ID, json);
            }
        } else {
            if (StringUtil.isNotEmpty(gradeId)) {
                json = new JSONObject();
                json.put("xid", xid);
                json.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER_POINT_GRADE));
                json.put("user_id", userId);
                json.put("point_grade_id", gradeId);
                json.put("user_score", score);
                json.put("create_time", now);
                callDataCenter(PUNCH_PIONT_GRADE_INSERT, json);
            }
        }

        //插入用户积分流水表
        json = new JSONObject();
        json.put("xid", xid);
        json.put("id",SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER_POINT_DATA));
        json.put("user_id", userId);
        json.put("point_config_id", pointConfigId);
        json.put("score", pointConfigJson.getString("score"));
        json.put("create_time", now);
        callDataCenter(PUNCH_PIONT_DATA_INSERT, json);
        return resultPointConfig;
    }

    /**
     * 打卡优惠券操作
     *
     * @param couponId
     * @throws Exception
     */
    private String punchCardCouponDo(String couponId, String userId,String xid) throws Exception {
        JSONObject json = new JSONObject();
        //查找优惠券规则
        json.put("id", couponId);
        String resultCouponInfo = callDataCenter(COUPON_INFO_GET, json);
        //判断优惠券规则是否过期
        String now = DateUtil.getNowTimeStr();
        JSONObject couponInfoJson = JSONObject.parseObject(resultCouponInfo);
        String bDate = couponInfoJson.getString("start_time");
        String eDate = couponInfoJson.getString("end_time");
        if (compareDate(bDate, now) || compareDate(now, eDate)) {
            return null;
        }
        json = new JSONObject();
        json.put("xid", xid);
        json.put("id",SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_CODE));
        json.put("user_id", userId);
        json.put("coupon_id", couponId);
        json.put("coupon_code", StringUtil.getRandomIntByLength(10));
        json.put("pull_status", COUPON_CODE_PULL_STATUS_PULLED.getCode());
        json.put("pull_time", now);
        json.put("use_status", COUPON_CODE_USE_STATUS_NOT_USE.getCode());
        json.put("create_time", now);
        callDataCenter(COUPON_CODE_INSERT, json);
        return resultCouponInfo;
    }

    /**
     * 比较日期
     *
     * @param date1
     * @param date2
     * @return
     */
    private boolean compareDate(String date1, String date2) {
        int i = 0;
        try {
            i = DateUtil.compareDateTime(date1, date2);
        } catch (Exception e) {
            i = -1;
        }
        return i > 0 ? true : false;

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
