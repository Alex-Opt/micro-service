package com.ly.mt.activity.server.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.hd.HdOperatorInfo;
import com.ly.mt.core.common.entity.hd.HdShopInfo;
import com.ly.mt.core.common.entity.hd.request.HdOperatorRequestBody;
import com.ly.mt.core.common.entity.hd.request.HdShopInfoRequestBody;
import com.ly.mt.core.common.entity.hd.request.RegistShopActivityRequestBody;
import com.ly.mt.core.common.entity.hd.request.ShopSendRegistCodeRequestBody;
import com.ly.mt.core.common.entity.hd.vo.FrontCategoryVo;
import com.ly.mt.core.common.entity.hd.vo.FrontCategoryVoBody;
import com.ly.mt.core.common.entity.hd.vo.FrontCategoryVoFace;
import com.ly.mt.core.common.entity.hd.vo.FrontSkuVo;
import com.ly.mt.activity.server.config.ActivitySMSService;
import com.ly.mt.activity.server.shop.mapper.HdOperatorInfoMapper;
import com.ly.mt.activity.server.shop.mapper.HdShopAttDetailMapper;
import com.ly.mt.activity.server.shop.mapper.HdShopAttGoodspuMapper;
import com.ly.mt.activity.server.shop.mapper.HdShopInfoMapper;
import com.ly.mt.activity.server.shop.service.ShopManagerOperationServer;
import com.ly.mt.core.common.constant.FilePathEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.constant.ResultCodeEnum;
import com.ly.mt.core.common.constant.SmsTemplateEnum;
import com.ly.mt.core.common.entity.hd.HdShopAttDetail;
import com.ly.mt.core.common.entity.hd.dto.AttShopInfoDto;
import com.ly.mt.core.common.entity.hd.dto.HdoperatorDto;
import com.ly.mt.core.common.entity.hd.model.HdShopAttDetailModel;
import com.ly.mt.core.common.entity.hd.model.HdSmsMsg;
import com.ly.mt.core.common.entity.hd.model.ShopRegistResultModel;
import com.ly.mt.core.common.entity.hd.vo.HdShopAttDetailVo;
import com.ly.mt.core.common.entity.hd.vo.HdShopInfoDto;
import com.ly.mt.core.common.server.RedisServer;
import com.ly.mt.core.common.util.BarCodeUtil;
import com.ly.mt.core.common.util.JsonUtil;
import com.ly.mt.core.common.util.StringUtil;
import com.ly.mt.core.oss.OssServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author panjingtian
 * @description 门店管理员实现
 * <p>
 * 门店活动url
 * String shopActivityUrl = model.getShopActivityUrl() + "?activityid=" + model.getActivityId() + "&shopid=" + model.getShopId();
 * @date 2019/6/10 3:09 PM
 */
