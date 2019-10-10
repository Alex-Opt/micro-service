package com.ly.mt.center.third.userCertificate.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 用户实名认证接口
 * @author wanglong
 */
public interface UserCertificateService {

    //身份证扫描
    ResponseJson idCardScan(JSONObject jsonObject);

    //三要素检测   姓名  身份证号  电话
    ResponseJson threeElementCheck(JSONObject jsonObject);
}
