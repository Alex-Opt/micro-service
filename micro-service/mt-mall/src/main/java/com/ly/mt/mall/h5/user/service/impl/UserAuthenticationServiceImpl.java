package com.ly.mt.mall.h5.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ly.mt.core.base.dict.PrimaryKey;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.entity.YHHDHttpResponse.TripartiteIdCardResultDto;
import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.SnowflakeUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.base.service.impl.BaseServiceImpl;
import com.ly.mt.mall.h5.user.service.UserAuthenticationService;
import com.ly.mt.mall.h5.user.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.AlOssPath.AL_OSS_PATH_IMAGE_USER_IDCARD_BACK;
import static com.ly.mt.core.base.dict.AlOssPath.AL_OSS_PATH_IMAGE_USER_IDCARD_FRONT;
import static com.ly.mt.core.base.dict.ThipartiteCardCode.THIPARTITE_CARD_CODE_200;
import static com.ly.mt.core.base.dict.ThipartiteThreeCheckCode.THIPARTITE_THREE_CHECK_CODE_1;
import static com.ly.mt.core.base.dict.UserCertifacationStatus.USER_CERTIFICATION_ADULT;
import static com.ly.mt.core.base.dict.UserCertifacationStatus.USER_CERTIFICATION_YOUNG;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.feign.DataCenterMethod.*;
import static com.ly.mt.core.feign.ThirdCenterMethod.USER_CERTIFICATION_SCAN_IDCARD;
import static com.ly.mt.core.feign.ThirdCenterMethod.USER_CERTIFICATION_THREE_ELEMENT_CHECK;
import static com.ly.mt.core.redis.RedisKey.USER_CERTIFICATION_SAVE_BY_USER_ID;

/**
 * @author zhanglifeng
 * @description 用户实名认证服务接口实现层
 * @date 2019-09-05
 */
