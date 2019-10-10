package com.ly.mt.center.third.userCertificate.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ly.mt.center.third.base.config.YmlConfig;
import com.ly.mt.center.third.userCertificate.service.UserCertificateService;
import com.ly.mt.core.base.dict.ThipartiteThreeCheckCode;
import com.ly.mt.core.base.dict.YHHDAccessKey;
import com.ly.mt.core.base.dict.YHHDUrl;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.YHHDHttpResponse.ThreeEleDetectionResponseDto;
import com.ly.mt.core.base.util.AESUtil;
import com.ly.mt.core.base.util.JwtUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 用户实名认证接口
 * @author wanglong
 */
@Service
public class UserCertificateServiceImpl implements UserCertificateService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserCertificateServiceImpl.class);
    @Resource
    YmlConfig yml;
    /**
     * ocr图像识别
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson idCardScan(JSONObject jsonObject) {
        try{
            String ocrImg = jsonObject.getString("ocrImg");
            String ocrImgExt = jsonObject.getString("ocrImgExt");
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Host", "api.yunheit.com");
            headers.add("Content-Type", "application/json; charset=utf-8");
            headers.add("Authorization", "Bearer "+ JwtUtil.getEncodedJWT(yml.getYyhdSercet(),yml.getYyhdAccessKey()));
            headers.add("x-encrypt", "128");;
            HashMap<String, String> map = new HashMap();
            map.put("image",ocrImg);
            map.put("ext",ocrImgExt);
            String jsonParamStr = JSON.toJSONString(map);
            String appSecret = YHHDAccessKey.THREE_ELE_KEY_SECRET.getName().substring(0,16);
            final String encrypt = AESUtil.encrypt(appSecret, jsonParamStr,appSecret.getBytes("UTF-8"));
            String param = "{\"params\":\"" + encrypt + "\"}";
            HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
            LOGGER.info("云和互动-------------OCR图像识别身份证---------------------------start");
            ResponseEntity<String> resp = rest.postForEntity(yml.getYyhdOcrScanUrl(),httpEntity,String.class);
            LOGGER.info("云和互动-------------OCR图像识别身份证--------------success-------------end");
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,resp.getBody());
        }catch (Exception e){
            LOGGER.info("云和互动-------------OCR图像识别身份证出现异常：{}",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 身份三要素证效验
     * @param jsonObject
     * @return
     */
    @Override
    public ResponseJson threeElementCheck(JSONObject jsonObject) {
        try {
            RestTemplate rest = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Host", "api.yunheit.com");
            headers.add("Content-Type", "application/json; charset=utf-8");
            headers.add("Authorization", "Bearer "+ JwtUtil.getEncodedJWT(yml.getYyhdSercet(),yml.getYyhdAccessKey()));
            headers.add("x-encrypt", "128");;
            HashMap<String, String> map = new HashMap();
            map.put("name",jsonObject.getString("card_name"));
            map.put("mobile",jsonObject.getString("mobile"));
            String cardId = jsonObject.getString("card_id");
            map.put("idcard",cardId);
            String jsonParamStr = JSON.toJSONString(map);
            System.out.println(jsonParamStr);
            String appSecret = yml.getYyhdSercet().substring(0,16);
            final String encrypt = AESUtil.encrypt(appSecret, jsonParamStr,appSecret.getBytes());
            String param = "{\"params\":\"" + encrypt + "\"}";
            HttpEntity<String> httpEntity = new HttpEntity<>(param, headers);
            LOGGER.info("云和互动-身份证三要素验证----------------------------start");
            ResponseEntity<String> resp = rest.postForEntity(yml.getYyhdThreeElementDetectionUrl(),httpEntity,String.class);
            LOGGER.info("云和互动-身份证三要素验证-----------success-----------------end");
            ThreeEleDetectionResponseDto dto =  JSONObject.parseObject(resp.getBody(), ThreeEleDetectionResponseDto.class);
            JSONObject jsonObject1 = new JSONObject();
            if (dto.getCode().equals(HttpStatus.OK.value())) {
                switch (dto.getData()){
                    case 0:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_0.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_0.getDesc()); break;
                    case 1:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_1.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_1.getDesc()); break;
                    case -1:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_2.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_2.getDesc()); break;
                    case -2:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_3.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_3.getDesc()); break;
                    case -3:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_4.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_4.getDesc()); break;
                    case -4:jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_5.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_5.getDesc()); break;
                    default:break;
                }
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,jsonObject1);
            }else{
                jsonObject1.put("code",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_5.getCode());jsonObject1.put("msg",ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_5.getDesc());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS,jsonObject1);
            }
        } catch (Exception e) {
            LOGGER.error("调用三要素检测异常: ",e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }
}
