package com.ly.mt.mall.h5.user.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhanglifeng
 * @description 用户实名认证服务接口层
 * @date 2019-09-05
 */
public interface UserAuthenticationService {
    /**
     * [图像信息识别用户正面身份证，并上传身份证图像到阿里云]
     *
     * @param files
     * @param frontImage
     * @param frontExt
     * @return
     */
    ResponseJson ocrScanFrontIdCard(MultipartFile[] files, String frontImage, String frontExt);

    /**
     * [上传身份证反面图像到阿里云]
     *
     * @param files
     * @param backImage
     * @param backExt
     * @return
     */
    ResponseJson ocrScanBackIdCard(MultipartFile[] files, String backImage, String backExt);

    /**
     * [查询用户实名认证的状态]
     *
     * @return
     */
    ResponseJson authenticateStatus();


    /**
     * [姓名，身份证号，手机号三要素检测]
     *
     * @param cardName
     * @param cardId
     * @param mobile
     * @return
     */
    ResponseJson threeFactorDetection(String cardName, String cardId, String mobile);

    /**
     * [保存用户的认证申请信息]
     *
     * @param cardName
     * @param cardId
     * @param mobile
     * @param cardFrontUrl 身份证正面图像地址
     * @param cardBackUrl 身份证反面图像地址
     * @return
     */
    ResponseJson saveAuthenticateInfo(String cardName, String cardId, String mobile, String cardFrontUrl, String cardBackUrl);


    /**
     * [查询用户的认证信息。记得用缓存]
     *
     * @return
     */
    ResponseJson queryAuthenticateInfo();

}
