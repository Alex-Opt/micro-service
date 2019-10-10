package com.ly.mt.order.server.coupon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.coupon.CouponCode;
import com.ly.mt.core.base.entity.coupon.CouponInfo;
import com.ly.mt.core.base.util.*;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.coupon.mapper.CouponInfoServiceMapper;
import com.ly.mt.order.server.coupon.service.CouponInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ly.mt.core.base.dict.PrimaryKey.COUPON_CODE;
import static com.ly.mt.core.base.dict.PrimaryKey.COUPON_INFO;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_COUPON_RECEIVED;
import static com.ly.mt.core.base.entity.ResponseCode.RESULT_CODE_SYSTEM_ERROR;

@Service
public class CouponInfoServiceImpl extends BaseServiceImpl implements CouponInfoService {


    private final static Logger Logger = LoggerFactory.getLogger(CouponInfoServiceImpl.class);
    @Resource
    CouponInfoServiceMapper couponInfoMapper;

    @Override
    public JSONObject saveCouponCode(String json) throws Exception {
        CouponCode entity = JSONObject.parseObject(json, CouponCode.class);
        try {
            CouponCode couponCode = couponInfoMapper.queryCouponCodeByCouponIdAndUserId(entity.getCouponId(), entity.getUserId());
            if (couponCode == null) {
                entity.setId(SnowflakeUtil.getPrimaryKey(COUPON_CODE));
                entity.setCouponCode(StringUtil.getRandomIntByLength(10));
                entity.setPullStatus("2");
                entity.setPullTime(DateUtil.getNowTimeStr());
                entity.setUseStatus("1");
                entity.setCreateTime(DateUtil.getNowTimeStr());
                int i = couponInfoMapper.saveCouponCode(entity);
                return JsonUtil.getSuccessJson(i);
            } else {
                return JsonUtil.getErrorJson(RESULT_CODE_COUPON_RECEIVED);
            }
        } catch (Exception e) {
            Logger.error("领取优惠券失败，异常={}", e.getMessage(), e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public List<CouponInfo> getValidCouponListByIds(List<String> ids) {
        return couponInfoMapper.batchQueryByCouponId(ids, DateUtil.getNowTimeStr());
    }

    /**
     * 查询用户领取的在过期时间内优惠券
     *
     * @param ids
     * @return
     */
    @Override
    public List<CouponInfo> getValidCouponListFromCouponCodeByIds(List<String> ids, Long userId) {
        String nowTimeStr = DateUtil.getNowTimeStr();
        List<CouponCode> couponCodes = couponInfoMapper.queryCouponCodeListByCouponIdList(ids, nowTimeStr, userId);
        if (couponCodes != null && couponCodes.size() > 0) {
            Set couponIdsSet = new HashSet();
            for (CouponCode couponCode : couponCodes) {
                couponIdsSet.add(couponCode.getCouponId());
            }
            List<String> couponIdList = new ArrayList<>();
            couponIdList.addAll(couponIdsSet);
            return couponInfoMapper.batchQueryByCouponId(couponIdList, nowTimeStr);
        }
        return null;
    }

    /**
     * 后台专用-优惠券兑换码生成接口，目前使用方法是测试类调用。
     *
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> generateRedemptionCode() throws Exception {
        // 生成的数量：num
        int num = 3;
        // 优惠券金额:denomination
        String denomination = "2";
        //优惠券名字:couponName
        String couponName = "2元通用优惠券兑换码";
        //有效日期开始
        String startTime = DateUtil.getNowTimeStr();
        //过期时间的小时数，目前设置为一周
        Integer hour = 7 * 24;
        //有效日期结束
        String endTime = DateUtil.getAfterTimeDate(hour);
        //最低订单金额
        String startFee = "0";
        //生成优惠券表主键
        String id = SnowflakeUtil.getPrimaryKey(COUPON_INFO);
        /*================================================数据初始化完成=============================================*/
        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setId(id);
        couponInfo.setStartTime(startTime);
        couponInfo.setEndTime(endTime);
        couponInfo.setCouponName(couponName);
        couponInfo.setDenomination(denomination);
        //优惠券是全品类的
        couponInfo.setLimitType("1");
        couponInfo.setStartFee(startFee);
        couponInfo.setCreateTime(startTime);
        //循环生成优惠码：coupon_code，优惠码设置和主键一样。
        List<CouponCode> ccList = new ArrayList<>(num);
        CouponCode cc;
        List<String> couponCodeList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            String couponId = SnowflakeUtil.getPrimaryKey(COUPON_CODE);
            cc = new CouponCode();
            cc.setId(couponId);
            String couponCode = RedeemCodeUtils.createBigStrOrNumberRadom(6);
            cc.setCouponCode(couponCode);
            cc.setCouponId(id);
            cc.setPullStatus("1");
            cc.setUseStatus("1");
            cc.setCreateTime(startTime);
            ccList.add(cc);
            couponCodeList.add(couponCode);
        }
        batchGenerateCouponCode(couponInfo, ccList);
        return couponCodeList;
    }

    @Transactional(rollbackFor = Exception.class)
    void batchGenerateCouponCode(CouponInfo couponInfo, List<CouponCode> ccList) throws Exception {
        couponInfoMapper.insert(couponInfo);
        couponInfoMapper.batchInsertCouponCode(ccList);
        Logger.info("=================优惠券码生成完毕！===========================");
    }


    @Override
    public JSONObject redemptionCode(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String couponCode = jsonObject.get("couponCode").toString();
        String loginUserId = getLoginUserId(json);
        String nowTimeStr = DateUtil.getNowTimeStr();
        int i = couponInfoMapper.updateConponUserInfoByCouponCode(loginUserId, nowTimeStr, couponCode);
        if (i > 0) {
            jsonObject.put("showFlag", true);
            jsonObject.put("info", "兑换成功！");

        } else {
            jsonObject.put("showFlag", false);
            jsonObject.put("info", "该兑换码已使用！");
        }
        return JsonUtil.getSuccessJson(jsonObject);
    }

    @Override
    public JSONObject getUserCouponList(String json) throws Exception {
        String loginUserId = getLoginUserId(json);
        String nowTimeStr = DateUtil.getNowTimeStr();
        List<CouponInfo> couponInfoByUserId = couponInfoMapper.getCouponInfoByUserId(loginUserId, nowTimeStr);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("showFlag", false);
        jsonObject.put("data", couponInfoByUserId);
        return JsonUtil.getSuccessJson(jsonObject);
    }

    @Override
    public List<String> getCouponIdsByLimitType(String nowDate) {
        return couponInfoMapper.getCouponIdsByLimitType(nowDate);
    }
}
