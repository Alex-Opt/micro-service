package com.ly.mt.mall.h5.user.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 用户注册接口
 * @Author taoye
 */
public interface UserService {
    /**
     * @Description 校验账号是否已注册
     * @Author taoye
     */
    ResponseJson checkLoginName(String loginName);

    /**
     * @Description 校验手机号是否已注册
     * @Author taoye
     */
    ResponseJson checkUserMobile(String mobile);

    /**
     * @Description 帐号注册
     * @Author taoye
     */
    ResponseJson regist(String loginName, String password, String mobile, String dynamicCode);

    /**
     * @Description 身份认证
     * @Author taoye
     */
    ResponseJson authentication(String userName, String idCard);

    /**
     * @Description 头像修改
     * @Author taoye
     */
    ResponseJson uploadAvatarPic(MultipartFile file);

    /**
     * @Description 绑定手机
     * @Author taoye
     */
    ResponseJson bindMobile(String mobile, String dynamicCode);

    /**
     * @Description 修改信息
     * @Author taoye
     */
    ResponseJson updateInfo(String provinceCode, String cityCode, String districtCode, String birthday, String sex);

    /**
     * @Description 获取用户信息
     * @Author taoye
     */
    ResponseJson loadInfo();

    /**
     * @Description 修改密码
     * @Author taoye
     */
    ResponseJson modifyPassword(String password, String mobile, String dynamicCode);

    /**
     * @Description 修改默认帐号
     * @Author taoye
     */
    ResponseJson updateLoginName(String loginName);
}