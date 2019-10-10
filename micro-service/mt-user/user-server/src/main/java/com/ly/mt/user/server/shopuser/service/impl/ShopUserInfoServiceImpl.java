package com.ly.mt.user.server.shopuser.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.RedisEnum;
import com.ly.mt.core.common.entity.YHHDHttpResponse.IdCardAnalysisRespDto;
import com.ly.mt.core.common.entity.YHHDHttpResponse.PortraintCompareRespDto;
import com.ly.mt.core.common.entity.YHHDHttpResponse.ThreeEleDetectionResponseDto;
import com.ly.mt.core.common.entity.shop.*;
import com.ly.mt.core.common.entity.user.User;
import com.ly.mt.core.common.entity.user.UserFeedback;
import com.ly.mt.core.common.entity.user.UserFeedbackImages;
import com.ly.mt.core.common.entity.user.UserLoginVo;
import com.ly.mt.core.common.util.*;
import com.ly.mt.user.server.base.service.impl.BaseServiceImpl;
import com.ly.mt.user.server.shopuser.mapper.ShopInfoServiceMapper;
import com.ly.mt.user.server.shopuser.service.ShopUserInfoService;
import com.ly.mt.user.server.user.async.UserInfoServiceAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.ly.mt.core.common.constant.RedisEnum.ENTITY_SHOP_USER_MOBILE_REDIS;
import static com.ly.mt.core.common.constant.ResultCodeEnum.*;
import  static com.ly.mt.core.common.dict.YHHDAccessKeyEnum.*;
import  static com.ly.mt.core.common.dict.YHHDUrlEnum.*;


@Service
public class ShopUserInfoServiceImpl extends BaseServiceImpl implements ShopUserInfoService {

    /**
     * 日志声明
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ShopUserInfoServiceImpl.class);

    @Resource
    private ShopInfoServiceMapper mapper;

    @Resource
    UserInfoServiceAsync async;
    /**
     * 补全信息
     *
     * @param json
     * @return
     */
    @Override
    public JSONObject finishInfo(String json) {
        JSONObject jsonObj = JSONObject.parseObject(json);
        String shopInfoStr = jsonObj.get("shopBasicInfo").toString();
        try{
            ShopInfoVo infoVo = JSONObject.parseObject(shopInfoStr, ShopInfoVo.class);
            //插入发货地址
            String ShopUserAddressId = IdUtil.getId(IdEnum.SHOP_USER_ADDRESS);
            //获取userId
            ShopAddress searchAdress = new ShopAddress();
            searchAdress.setShopId(infoVo.getShopId());
            Optional<ShopAddress> optionalShopAddress = Optional.
                    ofNullable(mapper.getShopAddress(searchAdress));
            if (optionalShopAddress.isPresent()) {
                //update
                mapper.updateShopAddressById(optionalShopAddress.get());
                //更新发货地址缓存
                ShopAddress dbAddress = mapper.getShopAddress(searchAdress);
                //   redisServer.set(RedisEnum.ENTITY_SHOP_USER_SENDADDRESS_REDIS,
                //         dbAddress.getId(), JSON.toJSONString(dbAddress));
            } else {
                insertNewShopAddress(infoVo, ShopUserAddressId);
            }
            //判断店铺类型 0:实体店主 1.实体电源 2:电商店主 4：共享烟民
            mapper.updateBasicInfoById(infoVo);
            //商铺认证成功
            mapper.updateShopStatus(Long.parseLong(infoVo.getShopId()));
            //更新缓存
            async.RefreshShopRedis(infoVo.getShopId());
            return JsonUtil.getSuccessJson();
        }catch (Exception e){
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }

    }

    @Override
    public JSONObject saveLicenses(String json) {
        Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String, String>>() {
        });
        String shopId = map.get("shopId");
        String url = map.get("path");
        Optional<ShopLicenses> shopLicensesOption = Optional.
                ofNullable(mapper.getShopLicensesByShopId(Long.parseLong(shopId)));
        ShopLicenses operatorParam = new ShopLicenses();
        operatorParam.setShopId(shopId);
        operatorParam.setPath(url);
        if (shopLicensesOption.isPresent()) {
            //update
            operatorParam.setModifyTime(DateUtil.getNowTimeStr());
            mapper.updateLicensesByShopId(operatorParam);
        } else {

            //insert
            operatorParam.setCreateTime(DateUtil.getNowTimeStr());
            operatorParam.setId(IdUtil.getId(IdEnum.SHOP_USER_LICENSES));
            mapper.InsertNewLicenses(operatorParam);
        }