@Service(value = "shopManagerOperationImpl")
public class ShopManagerOperationImpl implements ShopManagerOperationServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShopManagerOperationImpl.class);
    //设置目前过期时间为1年
    private final static Date EXPIRE_DATA = new Date(System.currentTimeMillis() + 1 * 365 * 24 * 3600 * 1000);

    private final static long EXPIRE_PHONE_CODE = 60 * 60 * 24 * 30l;

    /**
     * 验证码过期时间  单位 /秒
     */
    private final static Long EXPIRATION_SECONDS = 150l;

    /**
     * 支持活动及其负责人重复注册活动
     */
    private final static String REPETITION_REGIST_OK = "1";

    /**
     * 不支持活动及其负责人重复注册活动
     */
    private final static String REPETITION_REGIST_NO = "0";

    /**
     * 活动门店短信服务
     */
    @Resource
    private ActivitySMSService activitySMSService;

    @Resource
    private OssServer ossServer;
    /**
     * 门店活动信息dao
     */
    @Resource
    private HdShopAttDetailMapper shopAttDetailMapper;

    @Autowired
    RedisServer redisServer;

    @Resource
    private HdShopInfoMapper shopInfoMapper;

    @Resource
    private HdOperatorInfoMapper operatorInfoMapper;

    /**
     * 活动关联的商品
     */
    @Resource
    private HdShopAttGoodspuMapper shopAttGoodspuMapper;

    /**
     * 门店活动注册手机号验证
     * 同时也验证活动有没有发布过
     *
     * @param jsonObject 约定入参
     *            phone           手机号
     *            dynamicCodeType 短信模版代码
     *            activityId      活动id
     *            flag            是否可以重复注册 0为不可以 1为可以
     * @return true false
     * <p>
     * String phone, String dynamicCodeType, Long activityId, String flag
     */
    @Override
    public JSONObject sendActivityDynamicCode(JSONObject jsonObject) {
        try {

            ShopSendRegistCodeRequestBody body = JSONObject.toJavaObject(jsonObject, ShopSendRegistCodeRequestBody.class);
            String phone = body.getPhone();
            String dynamicCode = StringUtil.getRandomIntByLength(6);
            return JsonUtil.getSuccessJson(sendDynamicCode(phone, SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, dynamicCode, EXPIRATION_SECONDS));
        } catch (Exception e) {
            LOGGER.error("method={},发送门店注册验证码失败,异常信息为{}", "sendActivityDynamicCode", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_REGIST_REPETITION);
        }
    }

    /**
     * 门店注册
     *
     * @param jsonObject hdShopAttDetailModel {@link HdShopAttDetailModel}
     * @return ShopRegistResultModel {@link ShopRegistResultModel}
     * V1.门店单管理员
     * V2.2019.07.11 增加门店多管理员注册
     * 1 注册门店活动成功
     */
    @Override
    public JSONObject registShopActivity(JSONObject jsonObject) throws Exception {
        try {
            RegistShopActivityRequestBody body = JSONObject.toJavaObject(jsonObject, RegistShopActivityRequestBody.class);
            String dynamicCode = body.getDynamicCode();
            Long detailId = null;
            List<HdShopAttDetail> shopAttDetails = shopAttDetailMapper.findByShopIdActivityId(Long.valueOf(body.getHdShopAttDetailModel().getShopId()), body.getHdShopAttDetailModel().getActivityId());
            detailId = Long.valueOf((StringUtil.getRandomIntByLength(16)));
            //注册时候存的验证码  code:dynamic:regist:12121212121
            String redisKey = SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST.getRedisKey() + body.getHdShopAttDetailModel().getManagerPhone();
            String redisCode = redisServer.getVauel(redisKey);
            if (StringUtils.isEmpty(dynamicCode) || StringUtils.isEmpty(redisCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
            }
            if (!dynamicCode.equals(redisCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
            }

            String shopActivityUrl = body.getHdShopAttDetailModel().getShopActivityUrl() + "?activityid=" + body.getHdShopAttDetailModel().getActivityId() + "&shopid=" + body.getHdShopAttDetailModel().getShopId();
            InputStream activityImage = BarCodeUtil.createBarcode(shopActivityUrl);
            String ossImageUrl = ossServer.upload(activityImage, FilePathEnum.FILE_PATH_ACTIVITY, EXPIRE_DATA);
            //短信验证吗验证逻辑
            HdShopAttDetail shopAttDetail = new HdShopAttDetail();
            BeanUtils.copyProperties(body.getHdShopAttDetailModel(), shopAttDetail);
            shopAttDetail.setShopId(Long.valueOf(body.getHdShopAttDetailModel().getShopId()));
            shopAttDetail.setCreateTime(new Date());
            shopAttDetail.setImageUrl(ossImageUrl);
            shopAttDetail.setId(detailId);
            int i = shopAttDetailMapper.insertIgnore(shopAttDetail);
            if (1 == 1) {
                ShopRegistResultModel shopRegistResultModel = ShopRegistResultModel.builder()
                        .imgUrl(ossImageUrl)
                        .build();
                HdShopAttDetailVo vo = shopAttDetailMapper.findByActivityIdPhoneShopId(body.getHdShopAttDetailModel().getActivityId(), body.getHdShopAttDetailModel().getManagerPhone(), Long.valueOf(body.getHdShopAttDetailModel().getShopId()));
                //生成token
                String token = StringUtil.getRandomIntByLength(16);
                redisServer.setExString(token, body.getHdShopAttDetailModel().getManagerPhone(), EXPIRE_PHONE_CODE);
                JSONObject result = new JSONObject();
                result.put("imgUrl", shopRegistResultModel.getImgUrl());
                result.put("shopDetail", vo);
                result.put("token", token);
                return JsonUtil.getSuccessJson(result);
            }
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_REGIST_REPETITION);
        } catch (Exception e) {
            LOGGER.error("method={},门店注册失败,异常信息为{}", "registShopActivity", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_REGIST_REPETITION);
        }
    }

    /**
     * 登录
     * 登录后生成一个session每次传
     *
     * @param jsonObject {@link HdShopAttDetailModel}
     * @return
     */
    @Override
    public JSONObject login(JSONObject jsonObject) {
        try {
            ShopSendRegistCodeRequestBody body = JSONObject.toJavaObject(jsonObject, ShopSendRegistCodeRequestBody.class);
            String phone = body.getPhone();
            String dynamicCode = body.getDynamicCode();
            Long activityId = body.getActivityId();
            String redisDynamicCode = redisServer.getVauel(RedisEnum.LOGIN_ACTIVITY_SHOP_CODE.getKey() + phone);
            if (StringUtils.isEmpty(redisDynamicCode) || !redisDynamicCode.equals(dynamicCode)) {
                return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_DYNAMIC_CODE_ERROR);
            }
            //返回当前活动的注册商户信息哦
            HdShopAttDetail hdShopAttDetail = shopAttDetailMapper.findByActivityIdPhone(activityId, phone);
            HdShopAttDetailVo hdShopAttDetailVo = new HdShopAttDetailVo();
            BeanUtils.copyProperties(hdShopAttDetail, hdShopAttDetailVo);
            JSONObject successJson = JsonUtil.getSuccessJson(hdShopAttDetailVo);

            //生成登录token
            String token = StringUtil.getRandomIntByLength(16);
            redisServer.setExString("token" + token, phone, EXPIRE_PHONE_CODE);
            successJson.put("token", token);
            return successJson;
        } catch (BeansException e) {
            LOGGER.error("method={},门店登录异常{}", "login", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_LOGIN_ERROR);
        }
    }

    /**
     * 发送登录验证码
     *
     * @param jsonObject phone 手机号
     * @return 返回验证码
     * <p>
     * redis 缓存的验证码为 login:activity:shop:code:+phone     dynamicCode
     */
    @Override
    public JSONObject sendLoginCode(JSONObject jsonObject) {

        JSONObject result = new JSONObject();
        try {
            ShopSendRegistCodeRequestBody body = JSONObject.toJavaObject(jsonObject, ShopSendRegistCodeRequestBody.class);
            String phone = body.getPhone();
            Long activityId = body.getActivityId();
            List<HdShopAttDetail> list =  shopAttDetailMapper.findByPhoneActivityId(phone,activityId);
            if (list.size() <= 0) {
                return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_LOGIN_ERROR);
            }

            String dynamicCode = StringUtil.getRandomIntByLength(6);
            sendDynamicCode(phone, SmsTemplateEnum.SMS_TEMPLATE_CODE_REGIST, dynamicCode, EXPIRATION_SECONDS);
            redisServer.setExString(RedisEnum.LOGIN_ACTIVITY_SHOP_CODE.getKey() + phone, dynamicCode, 300l);
            result.put("dynamicCode", dynamicCode);
            return JsonUtil.getSuccessJson(result);
        } catch (Exception e) {
            LOGGER.error("method={},发送门店验证码失败{}", "sendLoginCode", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_LOGIN_ERROR);
        }
    }

    /**
     * 登出
     *
     * @param man {@link HdShopAttDetailModel}
     */
    @Override
    public void logout(HdShopAttDetailModel man) {

    }

    /**
     * 查询活动信息
     *
     * @param jsonObject Long  shopAttDetailId 活动表主键id
     * @return
     */
    @Override
    public JSONObject findAttDetailById(JSONObject jsonObject) {
        try {
            Long shopAttDetailId = (Long) jsonObject.getObject("shopAttDetailId", Long.class);
            HdShopAttDetail shopAttDetail = shopAttDetailMapper.selectByPrimaryKey(shopAttDetailId);
            Map<String, String> result = new HashMap<>(1);
            result.put("imageUrl", shopAttDetail.getImageUrl());
            return JsonUtil.getSuccessJson(result);
        } catch (Exception e) {
            LOGGER.error("查询门店信息{}", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
        }
    }

    /**
     * v2 在用 根据 front_category表查询front_category_sku进行关联
     * 展示当前门店活动的商品
     *
     * @param jsonObject Long activityId 活动id
     *                   Long shopId  门店id
     * @return
     */
    @Override
    public JSONObject showProducts(JSONObject jsonObject) {
        try {
            Long activityid = (Long) jsonObject.getObject("activityId", Long.class);
            List<FrontCategoryVo> fronts = shopAttGoodspuMapper.findFrontByActivityId(activityid);
            List<Long> fIds = fronts.stream().map(f -> {
                return f.getId();
            }).collect(Collectors.toList());
            List<FrontSkuVo> skuVos = shopAttGoodspuMapper.findFrontSkus(fIds);

            if (fronts.size() > 0) {
                fronts.stream().forEach(fontVo -> {
                    List<FrontSkuVo> targetModels =
                            skuVos.stream().filter(skuVo ->
                                    fontVo.getId().longValue() == skuVo.getFrontId().longValue())
                                    .collect(Collectors.toList());
                    fontVo.setSkus(targetModels);
                });
            }
            List<FrontCategoryVoFace> faces = FrontCategoryVo.converts(fronts);
            List<FrontCategoryVoBody> bodys = FrontCategoryVoFace.converts(faces);
            return JsonUtil.getSuccessJson(bodys);
        } catch (Exception e) {
            LOGGER.error("method={},查询门店商品失败{}", "showProducts", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_PRODUCTS_ERROR);
        }
    }

    /**
     * @deprecated
     * v1版本查询spu进行遍历商品
     * 展示当前门店活动的商品
     *
     * @param jsonObject Long activityId 活动id
     *                   Long shopId  门店id
     * @return
     */
    @Deprecated
    public JSONObject showProductsV1(JSONObject jsonObject) {
       /* try {
            Long activityid = (Long) jsonObject.getObject("activityId", Long.class);
            List<HdGoodsSpuInfoVo> spuVos = shopAttGoodspuMapper.findByActivityShopId(activityid);
            List<Long> spuIds = spuVos.stream().map(vo -> {
                return vo.getId();
            }).collect(Collectors.toList());
            if (spuIds.size() > 0) {
                List<HdGoodsSkuModel> skuModes = shopAttGoodspuMapper.findSkuInSpuId(spuIds);
                spuVos.stream().forEach(spuVo -> {
                    List<HdGoodsSkuModel> targetModels =
                            skuModes.stream().filter(skuMode -> spuVo.getId().longValue() == skuMode.getSpuId().longValue()).collect(Collectors.toList());

                    spuVo.setSkus(targetModels);
                });
            }
            return JsonUtil.getSuccessJson(spuVos);
        } catch (Exception e) {
            LOGGER.error("method={},发送门店验证码失败{}", "showProducts", e);
            return JsonUtil.getErrorJson(ResultCodeEnum.ACTIVITY_SHOP_PRODUCTS_ERROR);
        }*/
        return null;
    }


    /**
     * 根据代理商id查询门店
     *
     * @param jsonObject Long  operatorId 代理商主键
     * @return
     */
    @Override
    public JSONObject findShopByOperator(JSONObject jsonObject) {
        Long operatorId = jsonObject.getObject("operatorId", Long.class);
        List<HdShopInfoDto> shops = shopInfoMapper.findByOperatorId(operatorId);
        return JsonUtil.getSuccessJson(shops);
    }

    /**
     * 获取门店注册信息主键id
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getAttdetailId(JSONObject jsonObject) {
        try {
            Long shopId = Long.valueOf(jsonObject.getString("shopId"));
            Long activityId = (Long) jsonObject.getObject("activityId", Long.class);
            List<AttShopInfoDto> attShopInfoDto = shopAttDetailMapper.findShopByShopIdActivityId(shopId, activityId);
            if (attShopInfoDto.size() > 0) {
                AttShopInfoDto result = attShopInfoDto.get(0);
                return JsonUtil.getSuccessJson(result);
            }
            return JsonUtil.getSuccessJson(null);
        } catch (Exception e) {
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);

        }
    }

    /**
     * 查询所有的代理商
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject findOperator(JSONObject jsonObject) {
        List<HdoperatorDto> list = operatorInfoMapper.findOperatorAll();
        JSONObject result = new JSONObject(1);
        result.put("operator", list);
        return JsonUtil.getSuccessJson(result);
    }

    /**
     * 查询店员手机是否注册
     *
     * @param jsonObject key=phone
     * @return
     */
    @Override
    public JSONObject findManager(JSONObject jsonObject) {
        String phone = jsonObject.getString("phone");
        List<HdShopAttDetail> details = shopAttDetailMapper.selectByPhone(phone);
        JSONObject result = new JSONObject(1);
        if (details.size() < 0) {
            result.put("code", "0");
        } else {

            result.put("cpde", "1");
        }
        return result;
    }

    @Override
    public JSONObject addOperator(JSONObject jsonObject) {
        try {
            HdOperatorRequestBody body = JSONObject.toJavaObject(jsonObject, HdOperatorRequestBody.class);
            HdOperatorInfo info = new HdOperatorInfo();
            BeanUtils.copyProperties(body,info);
            info.setId(Long.valueOf((StringUtil.getRandomIntByLength(10))));
            operatorInfoMapper.insertSelective(info);
            return JsonUtil.getSuccessJson();
        }catch (Exception e){
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
        }
    }

    @Override
    public JSONObject addShop(JSONObject jsonObject) {
        try {
            HdShopInfoRequestBody body = JSONObject.toJavaObject(jsonObject, HdShopInfoRequestBody.class);
            HdShopInfo info = new HdShopInfo();
            BeanUtils.copyProperties(body,info);
            info.setId(Long.valueOf(StringUtil.getRandomIntByLength(10)));
            info.setOperatorId(body.getOperatorId());
            shopInfoMapper.insertSelective(info);
            return JsonUtil.getSuccessJson();
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.getErrorJson(ResultCodeEnum.RESULT_CODE_OPERATION_FAIL);
        }
    }


    /**
     * 发送短信验证码
     *
     * @param model 商家活动注册模型
     * @return
     */
    @Deprecated
    private HdSmsMsg sendDynamicCode(HdShopAttDetailModel model) {
        HdSmsMsg hdSmsMsg = activitySMSService.sendDynamicCode(model.getManagerPhone(),
                SmsTemplateEnum.getDynamicCodeEnumByCodeType(model.getDynamicCodeType()),
                StringUtil.getRandomIntByLength(6), EXPIRATION_SECONDS
        );
        return hdSmsMsg;
    }


    /**
     * 发送验证码
     *
     * @param phone
     * @param sms
     * @param dynamicCode
     * @param exprationSeconds
     * @return
     */
    private HdSmsMsg sendDynamicCode(String phone, SmsTemplateEnum sms, String dynamicCode, long exprationSeconds) {
        return activitySMSService.sendDynamicCode(phone, sms, dynamicCode, exprationSeconds);
    }


    /**
     * 校验手机验证吗
     *
     * @param phoneNum        手机号
     * @param dynamicCode     验证码
     * @param dynamicCodeType 短信模版类型编号 {@link SmsTemplateEnum}  .dynamicCodeType
     * @return 成功返回true 错误返回false
     */
    private HdSmsMsg verifyPhone(String phoneNum, String dynamicCode, String dynamicCodeType) {
        String key = SmsTemplateEnum.getDynamicCodeEnumByCodeType(dynamicCodeType) + phoneNum;
        return activitySMSService.verifyDynamicCode(key, dynamicCode);
    }
}
