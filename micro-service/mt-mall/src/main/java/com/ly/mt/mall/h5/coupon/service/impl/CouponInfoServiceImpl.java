package com.ly.mt.mall.h5.coupon.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.dict.CouponCodePull;
import com.ly.mt.core.base.dict.CouponUseScope;
import com.ly.mt.core.base.dict.CouponUseType;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.coupon.CouponCode;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.coupon.service.CouponInfoService;
import com.taobao.txc.client.aop.annotation.TxcTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_COUPON_CODE;
import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_COUPON_INFO;
import static com.ly.mt.core.base.entity.ResponseCode.*;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.redis.RedisKey.*;

@Service
public class CouponInfoServiceImpl extends BaseServiceImpl implements CouponInfoService {

    private final static Logger Logger = LoggerFactory.getLogger(CouponInfoServiceImpl.class);

    @Override
    public ResponseJson saveCouponCode(String couponId) throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("coupon_id", couponId);
        json.put("user_id", userId);
        String result = callDataCenter(COUPON_CODE_GET, json);
        if (StringUtil.isEmpty(result)) {
            insert(couponId, userId);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        } else {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "已领取过该优惠券");
        }
    }

    private void insert(String couponId, String userId) {
        JSONObject json = new JSONObject();
        json.put("id",couponId);
        String couponInfoStr = callDataCenter(COUPON_INFO_GET, json);
        JSONObject jsonObject = JSONObject.parseObject(couponInfoStr);
        String validity_day = jsonObject.getString("validity_day");
        String nowTimeStr = DateUtil.getNowTimeStr();
        if(StringUtil.isEmpty(validity_day) || "0".equals(validity_day)){
            //说明优惠券领取后使用的过期时间是依据优惠券的过期时间。
            json.put("Invalid_time",jsonObject.getString("end_time"));
        }else{
            //说明优惠券领取后使用的过期时间是依据优惠券的有效期和领取时间来计算出过期时间。
            int day = Integer.parseInt(validity_day);
            String endTime = DateUtil.getAfterTimeDate(day * 24);
            json.put("Invalid_time",endTime);
        }
        json.put("coupon_id", couponId);
        json.put("user_id", userId);
        json.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_INFO));
        json.put("pull_status", "2");
        json.put("pull_time", nowTimeStr);
        json.put("use_status", "1");
        json.put("create_time", nowTimeStr);
        callDataCenter(COUPON_CODE_INSERT, json);
    }

    /**
     * 批量领取优惠券接口处理层
     *
     * @param couponIds 优惠券id的集合
     * @param userId    用户id
     * @param times     单张优惠券允许被每个用户领取的次数限制
     * @return
     * @throws Exception
     */
    @Override
    @TxcTransaction
    public ResponseJson batchDrawCoupons(String couponIds, String userId, String times) throws Exception {
        String[] couponIdsArr = couponIds.split(",");
        List<String> couponList = new ArrayList<>();
        for (String str : couponIdsArr) {
            couponList.add(str);
        }
        JSONObject json = new JSONObject();
        json.put("couponIds", couponIds);
        json.put("userId", userId);
        String result = callDataCenter(COUPON_CODE_GET_BY_USERID_AND_COUPONIDS, json);
        List<Map> maps = JSONObject.parseArray(result, Map.class);
        if (maps != null && maps.size() > 0) {
            Map<String, Integer> couponMap = new ConcurrentHashMap<>(3);
            for (Map map : maps) {
                String couponId = String.valueOf(map.get("couponId"));
                String couponTimes = String.valueOf(map.get("times"));
                couponMap.put(couponId, Integer.parseInt(couponTimes));
            }
            for (String couponId : couponList) {
                Integer timesTemp = couponMap.get(couponId) == null ? 0 : couponMap.get(couponId);
                if (Integer.parseInt(times) > timesTemp) {
                    insert(couponId, userId);
                } else {
                    Logger.info("===================优惠券已经领取过了====================");
                    throw new Exception("优惠券您已经领取完啦！");
                }
            }
        } else {
            for (String couponId : couponList) {
                insert(couponId, userId);
            }
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson redemptionCode(String couponCode) throws Exception {
        JSONObject json = new JSONObject();
        json.put("user_id", getLoginUserId());
        json.put("coupon_code", couponCode);
        json.put("pull_time", DateUtil.getNowTimeStr());
        String result = callDataCenter(COUPON_CODE_UPDATE_BY_CODE, json);
        if (StringUtil.isNotEmpty(result)) {
            if (Integer.parseInt(result) > 0) {
                json.put("showFlag", true);
                json.put("info", "兑换成功！");
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
            }
        }
        json.put("showFlag", false);
        json.put("info", "该兑换码已使用或不存在！");
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    @Override
    public ResponseJson reuseRedemptionCode(String couponCode, String userId) throws Exception {
        //首先根据用户id,兑换码查询redis是否已经存在记录。如果存在直接返回已经兑换。
        String userCouponRedis = redisService.get(REDIS_COUPON_CODE_REDEMPTION, userId + "_" + couponCode);
        if (StringUtil.isEmpty(userCouponRedis)) {
            JSONObject json = new JSONObject();
            json.put("user_id", userId);
            json.put("coupon_code", couponCode);
            //缓存没有，就去数据库里查询。看查询结果才知道是否真的没有兑换。
            String result = callDataCenter(COUPON_CODE_GET_BY_USERID_AND_COUPONCODE, json);
            Logger.info("根据userId:" + userId + ",couponCode: " + couponCode + "得到的数据result：" + result);
            if (StringUtil.isEmpty(result)) {
                //如果查询出确实没有。则可以兑换。
                json.put("pull_time", DateUtil.getNowTimeStr());
                //由于是一个兑换码，兑换码对应的事一张优惠券。所以要先去查库获取一个coupon_code数据。根据id更新。
                //1.获取一张兑换码数据
                String couponStr = callDataCenter(COUPON_CODE_GET_ONE, json);
                Logger.info("查询数据中心获取一张兑换码数据：{}", couponStr);
                CouponCode couponCodeVo = JSONObject.parseObject(couponStr, CouponCode.class);
                json.put("id", couponCodeVo.getId());
                json.put("pull_status", "2");
                json.put("use_status", "1");
                callDataCenter(COUPON_CODE_UPDATE, json);
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_COUPON_CODE_PULL_SUCCESS, "兑换成功");
            }
            //如果查询出来有。则写入redis,直接返回。
            redisService.set(REDIS_COUPON_CODE_REDEMPTION, userId + "_" + couponCode, couponCode, 7L, TimeUnit.DAYS);
        }
        //如果查询出来有。则直接返回。
        return ResponseUtil.getResponseMsg(RESPONSE_CODE_COUPON_CODE_PULL_FAILURE, "兑换失败");
    }

    @Override
    public ResponseJson getUserCouponList() throws Exception {
        String userId = getLoginUserId();
        if (StringUtil.isEmpty(userId)) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_NOT_LOGIN);
        }
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        String result = callDataCenter(COUPON_INFO_GET_BY_USER_ID, json);
        JSONArray array = JSONObject.parseArray(result);
        List list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", node.getString("id"));
            jsonObject.put("couponName", node.getString("coupon_name"));
            jsonObject.put("startTime", DateUtil.timeFormat(node.getString("start_time")));
            jsonObject.put("endTime", DateUtil.timeFormat(node.getString("end_time")));
            jsonObject.put("denomination", node.getString("denomination"));
            jsonObject.put("discountRate", node.getString("discount_rate"));
            jsonObject.put("limitType", node.getString("limit_type"));
            jsonObject.put("startFee", node.getString("start_fee"));
            jsonObject.put("remark", node.getString("remark"));
            jsonObject.put("createrId", node.getString("creater_id"));
            jsonObject.put("createTime", node.getString("create_time"));
            list.add(jsonObject);
        }
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                Double denomination1 = o1.getDouble("denomination") == null ? 0.0 : o1.getDouble("denomination");
                Double denomination2 = o2.getDouble("denomination") == null ? 0.0 : o2.getDouble("denomination");
                if (denomination1 - denomination2 > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        json.put("data", list);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }


    @Override
    public ResponseJson createNewCustomerCoupons(String userId) throws Exception {
        //1.先捞取当前 有效的，配置好的，新人优惠券。
        JSONObject json = new JSONObject();
        String result = callDataCenter(COUPON_INFO_FOR_NEW_USER, json);
        if (result == null) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "暂无新人优惠券");
        }
        JSONArray array = JSONObject.parseArray(result);
        if (array == null || array.size() < 1) {
            return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
        }
        //2.拼装数据
        for (int i = 0; i < array.size(); i++) {
            JSONObject node = array.getJSONObject(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_CODE));
            jsonObject.put("coupon_id", node.getString("id"));
            jsonObject.put("user_id", userId);
            jsonObject.put("pull_status", "2");
            jsonObject.put("pull_time", DateUtil.getNowTimeStr());
            String validityDay = node.getString("validity_day");
            if(StringUtil.isEmpty(validityDay)){
                jsonObject.put("invalid_time", node.getString("end_time"));
            }else{
                Date date = DateUtil.offsetDate(new Date(), Integer.valueOf(validityDay));
                jsonObject.put("invalid_time", DateUtil.date2TimeStr(date));
            }
            jsonObject.put("use_status", "1");
            jsonObject.put("create_time", DateUtil.getNowTimeStr());
            //3.写表
            callDataCenter(COUPON_CODE_INSERT, jsonObject);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson createCouponInfo(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        jsonObject.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_INFO));
        jsonObject.put("create_time", DateUtil.getNowTimeStr());
        String validity_day = jsonObject.getString("validity_day");
        Object start_time = jsonObject.get("start_time");
        if (start_time == null || StringUtil.isEmpty(start_time.toString())) {
            jsonObject.put("start_time", DateUtil.getNowTimeStr());
        }
        if (StringUtil.isEmpty(validity_day)) {
            //如果没有传validity_day参，则用结束时间开始时间计算出.
            long validityDay = DateUtil.getDaySub(jsonObject.getString("start_time"), jsonObject.getString("end_time"));
            jsonObject.put("validity_day", validityDay + "");
        }else{
            jsonObject.put("validity_day", validity_day);
        }

        callDataCenter(COUPON_INFO_INSERT, jsonObject);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson receiveNewCustomerCoupons(String userId) throws Exception {
        //1.判断用户是否已经领取过
        String collectionNumber = redisService.get(COUPON_USER_SPREE_COLLECTION, userId);
        if (StringUtil.isEmpty(collectionNumber)) {
            //2.判断优惠券数量是否还够
            Integer couponNumber = 50000;
            String numberStr = redisService.get(COUPON_NEW_USER_SPREE);
            //如果获取不到数量，则说明没有设置，则设置为5万套，158天
            if (StringUtil.isEmpty(numberStr)) {
                redisService.set(COUPON_NEW_USER_SPREE, "50000", 158, TimeUnit.DAYS);
            } else {
                couponNumber = Integer.parseInt(numberStr);
            }
            if (couponNumber < 1) {
                return ResponseUtil.getResponseCode(RESULT_CODE_COUPON_NUMBER_EMPTY);
            }
            //则查询redis得到优惠券大礼包信息
            String couponListStr = redisService.get(REDIS_COUPON_SPREE_INFO);
            if (StringUtil.isEmpty(couponListStr)) {
                couponListStr = callDataCenter(COUPON_INFO_FOR_NEW_USER_SPREE, new JSONObject());
                Logger.info("查询到的优惠券大礼包数据：" + couponListStr);
                if (StringUtil.isEmpty(couponListStr)) {
                    Logger.info("没有获取到优惠券大礼包数据。请检查数据");
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "没有获取到优惠券大礼包数据");
                }
                //将优惠券数据缓存到redis
                redisService.set(REDIS_COUPON_SPREE_INFO, couponListStr, 1, TimeUnit.DAYS);
            }
            List<CouponInfo> couponInfoList = JSONObject.parseArray(couponListStr, CouponInfo.class);
            //根据userId,优惠券id查询该用户是否已经领取
            List<Long> couponIdList = new ArrayList<>(couponInfoList.size());
            for (CouponInfo couponInfo : couponInfoList) {
                couponIdList.add(Long.parseLong(couponInfo.getId()));
            }
            JSONObject requestJson = new JSONObject(2);
            requestJson.put("userId", userId);
            requestJson.put("couponIdList", couponIdList);
            String pullFlag = callDataCenter(COUPON_CODE_QUERY_BY_USERID_AND_COUPONID, requestJson);
            Logger.info("查询用户领取优惠券大礼包的结果：" + pullFlag);
            if (pullFlag.equals("false")) {
                //如果尚未领取则，则更新信息到coupon_code表。
                requestJson.remove("couponIdList");
                requestJson.put("couponListStr", couponListStr);
                callDataCenter(COUPON_CODE_UPDATE_USER_INFO, requestJson);
                //更新用户领取记录redis,添加当前用户进去。有效期一个月
                redisService.set(COUPON_USER_SPREE_COLLECTION, userId, userId, 30, TimeUnit.DAYS);
                couponNumber--;
                redisService.set(COUPON_NEW_USER_SPREE, couponNumber + "");
            } else {
                return ResponseUtil.getResponseCode(RESULT_CODE_COUPON_IS_TOKED);
            }
        } else {
            return ResponseUtil.getResponseCode(RESULT_CODE_COUPON_IS_TOKED);
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson getCustomerCouponsSpree() throws Exception {
        //则查询redis得到优惠券大礼包信息
        String couponListStr = redisService.get(REDIS_COUPON_SPREE_INFO);
        if (StringUtil.isEmpty(couponListStr)) {
            couponListStr = callDataCenter(COUPON_INFO_FOR_NEW_USER_SPREE, new JSONObject());
            Logger.info("查询到的优惠券大礼包数据：" + couponListStr);
            if (StringUtil.isEmpty(couponListStr)) {
                Logger.info("没有获取到优惠券大礼包数据。请检查数据");
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, "没有获取到优惠券大礼包数据");
            }
            //将优惠券数据缓存到redis
            redisService.set(REDIS_COUPON_SPREE_INFO, couponListStr, 1, TimeUnit.DAYS);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponListStr);
    }


    @Override
    public ResponseJson getShareCoupon(String skuId) throws Exception {
        String userId = getLoginUserId();
        //查询该用户是否第一次分享
        String redisShare = redisService.get(ENTITY_USER_SHARE_REDIS, userId);
        if (null == redisShare || "nil".equals(redisShare)) {
            redisService.set(ENTITY_USER_SHARE_REDIS, userId, "1");
            //如果第一次分享，则生成一张优惠券并为用户自动领取
            JSONObject couponJson = new JSONObject();
            String couponId = SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_INFO);
            couponJson.put("id", couponId);
            couponJson.put("start_time", DateUtil.getNowTimeStr());
            couponJson.put("coupon_name", "MOTI S优惠券(只限蓝牙使用)");
            couponJson.put("end_time", "2019-08-22 23:59:59");
            long validityDay = DateUtil.getDaySub(couponJson.getString("start_time"), couponJson.getString("end_time"));
            couponJson.put("validity_day", validityDay + "");
            couponJson.put("denomination", "130");
            couponJson.put("limit_type", CouponUseScope.COUPON_FOR_PART_GOODS.getId());
            couponJson.put("coupon_use_system", "10");//优惠券面向的系统 10:到家C
            couponJson.put("coupon_type", "30");//普通优惠券
            couponJson.put("remark", "蓝牙预售，用户分享优惠券,针对蓝牙产品");//优惠券描述
            couponJson.put("creater_id", userId);//优惠券创建人
            couponJson.put("create_time", DateUtil.getNowTimeStr());//创建时间
            callDataCenter(COUPON_INFO_INSERT, couponJson);
            //帮用户领取
            JSONObject couponCodeJson = new JSONObject();
            couponCodeJson.put("id", SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_COUPON_CODE));
            couponCodeJson.put("coupon_id", couponId);
            couponCodeJson.put("user_id", userId);
            couponCodeJson.put("pull_status", CouponCodePull.COUPON_CODE_PULL_STATUS_PULLED.getCode());
            couponCodeJson.put("pull_time", DateUtil.getNowTimeStr());
            couponCodeJson.put("use_status", CouponUseType.COUPON_NOT_USED.getId());
            couponCodeJson.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(COUPON_CODE_INSERT, couponJson);
            //插入一条分享记录

            JSONObject userShareRecordJson = new JSONObject();
            userShareRecordJson.put("id", SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_SHARE));
            userShareRecordJson.put("sku_id", skuId);
            userShareRecordJson.put("user_id", getLoginUserId());
            userShareRecordJson.put("share_count", "1");
            userShareRecordJson.put("create_time", DateUtil.getNowTimeStr());
            callDataCenter(USER_SHARE_RECORD_INSERT, userShareRecordJson);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, couponJson);
        } else {
            redisService.set(ENTITY_USER_SHARE_REDIS, userId, Integer.parseInt(redisShare) + 1 + "");
            JSONObject updUserShareRecordJson = new JSONObject();
            updUserShareRecordJson.put("user_id", getLoginUserId());
            updUserShareRecordJson.put("sku_id", skuId);
            callDataCenter(USER_SHARE_RECORD_ADD, updUserShareRecordJson);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, "优惠券已被领取");
        }
    }
}
