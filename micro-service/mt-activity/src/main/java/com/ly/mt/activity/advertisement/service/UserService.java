package com.ly.mt.activity.advertisement.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动用户注册登录
 * @author wanglong
 */

public interface UserService {


    /**
     * 活动注册登录
     * @param param
     * @param request
     * @return
     */
    public ResponseJson activityH5Regist(JSONObject param,HttpServletRequest request)throws  Exception;


    /**
     * @Description 校验手机号是否已注册
     * @Author taoye
     */
    ResponseJson checkUserMobile(String mobile);

    /**
     * @Description 保存活动页手机号
     * @Author zhanglifeng
     * @param mobile
     * @param quickType 用户来源
     */
    ResponseJson savePhoneNumber(String mobile,String quickType);





}
