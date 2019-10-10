package com.ly.mt.order.server.border.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.dict.ImageVerifyCodeType;
import com.ly.mt.core.base.dict.PicturePlaceholder;
import com.ly.mt.core.base.entity.ResponseCode;
import com.ly.mt.core.base.entity.purchase.ShopPurchasesDiscount;
import com.ly.mt.core.base.entity.shop.*;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserUpdateLoginNameLogs;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.JsonUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.redis.RedisKey;
import com.ly.mt.order.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.order.server.border.mapper.BOrderMyInfoServiceMapper;
import com.ly.mt.order.server.border.service.BOrderMyInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.ly.mt.core.base.dict.DefaultAddress.DEFAULT_ADDRESS;
import static com.ly.mt.core.base.dict.DefaultAddress.DEFAULT_ADDRESS_NOT;
import static com.ly.mt.core.base.dict.PrimaryKey.SHOP_ADDRESS;
import static com.ly.mt.core.base.dict.PrimaryKey.USER_UPDATE_LOGIN_NAME_LOGS;
import static com.ly.mt.core.redis.RedisKey.IMAGE_VERIFY_CODE_BIND_MOBILE;
import static com.ly.mt.core.redis.dict.AlSmsTemplate.SMS_TEMPLATE_CODE_MAll_H5_BIND_MOBILE;
import static com.ly.mt.core.redis.dict.DynamicCodeType.DYNAMIC_CODE_TYPE_MALL_H5_BIND_MOBILE;

/**
 * 我的模块
 *
 * @author zhanglifeng
 * @date 2019-06-18
 */
@Service("bOrderMyInfoServiceImpl")
public class BOrderMyInfoServiceImpl extends BaseServiceImpl implements BOrderMyInfoService {

    @Resource
    private BOrderMyInfoServiceMapper bOrderMyInfoServiceMapper;


    @Override
    public JSONObject getShopPurchaseList(String json) throws Exception {
        Map<String, String> mapParam = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
//        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject result = new JSONObject();
        Long id = null;
        int page = 1;
        int rows = 1;
        if (!"".equals(mapParam.get("id"))) {
            id = Long.parseLong(mapParam.get("id"));
        }
        if (mapParam.get("page") != null) {
            page = Integer.parseInt(mapParam.get("page"));
        }
        if (mapParam.get("rows") != null) {
            rows = Integer.parseInt(mapParam.get("rows"));
        }
        Long userId = Long.parseLong(getLoginUserId(json));
        Long shopId = Long.parseLong(getLoginShopId(json));
        PageHelper.startPage(page, rows);
        List<ShopPurchases> shopPurchaseList = bOrderMyInfoServiceMapper.getShopPurchaseListByPrimaryKey(shopId, userId, id);
        if (null == shopPurchaseList || shopPurchaseList.size() == 0) {
            result.put("total", 0);
            result.put("rows", null);
            return JsonUtil.getSuccessJson(result);
        }
        PageInfo<ShopPurchases> pageInfo = new PageInfo<>(shopPurchaseList);
        ShopPurchaseVo shopPurchaseVo;
        List<ShopPurchaseVo> shopPurchaseVoList = new ArrayList<>();
        for (ShopPurchases shopPurchases : shopPurchaseList) {
            shopPurchaseVo = new ShopPurchaseVo();
            shopPurchaseVo.setShopPurchases(shopPurchases);
            //List<ShopPurchasesItems> shopPurchasesItems = bOrderMyInfoServiceMapper.selectShopPurchaseItemListByPurchaseId(Long.parseLong(shopPurchases.getId()));
            List<ShopPurchasesItemVo> shopPurchasesItemVoList = bOrderMyInfoServiceMapper.selectShopPurchaseItemListAndPictureByPurchaseId(Long.parseLong(shopPurchases.getId()));
            //图片占位图
            if (shopPurchasesItemVoList != null && shopPurchasesItemVoList.size() > 0) {
                for (ShopPurchasesItemVo vo : shopPurchasesItemVoList) {
                    if (null == vo.getSpuPictureUrl() || "".equals(vo.getSpuPictureUrl())) {
                        vo.setSpuPictureUrl(PicturePlaceholder.PICTURE_PLACEHOLDER_ORDER_GOODS_DETAIL.getId());
                    }
                }
            }
            shopPurchaseVo.setShopPurchasesItemsList(shopPurchasesItemVoList);
            int skuNum = 0;
            for (ShopPurchasesItemVo shopPurchasesItems1 : shopPurchasesItemVoList) {
                skuNum = skuNum + Integer.parseInt(shopPurchasesItems1.getSkuNum());
            }
            shopPurchaseVo.setSkuNumber(skuNum);
            List<ShopPurchasesDiscount> shopPurchasesDiscountList = bOrderMyInfoServiceMapper.getShopPurchaseDiscountByPurchaseId(Long.parseLong(shopPurchases.getId()), userId, shopId);
            shopPurchaseVo.setDiscountList(shopPurchasesDiscountList);
            shopPurchaseVoList.add(shopPurchaseVo);
        }

        result.put("total", pageInfo.getTotal());
        result.put("rows", shopPurchaseVoList);
        return JsonUtil.getSuccessJson(result);
    }

