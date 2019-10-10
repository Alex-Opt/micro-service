package com.ly.mt.mis.mis.login.async;

import com.ly.mt.core.base.util.DateUtil;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录异步任务
 *
 * @author taoye
 */
@Component
public class LoginServiceAsync {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceAsync.class);
    @Resource
    private UserDao userDao;

    /**
     * 更新登录信息
     *
     * @param user      用户信息
     * @param ipAddress 登录ip
     * @author taoye
     */
    @Async
    public void updateLoginInfo(User user, String ipAddress) {
        try {
            user.setLastLoginTime(DateUtil.getNowTimeStr());
            user.setLastLoginIp(ipAddress);
            userDao.updateUser(user);
        } catch (Exception e) {
            LOGGER.error("LoginServiceAsync.updateLoginInfo error ", e);
        }
    }
}