@Service
public class UserAuthenticationServiceImpl extends BaseServiceImpl implements UserAuthenticationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    /**
     * [身份证正面处理业务逻辑：
     * 1.身份证是否为正面校验
     * 2.获取身份证正面身份证号，姓名信息
     * 3.上传身份证正面到阿里云
     * 4.身份证信息,图片上传后生成的url 组装成出参 返回前台]
     *
     * @param files
     * @param frontImage
     * @param frontExt
     * @return
     */
    @Override
    public ResponseJson ocrScanFrontIdCard(MultipartFile[] files, String frontImage, String frontExt) {
        try {
            //扫描身份证正面
            String frontResp = callThirdCenterOCRResultJson(frontImage, frontExt);
            //三方ocr图像识别返回结果(正面结果)
            TripartiteIdCardResultDto tripartiteIdCardResultFrontDto = JSONObject.parseObject(frontResp, TripartiteIdCardResultDto.class);
            LOGGER.info("到家C实名认证---------------调用OCR图像识别结果:{}", JSONObject.toJSONString(tripartiteIdCardResultFrontDto));
            if (THIPARTITE_CARD_CODE_200.getCode().equals(tripartiteIdCardResultFrontDto.getCode())) {
                //身份证正面返回结果
                final TripartiteIdCardResultDto.data data = tripartiteIdCardResultFrontDto.getData();
                LOGGER.info("-------------身份证正面--------------OcrImgType: " + data.getOcrImgType());
                //只有身份证正面的OcrImgType为1，反面的为2.
                if ("2".equals(data.getOcrImgType())) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "请上传身份证正面！");
                }
                String imageUrl = getIdCardImageUrl(files, AL_OSS_PATH_IMAGE_USER_IDCARD_FRONT.getPath());
                JSONObject json = new JSONObject();
                json.put("imageUrl", imageUrl);
                json.put("realName", data.getName());
                json.put("cardId", data.getIdcard());
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
            } else {
                LOGGER.error("-------------到家C----------------调用二代身份证扫描身份证返回状态异常--------------------------------");
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "上传的身份证照片不清晰或图片过大，请重新上传。");
            }
        } catch (Exception e) {
            LOGGER.error("调用二代身份证扫描异常:{} ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * [身份证反面业务处理逻辑：反面不用获取信息，校验确认是反面身份证后，直接上传到阿里云，返回图片生成的url]
     *
     * @param files
     * @param backImage
     * @param backExt
     * @return
     */
    @Override
    public ResponseJson ocrScanBackIdCard(MultipartFile[] files, String backImage, String backExt) {
        try {
            //扫描身份证反面
            String backResp = callThirdCenterOCRResultJson(backImage, backExt);
            //三方ocr图像识别返回结果(正面结果)
            TripartiteIdCardResultDto tripartiteIdCardResultBackDto = JSONObject.parseObject(backResp, TripartiteIdCardResultDto.class);
            LOGGER.info("到家C实名认证---------------调用OCR图像识别结果:{}", JSONObject.toJSONString(tripartiteIdCardResultBackDto));
            if (THIPARTITE_CARD_CODE_200.getCode().equals(tripartiteIdCardResultBackDto.getCode())) {
                //身份证正面返回结果
                final TripartiteIdCardResultDto.data data = tripartiteIdCardResultBackDto.getData();
                LOGGER.info("----------身份证反面-----------------OcrImgType: " + data.getOcrImgType());
                //只有身份证正面的OcrImgType为1，反面的为2.
                if ("1".equals(data.getOcrImgType())) {
                    return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "请上传身份证反面！");
                }
                String imageUrl = getIdCardImageUrl(files, AL_OSS_PATH_IMAGE_USER_IDCARD_BACK.getPath());
                JSONObject json = new JSONObject();
                json.put("imageUrl", imageUrl);
                return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
            } else {
                LOGGER.error("-------------到家C----------------调用二代身份证扫描身份证返回状态异常--------------------------------");
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "上传的身份证照片不清晰，请重新上传。");
            }
        } catch (Exception e) {
            LOGGER.error("调用二代身份证扫描异常:{} ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * 上传身份证图片方法
     *
     * @param files
     * @return
     */
    private String getIdCardImageUrl(MultipartFile[] files, String ossPath) throws Exception {
        String[] paths = new String[1];
        //反面
        paths[0] = ossPath;
        String uploadResult = callThirdCenter(files, paths);
        if (StringUtil.isEmpty(uploadResult)) {
            LOGGER.info("到家C实名认证--------------------------------上传身份证失败");
        }
        List<String> returnUrls = JSONObject.parseObject(uploadResult, new TypeReference<List<String>>() {
        });
        return returnUrls.get(0);
    }


    /**
     * [云和互动-OCR图像识别身份证]
     *
     * @param ocrImg
     * @param ocrImgExt
     * @return
     * @throws Exception
     */
    private String callThirdCenterOCRResultJson(String ocrImg, String ocrImgExt) throws Exception {
        JSONObject object = new JSONObject();
        object.put("ocrImg", ocrImg);
        object.put("ocrImgExt", ocrImgExt);
        return callThirdCenter(USER_CERTIFICATION_SCAN_IDCARD, object);
    }

    /**
     * [查询用户认证状态和手机号]
     *
     * @return
     */
    @Override
    public ResponseJson authenticateStatus() {
        String userId = getLoginUserId();
        LOGGER.info("--------------获取到的用户信息：" + userId);
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        //如果没有查询到一条记录。说明尚未注册。
        String status = "9";
        try {
            String authenticateStr = callDataCenter(USER_CERTIFACATION_GET, json);
            if (StringUtil.isNotEmpty(authenticateStr)) {
                JSONObject jsonObject = JSONObject.parseObject(authenticateStr);
                LOGGER.info("-------------------查询到的用户实名认证信息map:{}", authenticateStr);
                status = jsonObject.getString("status");
            }
            String userStr = callDataCenter(USER_GET_USER_BY_ID, json);
            if (StringUtil.isEmpty(userStr)) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户不存在!");
            }
            UserInfoVo user = JSONObject.toJavaObject(JSONObject.parseObject(userStr), UserInfoVo.class);
            String mobile = user.getMobile();
            json.put("mobile", mobile);
            LOGGER.info("----------------------实名认证的用户认证状态：status  " + status);
            json.put("status", status);
            json.remove("user_id");
        } catch (Exception e) {
            LOGGER.error("------------------根据用户id查询是否已经实名认证异常：{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, json);
    }

    /**
     * [姓名，身份证号，手机号三要素检测]
     *
     * @param cardName
     * @param cardId
     * @param mobile
     * @return
     */
    @Override
    public ResponseJson threeFactorDetection(String cardName, String cardId, String mobile) {
        JSONObject paramJson = new JSONObject();
        paramJson.put("mobile", mobile);
        paramJson.put("card_id", cardId);
        paramJson.put("card_name", cardName);
        LOGGER.info("到家C--实名认证三要素检测--调用云和互动三要素接口入参:" + JSONObject.toJSONString(paramJson));
        try {
            //调用第三方
            String threeElementResultStr = callThirdCenter(USER_CERTIFICATION_THREE_ELEMENT_CHECK, paramJson);
            JSONObject threeElementResultJson = JSONObject.parseObject(threeElementResultStr);
            String resultCode = threeElementResultJson.getString("code");
            String resultMsg = threeElementResultJson.getString("msg");
            if (!resultCode.equals(THIPARTITE_THREE_CHECK_CODE_1.getCode())) {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, resultMsg);
            } else {
                return ResponseUtil.getResponseMsg(RESPONSE_CODE_SUCCESS, resultMsg);
            }
        } catch (Exception e) {
            LOGGER.error("到家C-实名认证-----------调用三要素检测异常: ", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    /**
     * [保存用户的实名认证信息]
     *
     * @param cardName
     * @param cardId
     * @param mobile
     * @param cardFrontUrl 身份证正面图像地址
     * @param cardBackUrl  身份证反面图像地址
     * @return
     */
    @Override
    public ResponseJson saveAuthenticateInfo(String cardName, String cardId, String mobile, String cardFrontUrl, String cardBackUrl) {
        //首先要验证该身份证是否已经使用过。如果身份证使用过，则直接返回提示信息。
        JSONObject cardIdJson = new JSONObject();
        cardIdJson.put("cardId", cardId);
        String flag = callDataCenter(USER_CERTIFACATION_GET_BY_CARDID, cardIdJson);
        if ("true".equals(flag)) {
            //说明存在了已经。
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "此身份证已被其他账号认证");
        }
        int age = 0;
        String userId = getLoginUserId();
        try {
            String birthday = cardId.substring(6, 13);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = new Date(formatter.parse(birthday).getTime());
            //年龄
            age = DateUtil.getAgeByBirth(birthDate);
        } catch (ParseException e) {
            LOGGER.error("从身份证中获取年龄出现异常：{}", e);
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "从身份证中获取年龄出现异常");
        }
        JSONObject certificationJson = new JSONObject();
        certificationJson.put("id", SnowflakeUtil.getPrimaryKey(PrimaryKey.PRIMARY_KEY_USER_CERTIFICATION));
        certificationJson.put("user_id", userId);
        certificationJson.put("card_name", cardName);
        certificationJson.put("card_id", cardId);
        certificationJson.put("mobile", mobile);
        certificationJson.put("card_front_url", cardFrontUrl);
        certificationJson.put("card_reverse_url", cardBackUrl);
        certificationJson.put("status", (age >= 18) ? USER_CERTIFICATION_ADULT.getId() : USER_CERTIFICATION_YOUNG.getId());
        certificationJson.put("create_time", DateUtil.getNowTimeStr());
        try {
            callDataCenter(USER_CERTIFACATION_INSERT, certificationJson);
            //将实名认证信息根据用户id保存到redis  30天
            redisService.set(USER_CERTIFICATION_SAVE_BY_USER_ID, userId, JSONObject.toJSONString(certificationJson), 30L, TimeUnit.DAYS);
            return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, certificationJson.getString("status"));
        } catch (Exception e) {
            LOGGER.error("到家C---保存用户---实名认证申请信息出现异常:{}", e);
            return ResponseUtil.getResponseCode(RESPONSE_CODE_ERROR);
        }
    }

    @Override
    public ResponseJson queryAuthenticateInfo() {
        String userId = getLoginUserId();
        //根据用户id去redis中拿数据
        String userCertificationStr = redisService.get(USER_CERTIFICATION_SAVE_BY_USER_ID, userId);
        if (StringUtil.isEmpty(userCertificationStr)) {
            JSONObject json = new JSONObject();
            json.put("user_id", userId);
            userCertificationStr = callDataCenter(USER_CERTIFACATION_GET, json);
        }
        LOGGER.info("------------------------获取到的用户实名认证信息：{}", userCertificationStr);
        JSONObject userCertificationJson = JSONObject.parseObject(userCertificationStr);
        String card_name = userCertificationJson.getString("card_name");
        String card_id = userCertificationJson.getString("card_id");
        String mobile = userCertificationJson.getString("mobile");
        String p1 = mobile.substring(0, 3) + "****" + mobile.substring(8, 11);
        String p2 = card_id.substring(0, 5) + "***********" + card_id.substring(card_id.length() - 2, card_id.length());
        String p3 = null;
        if (card_name.length() > 2) {
            p3 = card_name.substring(0, 1) + "*" + card_name.substring(card_name.length() - 1, card_name.length());
        } else {
            p3 = card_name.substring(0, 1) + "*";
        }
        userCertificationJson.put("card_name",p3);
        userCertificationJson.put("card_id",p2);
        userCertificationJson.put("mobile",p1);
        return ResponseUtil.getResponseObj(RESPONSE_CODE_SUCCESS, userCertificationJson);
    }
}
