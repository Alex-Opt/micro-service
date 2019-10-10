package com.ly.mt.activity.server.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.activity.server.config.ActivitySMSService;
import com.ly.mt.activity.server.order.mapper.HdShopAttOrderDetailMapper;
import com.ly.mt.activity.server.order.mapper.HdShopAttOrderMapper;
import com.ly.mt.activity.server.shop.mapper.HdShopAttDetailMapper;
import com.ly.mt.activity.server.user.mapper.HdActivityUserMapper;
import com.ly.mt.activity.server.user.mapper.HdUserMapper;
import com.ly.mt.activity.server.user.service.HdShopUserService;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.constant.hd.HdShopAttEnum;
import com.ly.mt.core.common.entity.hd.*;
import com.ly.mt.core.common.entity.hd.dto.HdActivityUserDto;
import com.ly.mt.core.common.entity.hd.dto.HdgoodsSkuRequestBody;
import com.ly.mt.core.common.entity.hd.dto.UserSendCOdeRequestDto;
import com.ly.mt.core.common.entity.hd.model.HdGoodsSkuModel;
import com.ly.mt.core.common.entity.hd.model.HdSmsMsg;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author panjingtian
 * @description 活动用户操作
 * @date 2019/6/14 1:47 PM
 */
@Service("hdShopUserServiceImpl")
public class HdShopUserServiceImpl implements HdShopUserService {

    private final static Logger log = LoggerFactory.getLogger(HdShopUserServiceImpl.class);
    private final  static  Long ACTIVITY_ID = 546564126l;
    /**
     * 验证码过期时间  单位 /秒
     */
    private final static long EXPIRATION_SECONDS = 90l;


    @Autowired
    private HdActivityUserMapper hdActivityUserMapper;
    @Autowired
    private HdUserMapper userMapper;
    @Autowired
    private ActivitySMSService activitySMSService;
    @Autowired
    RedisServer redisServer;
    @Autowired
    private HdShopAttOrderMapper orderMapper;
    @Autowired
    private HdShopAttOrderDetailMapper orderDetailMapper;
    @Autowired
    private HdShopAttDetailMapper detailMapper;

    /**
     * 活动用户注册
     * <p>
     * 同时注册成为c端商城用户
     *
     * @param jsonObject {@link HdActivityUserDto}
     * @return 活动用户主键id  当0时候不存在 {@link HdActivityUser}
     */
    @Override
    @Transactional
    public JSONObject registAndOrder(JSONObject jsonObject) {
        try {
            HdActivityUserDto dto = jsonObject.getObject("hdActivityUserDto", HdActivityUserDto.class);
            Long activityId = detailMapper.findActivityIdById(dto.getShopAttDetailId());
            List<HdgoodsSkuRequestBody> skus = jsonObject.getObject("hdGoodsSkuModel", List.class);
            String json = JSON.toJSONString(skus);
            List<HdgoodsSkuRequestBody> bodys = JSONArray.parseArray(json, HdgoodsSkuRequestBody.class);
            List<HdGoodsSkuModel> skuModels = bodys.stream().map(body -> {
                return HdgoodsSkuRequestBody.convert(body);
            }).collect(Collectors.toList());

            String dynamicCode = (String) jsonObject.getString("dynamicCode");
            String redisDynamicCode = redisServer.getVauel(RedisEnum.ACTIVITY_BUYER_ORDER_CODE.getKey() + dto.getPhone());
            if (StringUtils.isEmpty(redisDynamicCode) || !redisDynamicCode.equals(dynamicCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_USER_DYNAMIC_CODE_ERROR);
            }
            if (isJoin(dto.getShopAttDetailId(), dto.getPhone())) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_JOIN_REPETITION);
            }
            //先保存用户
            HdActivityUser hdActivityUser = new HdActivityUser();
            Long userId = null;
            Long uid = Long.valueOf((StringUtil.getRandomIntByLength(16)));
            List<HdUserInfo> sources = userMapper.selectByPhone(dto.getPhone());
            if (sources.size() <= 0) {
                try {
                    HdUserInfo user = new HdUserInfo(dto.getPhone(), Integer.parseInt(dto.getSex()));
                    user.setId(uid);
                    userId = Long.valueOf(userMapper.insert(user));
                } catch (Exception e) {
                    log.error("注册异常{}", e);
                }
            } else {
                userId = sources.get(0).getId();
            }

