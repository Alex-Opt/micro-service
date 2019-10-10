package com.ly.mt.mall.h5.user.controller;

import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.mall.h5.user.service.UserAuthenticationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.ly.mt.core.base.dict.FileType.*;
import static com.ly.mt.core.base.entity.ResponseCode.*;

/**
 * @author zhanglifeng
 * @description 到家C用户中心：实名认证
 * @date 2019-09-05
 */
@Api(description = "用户实名认证接口")
@RestController
@RequestMapping("/mall/h5/user/authenticate")
public class UserAuthenticationController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationController.class);
    @Resource
    private UserAuthenticationService userAuthenticationService;

    @ApiOperation("图像信息识别用户正面身份证，并上传身份证图像到阿里云")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageFront", value = "身份证正面", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!返回身份信息(真实姓名，身份证号,正面身份证图片的阿里云访问url)"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/ocrScanFrontIdCard")
    public ResponseJson ocrScanFrontIdCard(@RequestParam(value = "imageFront") MultipartFile multipartFile) {
        try {
            MultipartFile[] multipartFiles = new MultipartFile[1];
            multipartFiles[0] = multipartFile;
            String frontImage = getImageBase64EncodeStr(multipartFile);
            //获取身份证正面后缀
            String frontExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            LOGGER.info("获取身份证正面后缀:{}", frontExt);
            if (FILE_TYPE_IMAGE_EXT_JPG.getId().equals(frontExt) || FILE_TYPE_IMAGE_EXT_BMP.getId().equals(frontExt) || FILE_TYPE_IMAGE_EXT_JPEG.getId().equals(frontExt)) {
                return userAuthenticationService.ocrScanFrontIdCard(multipartFiles, frontImage, frontExt);
            } else {
                return ResponseUtil.getResponseMsg(RESULT_CODE_ID_CARD_EXT_ERROR, "上传身份证图片格式不正确,格式要求为jpg或者bmp!");
            }
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--身份证扫描正面异常:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
    }

    @ApiOperation("上传用户反面身份证图像到阿里云")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageBack", value = "身份证反面", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功!返回身份信息(反面身份证图片的阿里云访问url)"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/ocrScanBackIdCard")
    public ResponseJson ocrScanBackIdCard(@RequestParam(value = "imageBack") MultipartFile multipartFile) {
        try {
            MultipartFile[] multipartFiles = new MultipartFile[1];
            multipartFiles[0] = multipartFile;
            String backImage = getImageBase64EncodeStr(multipartFile);
            //获取身份证反面后缀
            String backExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            LOGGER.info("获取身份证反面后缀:{}", backExt);
            if (FILE_TYPE_IMAGE_EXT_JPG.getId().equals(backExt) || FILE_TYPE_IMAGE_EXT_BMP.getId().equals(backExt)) {
                return userAuthenticationService.ocrScanBackIdCard(multipartFiles, backImage, backExt);
            } else {
                return ResponseUtil.getResponseMsg(RESULT_CODE_ID_CARD_EXT_ERROR, "上传身份证图片格式不正确!");
            }
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--身份证扫描反面异常:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
    }


    @ApiOperation("个人中心:查询实名认证的状态值,和手机号，无入参。后台获取userId当入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! status: 0:待审核 1:已认证 2:未成年 9-未认证"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/authenticateStatus")
    public ResponseJson authenticateStatus(HttpServletRequest request) {
        try {
            return userAuthenticationService.authenticateStatus();
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--身份证扫描反面异常:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "参数错误!");
        }
    }


    @ApiOperation("个人中心:根据身份证id，姓名，手机号做三要素检查")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardId", value = "身份证号", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardName", value = "身份证上的名字", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! "),
            @ApiResponse(code = 1, message = "系统异常!异常信息通过后台获取msg")
    })
    @PostMapping("/threeFactorDetection")
    public ResponseJson threeFactorDetection(@RequestParam(value = "cardId") String cardId,
                                             @RequestParam(value = "cardName") String cardName,
                                             @RequestParam(value = "mobile") String mobile,
                                             HttpServletRequest request) {
        try {
            return userAuthenticationService.threeFactorDetection(cardName, cardId, mobile);
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--三要素检测异常:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "三要素检测异常!");
        }
    }


    @ApiOperation("个人中心:保存用户的实名认证申请信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cardId", value = "身份证号", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardName", value = "身份证上的名字", paramType = "query", required = true),
            @ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardFrontUrl", value = "身份证正面图片url", paramType = "query", required = true),
            @ApiImplicitParam(name = "cardBackUrl", value = "身份证反面图片url", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! "),
            @ApiResponse(code = 1, message = "系统异常!异常信息通过后台获取msg")
    })
    @PostMapping("/saveAuthenticateInfo")
    public ResponseJson saveAuthenticateInfo(@RequestParam(value = "cardId") String cardId,
                                             @RequestParam(value = "cardName") String cardName,
                                             @RequestParam(value = "mobile") String mobile,
                                             @RequestParam(value = "cardFrontUrl") String cardFrontUrl,
                                             @RequestParam(value = "cardBackUrl") String cardBackUrl,
                                             HttpServletRequest request) {
        try {
            if(StringUtil.isEmpty(cardId) || StringUtil.isEmpty(cardName)|| StringUtil.isEmpty(mobile)|| StringUtil.isEmpty(cardFrontUrl)|| StringUtil.isEmpty(cardBackUrl)){
                return ResponseUtil.getResponseCode(RESULT_CODE_PARAM_NEED);
            }
            return userAuthenticationService.saveAuthenticateInfo(cardName, cardId, mobile, cardFrontUrl, cardBackUrl);
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--保存用户的实名认证申请信息:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "保存用户的实名认证申请信息异常!");
        }
    }

    @ApiOperation("个人中心:查询用户的实名认证信息，无入参。后台获取userId当入参")
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功! status: 0:待审核 1:已认证 2:未成年 9-未认证"),
            @ApiResponse(code = 1, message = "系统异常!")
    })
    @PostMapping("/queryAuthenticateInfo")
    public ResponseJson queryAuthenticateInfo(HttpServletRequest request) {
        try {
            return userAuthenticationService.queryAuthenticateInfo();
        } catch (Exception e) {
            LOGGER.error("到家C实名认证--查询用户的实名认证信息异常:{}", e.getMessage());
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "查询用户的实名认证信息异常!");
        }
    }


    /**
     * 将文件转成base64码
     *
     * @param file
     * @return
     * @throws Exception
     */
    private String getImageBase64EncodeStr(MultipartFile file) throws Exception {
        BASE64Encoder baseEncode = new BASE64Encoder();
        String base64EncoderImg = baseEncode.encode(file.getBytes());
        String encoded = base64EncoderImg.replaceAll("[\\s*\t\n\r]", "");
        return encoded;
    }

}