        return JsonUtil.getSuccessJson();
    }

    @Override
    public JSONObject saveIdCard(String json) {
        IdCardVo idCardVo = JSONObject.parseObject(json, IdCardVo.class);
        idCardVo.setModifyTime(DateUtil.getNowTimeStr());
        try {
            mapper.updateIdCardByShopId(idCardVo);
            return JsonUtil.getSuccessJson();
        } catch (Exception e) {
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject updateStatus(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        try {
            String shopId = (String) jsonObject.get("shopId");
            mapper.updateShopStatus(Long.parseLong(shopId));
            return JsonUtil.getSuccessJson();
        }catch (Exception e){
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
    }

    @Override
    public JSONObject threeElementCheck(String json) {
        Map<String, String> mapParam  = JSON.parseObject(json, new TypeReference<Map<String, String>>() {});
        try {
           // RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Host", THREE_ELEMENT_DETECTION_TEST_URL.getUrl().substring(7,23));
            headers.add("Content-Type", "application/json; charset=utf-8");
            headers.add("Authorization", "Bearer "+ JwtUtil.getEncodedJWT());
            headers.add("x-encrypt", "128");;
            HashMap<String, String> map = new HashMap();
            map.put("name",mapParam.get("name"));
            map.put("mobile",mapParam.get("mobile"));
            map.put("idcard",mapParam.get("idcard"));

            String jsonParamStr = JSON.toJSONString(map);
            System.out.println(jsonParamStr);
            String appSecret = THREE_ELE_KEY_SECRET.getName().substring(0,16);
            final String encrypt = AESUtil.encrypt(appSecret, jsonParamStr,appSecret.getBytes());
            String param = "{\"params\":\"" + encrypt + "\"}";
            HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
            ResponseEntity<String> resp = restTemplate.postForEntity(THREE_ELEMENT_DETECTION_TEST_URL.getUrl(), httpEntity, String.class);
            ThreeEleDetectionResponseDto dto = JSONObject.parseObject(resp.getBody(), ThreeEleDetectionResponseDto.class);
            if (dto.getCode().equals(HttpStatus.OK)) {
                switch (dto.getData()) {
                    case 0:
                        return JsonUtil.getErrorJson(RESULT_CODE_IDCARD_UNKNOWN);
                    case 1:
                        return JsonUtil.getSuccessJson(RESULT_CODE_IDCARD_SAME);
                    case -1:
                        return JsonUtil.getErrorJson(RESULT_CODE_IDCARD_UNSAME);
                    case -2:
                        return JsonUtil.getErrorJson(RESULT_CODE_IDCARD_SAME_ID_DIFFERENT_NAME);
                    case -3:
                        return JsonUtil.getErrorJson(RESULT_CODE_IDCARD_DIFFERENT_ID_SAME_NAME);
                    case -4:
                        return JsonUtil.getErrorJson(RESULT_CODE_IDCARD_UNSAME_ALL);
                    default:
                        break;
                }
            } else {
                return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
            }
        } catch (Exception e) {
            LOGGER.error("调用三要素检测异常: ", e);
            return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
        }
        return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
    }

    /**
     * 人像比对
     * @param json  json字符串 真实姓名  身份证号  sdk获取的人脸加密数据包
     * @return
     */
    @Override
    public JSONObject portraitCompare(String json) {
        Map<String, String> mapParam  = JSON.parseObject(json, new TypeReference<Map<String, String>>() {});

           //查询用户真实姓名和身份证号
           ShopInfo search = new ShopInfo();
           search.setId(mapParam.get("shopId"));
           Optional<ShopInfo> shopInfoOptional = Optional.ofNullable(mapper.getShopUser(search));
           if(shopInfoOptional.isPresent()){
               try{
                 //   RestTemplate rest = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Host", PORTRAINT_COMPARE_TEST_URL.getUrl().substring(7,23));
                    headers.add("Content-Type", "application/json; charset=utf-8");
                    headers.add("Authorization", "Bearer "+ JwtUtil.getEncodedJWT());
                    System.out.println("Bearer "+ JwtUtil.getEncodedJWT());
                    HashMap<String, String> map = new HashMap();
                    map.put("name",shopInfoOptional.get().getRealName());
                    map.put("package",mapParam.get("facePhoto"));
                    map.put("idcard",shopInfoOptional.get().getIdcard());
                    String appSecret = THREE_ELE_KEY_SECRET.getName().substring(0,16);
                    final String encrypt = AESUtil.encrypt(appSecret, JSON.toJSONString(map),appSecret.getBytes());
                    String param = "{\"params\":\"" + encrypt + "\"}";
                    HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
                    //System.out.println("param:"+param);
                    ResponseEntity<String> resp = restTemplate.postForEntity(PORTRAINT_COMPARE_TEST_URL.getUrl(),httpEntity,String.class);
                    PortraintCompareRespDto dto =  JSONObject.parseObject(resp.getBody(), PortraintCompareRespDto.class);
                    if (dto.getCode().equals(HttpStatus.OK)) {
                        PortraintCompareRespDto.Data data = dto.getData();
                            switch(data.getStatus()){
                            case "-1":return JsonUtil.getErrorJson(RESULT_CODE_PHOTO_NAME_DIFFERENT);
                            case "1":
                                //商铺认证成功
                                mapper.updateShopStatus(Long.parseLong(mapParam.get("shopId")));
                                //更新缓存
                                async.RefreshShopRedis(mapParam.get("shopId"));
                                return JsonUtil.getSuccessJson(RESULT_CODE_PHOTO_NAME_SAME);
                            case "0":return JsonUtil.getErrorJson(RESULT_CODE_NOT_FOUND_STOCK);
                            default: break;
                        }
                    }else{
                        LOGGER.error("调用人脸比对返回状态异常:");
                        return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
                    }
                }catch (Exception e){
                    LOGGER.error("调用人脸比对异常: ",e.getMessage());
                    return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
               }
           }
           return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
    }

    @Override
    public JSONObject bindWx(String json) {
        Map<String, String> mapParam  = JSON.parseObject(json, new TypeReference<Map<String, String>>() {});
        UserLoginVo vo = new UserLoginVo();
        vo.setMobile(mapParam.get("mobile"));
        Optional<User> userOptional = Optional.ofNullable(mapper.getUser(vo));
        if(userOptional.isPresent()){
            if(userOptional.get().getWxOpenId() != null ||
                    !"".equals(userOptional.get().getWxOpenId())){
                //已绑定微信
                return JsonUtil.getErrorJson(RESULT_CODE_ALREADY_BIND_WECHAT);
            }
            //绑定微信
            User user = new User();
            user.setWxOpenId(mapParam.get("wxOpenId"));
            user.setMobile(mapParam.get("mobile"));
            mapper.bindWechat(user);
            return JsonUtil.getSuccessJson();
        }
        return JsonUtil.getErrorJson(RESULT_CODE_SYSTEM_ERROR);
    }

    /**
     * 插入新的发货地址信息
     * @param infoVo
     * @param shopUserAddressId
     */
    private void  insertNewShopAddress(ShopInfoVo infoVo, String shopUserAddressId) {
        ShopAddress address = new ShopAddress();
        address.setId(shopUserAddressId);
        address.setShopId(infoVo.getShopId());
        //根据shopId获取userId
        ShopInfo dbShopInfo = mapper.selecShopInfoByShopId(Long.parseLong(infoVo.getShopId()));
        address.setUserId(dbShopInfo.getUserId());
        //发货人姓名
        //发货人电话
        address.setReceivePhone(dbShopInfo.getMobile());
        address.setProvinceCode(infoVo.getSendProvinceCode());
        address.setProvinceName(infoVo.getSendProvinceName());
        address.setCityName(infoVo.getSendCityName());
        address.setCityCode(infoVo.getSendCityCode());
        address.setDistrictCode(infoVo.getSendDistrictCode());
        address.setDistrictName(infoVo.getSendDistrictName());
        address.setUserAddress(infoVo.getSendAddress());
        address.setIsDefault("1");//设为默认地址
        address.setSendLon(infoVo.getSendLon());
        address.setSendLat(infoVo.getSendLat());
        address.setValidStatus("1");//默认地址有效
        address.setCreateTime(DateUtil.getNowTimeStr());
        mapper.inserNewShopAddress(address);
       // redisServer.set(RedisEnum.ENTITY_SHOP_USER_SENDADDRESS_REDIS,
         //       dbShopInfo.getId(), JSON.toJSONString(address));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject userFeedBack(String json) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(json);
        String backType = jsonObject.get("backType").toString();
        String content = jsonObject.get("content").toString();
        String loginUserMobile = getLoginUserMobile();
        String loginUserId = getLoginUserId();
        UserFeedback userFeedBack = new UserFeedback();
        String id = IdUtil.getId(IdEnum.USER_FEEDBACK);
        userFeedBack.setId(id);
        userFeedBack.setContent(content);
        userFeedBack.setMobile(loginUserMobile);
        userFeedBack.setUserId(loginUserId);
        userFeedBack.setStatus("1");
        userFeedBack.setCreateTime(DateUtil.getNowTimeStr());
        userFeedBack.setBackType(backType);
        mapper.addUserFeedback(userFeedBack);
        UserFeedbackImages userFeedBackImages;

        if(jsonObject.get("urlList") !=null || !jsonObject.get("urlList").equals("")){
            String urlList = JSONObject.toJSONString(jsonObject.get("urlList"));
            List<String> urls = JSONObject.parseArray(urlList, String.class);
            for (String url : urls) {
                userFeedBackImages = new UserFeedbackImages();
                userFeedBackImages.setId(IdUtil.getId(IdEnum.USER_FEEDBACK_IMAGES));
                userFeedBackImages.setFeedbackId(id);
                userFeedBackImages.setImagesUrl(url);
                userFeedBackImages.setCreateTime(DateUtil.getNowTimeStr());
                mapper.addUserFeedbackImages(userFeedBackImages);
            }
        }
        Map map = new HashMap(1);
        map.put("msg", "提交成功，感谢您的反馈");
        return JsonUtil.getSuccessJson(map);
    }
}
