package com.ly.mt.mis.mis.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;
import com.ly.mt.core.base.util.MD5Util;
import com.ly.mt.core.base.util.RequestUtil;
import com.ly.mt.core.base.util.ResponseUtil;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.mis.base.service.impl.BaseServiceImpl;
import com.ly.mt.mis.mis.login.async.LoginServiceAsync;
import com.ly.mt.mis.mis.login.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ly.mt.core.base.dict.ProjectType.PROJECT_TYPE_MIS;
import static com.ly.mt.core.base.dict.ValidStatus.VALID_STATUS_NO;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_ERROR;
import static com.ly.mt.core.base.entity.ResponseCode.RESPONSE_CODE_SUCCESS;
import static com.ly.mt.core.redis.RedisKey.REDIS_MIS_STRING_LOGIN_ERROR_COUNT_SESSION_ID;
import static com.ly.mt.core.redis.RedisKey.REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME;

/**
 * 登录
 *
 * @author taoye
 */
@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    @Resource
    private LoginServiceAsync async;

    @Override
    public String getLoginErrorCount(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String errorCount = redisService.get(REDIS_MIS_STRING_LOGIN_ERROR_COUNT_SESSION_ID, sessionId);
        if (StringUtil.isEmpty(errorCount)) {
            errorCount = "0";
        }
        return errorCount;
    }

    @Override
    public ResponseJson loginIn(HttpServletRequest request) throws Exception {
        User user = JSONObject.toJavaObject(RequestUtil.getJSONObject(request), User.class);
        String password = user.getPassword();
        String loginName = user.getLoginName();
        user = userDao.getUserByProjectTypeAndLoginNameFromRedis(PROJECT_TYPE_MIS.getId(), loginName);
        if (null == user) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "账户不存在，请联系管理员！");
        }
        if (VALID_STATUS_NO.getId().equals(user.getValidStatus())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "账户已停用，请联系管理员！");
        }
        if (!MD5Util.md5(password).equals(user.getPassword())) {
            return ResponseUtil.getResponseMsg(RESPONSE_CODE_ERROR, "用户名、密码不匹配！");
        }
        // 登录成功保存session
        String token = UUID.randomUUID().toString();
        String ipAddress = RequestUtil.getIpAddress(request);
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        session.setAttribute("userId", user.getId());
        session.setAttribute("loginName", loginName);
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("mobile", user.getMobile());
        session.setAttribute("ipAddress", ipAddress);
        session.setMaxInactiveInterval(1800);
        redisService.set(REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME, loginName, token, 30L, TimeUnit.MINUTES);
        // 更新最后登录时间和ip
        async.updateLoginInfo(user, ipAddress);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }

    @Override
    public ResponseJson loginOut(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        User user = JSONObject.toJavaObject(RequestUtil.getJSONObject(request), User.class);
        String loginName = user.getLoginName();
        redisService.del(REDIS_MIS_STRING_LOGIN_TOKEN_LOGIN_NAME, loginName);
        return ResponseUtil.getResponseCode(RESPONSE_CODE_SUCCESS);
    }
}