    @Override
    public JSONObject getShopPurchaseStack(String json) throws Exception {
        Long userId = Long.parseLong(getLoginUserId(json));
        Long shopId = Long.parseLong(getLoginShopId(json));

        Map<Object, Object> resultMap = new HashMap<>();

        List<ShopStocks> shopStocks = bOrderMyInfoServiceMapper.selectShopStocksByShopId(shopId);
        //归类 spu相同的要归类
        final Map<String, List<ShopStocks>> collect = shopStocks.stream().collect(Collectors.groupingBy(ShopStocks::getSpuId));
        List<ShopStockVo> shopStockVoList = new LinkedList<>();
        for (Map.Entry<String, List<ShopStocks>> entry : collect.entrySet()) {
            String spuId = entry.getKey();
            ShopStockVo vo = new ShopStockVo();
            ShopStocks spuInfo = bOrderMyInfoServiceMapper.selectSpuInfoBySpuId(Long.parseLong(spuId));
            vo.setSpuName(spuInfo.getSpuName());
            vo.setSpuId(spuId);
            vo.setSpuPrice(spuInfo.getSpuPrice());
            List<ShopStocks> shopStocks1 = bOrderMyInfoServiceMapper.selectSpuPictureBySouId(Long.parseLong(spuId));
            if (shopStocks1 != null && shopStocks1.size() > 0) {
                vo.setSpuPictureUrl((shopStocks1.get(0).getSpuPictureUrl() == null || "".equals(shopStocks1.get(0).getSpuPictureUrl())) ?
                        PicturePlaceholder.PICTURE_PLACEHOLDER_ORDER_GOODS_DETAIL.getId() :
                        shopStocks1.get(0).getSpuPictureUrl());
            }
            vo.setStocks(entry.getValue());
            shopStockVoList.add(vo);
        }
        return JsonUtil.getSuccessJson(shopStockVoList);
    }

    @Override
    public JSONObject getMyShopCouponInfo(String json) throws Exception {
        Long userId = Long.parseLong(getLoginUserId(json));
        Long shopId = Long.parseLong(getLoginShopId(json));
        List<ShopCoupon> shopCouponList = bOrderMyInfoServiceMapper.getShopCouponByUserIdAndShopId(userId, shopId);
        return JsonUtil.getSuccessJson(shopCouponList);
    }

    @Override
    public JSONObject getShopDefaultAddress(String json) throws Exception {
        String shopId = getLoginShopId(json);
        ShopAddress shopAddress = bOrderMyInfoServiceMapper.getShopDefaultAddressByShopId(Long.parseLong(shopId));
        return JsonUtil.getSuccessJson(shopAddress);
    }

    @Override
    public JSONObject getShopAddressById(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = jsonObject.get("id").toString();
        ShopAddress shopAddress = bOrderMyInfoServiceMapper.getShopAddressById(Long.parseLong(id));
        return JsonUtil.getSuccessJson(shopAddress);
    }

