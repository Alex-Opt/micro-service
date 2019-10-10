package com.ly.mt.mis.basic.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.*;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.basic.user.service.UserService;
import org.springframework.stereotype.Service;

import static com.ly.mt.core.base.dict.PrimaryKey.PRIMARY_KEY_USER;
import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_MIS;
import static com.ly.mt.core.base.dict.QuickType.QUICK_TYPE_MIS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_YES;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;

/**
 * 用户管理
 *
 * @author taoye
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    /**
     * 登陆帐号统一前缀
     */
    private static final String MIS_LOGIN_NAME_PREFIX = "yykj";

    @Override
    public ResponseJson insertUser(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user.setId(SnowflakeUtil.getPrimaryKey(PRIMARY_KEY_USER));

        String projectType = user.getProjectType();
        if (PROJECT_TYPE_MIS.getId().equals(projectType)) {
            user.setLoginName(MIS_LOGIN_NAME_PREFIX + user.getLoginName());
        } else {
            user.setLoginName(user.getMobile());
        }

        String time = DateUtil.getNowTimeStr();
        user.setCreateTime(time);
        user.setUpdateTime(time);

        String loginUserId = getLoginUserId();
        user.setCreateUser(loginUserId);
        user.setUpdateUser(loginUserId);

        user.setValidStatus(VALID_STATUS_YES.getId());
        user.setPassword(MD5Util.md5(user.getPassword()));
        user.setQuickType(QUICK_TYPE_MIS.getId());

        userDao.insertUser(user);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson updateUser(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        String projectType = user.getProjectType();
        if (PROJECT_TYPE_MIS.getId().equals(projectType)) {
            user.setLoginName(MIS_LOGIN_NAME_PREFIX + user.getLoginName());
        }
        user.setUpdateTime(DateUtil.getNowTimeStr());
        user.setUpdateUser(getLoginUserId());
        userDao.updateUser(user);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson updateValidStatus(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user.setValidTime(DateUtil.getNowTimeStr());
        user.setValidUser(getLoginUserId());
        userDao.updateUser(user);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson resetPassword(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user.setUpdateTime(DateUtil.getNowTimeStr());
        user.setUpdateUser(getLoginUserId());
        user.setPassword(MD5Util.md5("123456"));
        userDao.updateUser(user);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson checkLoginName(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user = userDao.getUserFromMysql(user);
        if (StringUtil.isNotEmpty(user.getId())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "帐号已存在");
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }


    @Override
    public ResponseJson checkMobile(JSONObject jsonObject) throws Exception {
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        user = userDao.getUserFromMysql(user);
        if (StringUtil.isNotEmpty(user.getId())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "手机号已存在");
        }
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}