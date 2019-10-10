package com.ly.mt.blue.tooth.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * 注册
     * @throws Exception
     */
    ResponseJson buletoothRegister(String loginName,String password,String dynamicCode) throws Exception;

    /**
     * 重置密码
     * @throws Exception
     */
    ResponseJson resetPwd(String mobile,String dynamicCode,String password) throws Exception;

    /**
     * 验证登录名是否被注册
     * @throws Exception
     */
    ResponseJson checkUserLoginName(String loginName) throws Exception;

    /**
     * 修改用户信息
     * @throws Exception
     */
    ResponseJson modifyUserInfo(String nickName,String sex,String birthday) throws Exception;

    /**
     * 修改用户密码
     * @throws Exception
     */
    ResponseJson modifyPassword(String password) throws Exception;

    /**
     * @Description 头像修改
     * @Author whl
     */
    ResponseJson uploadAvatarPic(MultipartFile file) throws Exception;

    /**
     * @Description 获取用户信息
     * @Author whl
     */
    ResponseJson loadUserInfo() throws Exception;

    /**
     * @Description 更换绑定账号/效验验证码是否正确
     * @Author whl
     */
    ResponseJson validateCode(String phone,String dynamicCode) throws Exception;

    /**
     * @Description 更换绑定账号/效验验证码是否正确
     * @Author whl
     */
    ResponseJson modifyBindPhone(String phone,String dynamicCode) throws Exception;


    /**
     * 扫描身份信息
     * @param
     * @return
     */
    ResponseJson scanIdCard(String frontImage, String backImage,String frontExt,String backExt, MultipartFile[] files);

    /**
     * 三要素检测
     * @param json
     * @return
     */
     ResponseJson threeElementCheck(JSONObject  json);

}