    @Override
    public JSONObject updateShopAddress(String json) throws Exception {
        ShopAddress shopAddress = JSONObject.parseObject(json, ShopAddress.class);
        if (shopAddress.getIsDefault().equals(DEFAULT_ADDRESS.getId())) {
            //则要先更新默认地址都为0。
            bOrderMyInfoServiceMapper.updateShopNotDefaultAddress(Long.parseLong(getLoginUserId(json)), Long.parseLong(getLoginShopId(json)), Integer.parseInt(DEFAULT_ADDRESS_NOT.getId()), null);
        }
        bOrderMyInfoServiceMapper.updateShopAddressByPrimaryKey(shopAddress);
        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject updateLoginName(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String userId = getLoginUserId(json);
        String loginName = jsonObject.get("loginName").toString();
        UserUpdateLoginNameLogs updateTimes = bOrderMyInfoServiceMapper.getUpdateTimesByUserId(Long.parseLong(userId));
        if (updateTimes != null && Integer.parseInt(updateTimes.getTimes()) > 0) {
            jsonObject.put("falg", "false");
            jsonObject.put("msg", "您已经修改过一次用户名！");
        } else {
            //校验用户名是否已经存在
            String exists = bOrderMyInfoServiceMapper.queryLoginNameExists(loginName);
            if (exists != null) {
                jsonObject.put("falg", "false");
                jsonObject.put("msg", "用户名已存在！");
                return JsonUtil.getSuccessJson(jsonObject);
            }
            User user = new User();
            user.setId(userId);
            user.setLoginName(loginName);
            bOrderMyInfoServiceMapper.updateUserInfoByPrimaryKey(user);
            UserUpdateLoginNameLogs userUpdateLoginNameLogs = new UserUpdateLoginNameLogs();
            userUpdateLoginNameLogs.setId(SnowflakeUtil.getPrimaryKey(USER_UPDATE_LOGIN_NAME_LOGS));
            userUpdateLoginNameLogs.setUserId(userId);
            userUpdateLoginNameLogs.setTimes("1");
            userUpdateLoginNameLogs.setCreateTime(DateUtil.getNowTimeStr());
            userUpdateLoginNameLogs.setLoginName(loginName);
            bOrderMyInfoServiceMapper.insertUpdateLogs(userUpdateLoginNameLogs);
        }
        return JsonUtil.getSuccessJson(jsonObject);
    }

    @Override
    public JSONObject userAccountInfo(String json) throws Exception {
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        ShopInfo shopInfo = bOrderMyInfoServiceMapper.getShopInfoByPrimaryKey(Long.parseLong(shopId));
        User user = bOrderMyInfoServiceMapper.getUserByByPrimaryKey(Long.parseLong(userId));
        Map<String, String> map = new HashMap<>(4);
        if (user.getWxOpenId() != null && StringUtil.isNotEmpty(user.getWxOpenId())) {
            map.put("wxFlag", "true");
        } else {
            map.put("wxFlag", "false");
        }
        String mobile = shopInfo.getMobile();
        if (mobile != null && StringUtil.isNotEmpty(mobile)) {
            String m1 = mobile.substring(0, 3);
            String m2 = mobile.substring(8, 11);
            map.put("mobile", m1 + "*****" + m2);
        }
        if (shopInfo.getStatus().equals("0")) {
            map.put("storeCertification", "true");
        } else {
            map.put("storeCertification", "false");
        }
        return JsonUtil.getSuccessJson(map);
    }

    @Override
    public JSONObject getShopAddressList(String json) throws Exception {
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        List<ShopAddress> shopAddressList = bOrderMyInfoServiceMapper.getShopAddressByUserIdAndShopId(Long.parseLong(userId), Long.parseLong(shopId));
        return JsonUtil.getSuccessJson(shopAddressList);
    }

    @Override
    public JSONObject addShopAddress(String json) throws Exception {
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        ShopAddress shopAddress = JSONObject.parseObject(json, ShopAddress.class);
        shopAddress.setId(SnowflakeUtil.getPrimaryKey(SHOP_ADDRESS));
        shopAddress.setUserId(userId);
        shopAddress.setShopId(shopId);
        shopAddress.setValidStatus("1");
        shopAddress.setCreateTime(DateUtil.getNowTimeStr());
        if (shopAddress.getIsDefault().equals(DEFAULT_ADDRESS.getId())) {
            //则要先更新默认地址都为0。
            bOrderMyInfoServiceMapper.updateShopNotDefaultAddress(Long.parseLong(userId), Long.parseLong(shopId), Integer.parseInt(DEFAULT_ADDRESS_NOT.getId()), null);
        }
        bOrderMyInfoServiceMapper.insertShopAddress(shopAddress);
        return JsonUtil.getSuccessJson(shopAddress);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateDefaultShopAddress(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String id = jsonObject.get("id").toString();
        String userId = getLoginUserId(json);
        String shopId = getLoginShopId(json);
        bOrderMyInfoServiceMapper.updateShopNotDefaultAddress(Long.parseLong(userId), Long.parseLong(shopId), Integer.parseInt(DEFAULT_ADDRESS_NOT.getId()), null);
        bOrderMyInfoServiceMapper.updateShopNotDefaultAddress(Long.parseLong(userId), Long.parseLong(shopId), Integer.parseInt(DEFAULT_ADDRESS.getId()), Long.parseLong(id));
        return JsonUtil.getSuccessJson();
    }


    @Override
    public JSONObject modifyMobile(String json) throws Exception {
        JSONObject jsonReturn = new JSONObject();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String mobile = jsonObject.get("mobile").toString();
        String dynamicCode = jsonObject.get("dynamicCode").toString();
        String verifyCode = jsonObject.get("verifyCode").toString();

        //首先校验手机号是否已经注册过。该手机号与当前绑定的手机号相同
        String loginUserMobile = getLoginUserMobile(json);
        if (mobile.equals(loginUserMobile)) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_MOBILE_SAME_BIND);
        }
        //该手机号已绑定其它帐号
        int mobileCount = bOrderMyInfoServiceMapper.countUserNumByMobile(mobile);
        if (mobileCount > 0) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_MOBILE_OTHER_BIND);
        }

        String code = redisServer.get(DYNAMIC_CODE_TYPE_MALL_H5_BIND_MOBILE.getRedisKey(), mobile);
        if (code == null) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_DYNAMIC_CODE_INVALID);
        }
        if (!code.equals(dynamicCode)) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_DYNAMIC_CODE_ERROR);
        }

        //检验图片验证码
        String redisVerifyCode = redisServer.get(IMAGE_VERIFY_CODE_BIND_MOBILE,
                ImageVerifyCodeType.IMAGE_VERIFY_CODE_BIND_MOBILE.getCode());
        if (StringUtil.isEmpty(redisVerifyCode)) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_VERIFY_CODE_INVALID);
        }
        if (!verifyCode.equalsIgnoreCase(redisVerifyCode)) {
            return JsonUtil.getErrorJson(ResponseCode.RESULT_CODE_VERIFY_CODE_ERROR);
        }

        String str = bOrderMyInfoServiceMapper.validateMobileIsBind(mobile);
        if (StringUtil.isNotEmpty(str)) {
            jsonReturn.put("msg", "该手机号已绑定其它帐号");
            jsonReturn.put("flag", "false");
            return JsonUtil.getSuccessJson(jsonReturn);
        }
        User user = new User();
        user.setId(getLoginUserId(json));
        user.setMobile(mobile);
        bOrderMyInfoServiceMapper.updateUserInfoByPrimaryKey(user);
        bOrderMyInfoServiceMapper.modifyShopMobile(Long.parseLong(getLoginUserId(json)), mobile);
        jsonReturn.put("msg", "更换成功");
        jsonReturn.put("flag", "true");
        return JsonUtil.getSuccessJson(jsonReturn);
    }


}