            BeanUtils.copyProperties(dto, hdActivityUser);
            hdActivityUser.setCreateTime(new Date());
            hdActivityUser.setUserStatus(HdShopAttEnum.ACTIVITY_USER_STATUS_VALID.getCode());
            hdActivityUser.setUserId(userId);
            hdActivityUser.setHdShopAttDetailId(dto.getShopAttDetailId());
            Long uId = Long.valueOf((StringUtil.getRandomIntByLength(16)));
            hdActivityUser.setId(uId);
            hdActivityUserMapper.insert(hdActivityUser);
            List<HdActivityUser> userlist = hdActivityUserMapper.findByShopAttDetailIdAndPhone(hdActivityUser.getHdShopAttDetailId(), hdActivityUser.getPhone());
            if (userlist.size() <= 0) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_JOIN_REPETITION);
            }
            //在保存订单
            String getProductCode = downOrder(skuModels, userlist.get(0).getId(), dto.getShopAttDetailId(),activityId);
            HdSmsMsg hdSmsMsg = activitySMSService.sendGetProductCode(dto.getPhone(), SmsTemplateEnum.SMS_TEMPLATE_CODE_ACTIVITY_GETPRODUCT_CODE, getProductCode);
            JSONObject result = new JSONObject();
            result.put("attUserId", userlist.get(0).getId());
            result.put("getProductCode", hdSmsMsg.getCode());
            return JsonUtil.getSuccessJson(result);
        } catch (BeansException e) {
            log.error("method={},注册用户及其下单失败，e={}","registAndOrder",e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_JOIN_REPETITION);
        }

    }


    /**
     * 验证是否参与过门店活动
     *
     * @param detailId
     * @param phone
     * @return
     */
    private boolean isJoin(Long detailId, String phone) {
        List<HdActivityUser> resultUsers = hdActivityUserMapper.findByShopAttDetailIdAndPhone(detailId, phone);
        if (resultUsers == null || resultUsers.size() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 下单操作
     * 用户的信息
     * 订单详情，金额烂七八糟的
     *
     * @return 取货码
     */
    private String downOrder(List<HdGoodsSkuModel> skus, Long attUserId, Long shooDetailId,Long activityId) {

        //先生成单号
        String orderId = IdUtil.getId(IdEnum.TRADE_ORDERS);
        //详情入库走起
        BigDecimal totalPrice = new BigDecimal(0);

        for (HdGoodsSkuModel model : skus) {
            HdShopAttOrderDetail hdShopAttOrderDetail = new HdShopAttOrderDetail();
            hdShopAttOrderDetail.setUserId(attUserId);
            hdShopAttOrderDetail.setShopAttDetailId(shooDetailId);
            hdShopAttOrderDetail.setOrderId(orderId);
            hdShopAttOrderDetail.setGoodsSkuId(model.getId());
            hdShopAttOrderDetail.setGoodsSpuId(model.getSpuId());
            Double privce = orderDetailMapper.findSkuPrice(model.getId());
            hdShopAttOrderDetail.setGoodsQuantity(model.getBuyNum());
            hdShopAttOrderDetail.setGoodsPrice(new BigDecimal(privce));
            hdShopAttOrderDetail.setCreateTime(new Date());
            hdShopAttOrderDetail.setFrontCategoryId(model.getFrontId());
            Long did = Long.valueOf(Long.valueOf((StringUtil.getRandomIntByLength(16))));
            hdShopAttOrderDetail.setId(did);
            orderDetailMapper.insert(hdShopAttOrderDetail);
            BigDecimal multiply = new BigDecimal(privce).multiply(new BigDecimal(model.getBuyNum()));
            totalPrice = totalPrice.add(multiply);
        }

        //TODO 后期项目重构活动模块此处用活动规则字典，做责任链handler进行字典匹配规则过滤，返回布尔类型
        if (activityId == ACTIVITY_ID && totalPrice.compareTo(new BigDecimal(99)) == -1){
            throw  new IllegalArgumentException("不满足99元活动规则");
        }
        String getProductCode = StringUtil.getRandomIntByLength(6);
        HdShopAttOrder hdShopAttOrder = new HdShopAttOrder();
        hdShopAttOrder.setUserId(attUserId);
        hdShopAttOrder.setOrderId(orderId);
        hdShopAttOrder.setOrderStatus(HdShopAttEnum.ACTIVITY_ORDER_STATUS_SEND.getCode());
        hdShopAttOrder.setTotalPrice(totalPrice);
        hdShopAttOrder.setCreateTime(new Date());
        hdShopAttOrder.setGetGoodsCode(getProductCode);
        hdShopAttOrder.setShopAttDetailId(shooDetailId);
        hdShopAttOrder.setId(Long.valueOf((StringUtil.getRandomIntByLength(16))));
        orderMapper.insert(hdShopAttOrder);

        return getProductCode;
    }

    /**
     * 活动买家下单发送验证码
     *
     * @param jsonObject Long activityId
     *                   Long shopId
     *                   Long shopAttDetailId
     *                   string phone
     *                   //user_status控制状态
     *
     * @return
     */
    @Override
    public JSONObject sendCode(JSONObject jsonObject) {
        try {
            UserSendCOdeRequestDto userSendCOdeRequestDto = JSONObject.toJavaObject(jsonObject, UserSendCOdeRequestDto.class);
            Long shopAttDetailId = userSendCOdeRequestDto.getShopAttDetailId();
            String phone = userSendCOdeRequestDto.getPhone();
            //是否可以注册
            Boolean flag = isRegsit(shopAttDetailId, phone);

            if (!flag) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_JOIN_REPETITION);
            }
            //未注册
            String dynamicCode = StringUtil.getRandomIntByLength(6);
            HdSmsMsg hdSmsMsg = activitySMSService.sendDynamicCode(phone, SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, dynamicCode, EXPIRATION_SECONDS);
            redisServer.setExString(RedisEnum.ACTIVITY_BUYER_ORDER_CODE.getKey() + phone, dynamicCode, EXPIRATION_SECONDS);
            return JsonUtil.getSuccessJson(hdSmsMsg);
        } catch (Exception e) {
            log.error("method={},发送下单验证码失败，e={}", "sendCode", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_USER_ORDER_DOWN_ERROR);
        }
    }

    /**
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getUserByAttIdPhone(JSONObject jsonObject) {
        try {
            Long hdShopAttDetailId = (Long) jsonObject.getObject("hdShopAttDetailId", Long.class);
            String phone = (String) jsonObject.get("phone");
            List<HdActivityUser> byShopAttDetailIdAndPhone = hdActivityUserMapper.findByShopAttDetailIdAndPhone(hdShopAttDetailId, phone);
            if (byShopAttDetailIdAndPhone.size() > 0) {
                return JsonUtil.getSuccessJson(byShopAttDetailIdAndPhone.get(0));
            }
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_USER_UNREGIST);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_USER_UNREGIST);
        }
    }


    /**
     * 查询是否注册过为活动用户
     *
     * @param shopAttDetailId
     * @param phone
     * @return 可以注册返回true
     * 已经注册了返回false
     */
    private Boolean isRegsit(Long shopAttDetailId, String phone) {
        /**
         * 1、先验证是否已经是注册用户
         * TODO先这么写死，下次活动在做更改重构，进行订单查询对比次数校验参与活动的次数
         *
         */
        List<HdActivityUser> list = hdActivityUserMapper.findByShopAttDetailIdAndPhone(shopAttDetailId, phone);
        if (list == null || list.size() > 0) {
            return false;
        }
        return true;
    }

}
