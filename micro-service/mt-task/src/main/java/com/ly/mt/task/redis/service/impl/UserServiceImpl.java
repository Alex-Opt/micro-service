package com.ly.mt.task.redis.service.impl;

import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.redis.entity.RedisRefresh;
import com.ly.mt.core.data.user.dao.UserAddressDao;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.task.base.service.impl.BaseServiceImpl;
import com.ly.mt.task.redis.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ly.mt.core.base.dict.TriggerType.TRIGGER_TYPE_DELETE;
import static com.ly.mt.core.redis.RedisKey.*;

/**
 * User缓存更新处理
 *
 * @author taoye
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserAddressDao userAddressDao;

    @Override
    public void refreshUser(RedisRefresh refresh) throws Exception {
        String projectType = refresh.getId1();
        if (StringUtil.isEmpty(projectType)) {
            projectType = "0";
        }
        String id = refresh.getId2();
        String mobile = refresh.getId3();
        String loginName = refresh.getId4();
        String wxOpenId = refresh.getId5();
        deleteRedis(projectType, id, mobile, loginName, wxOpenId);
        if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
            User user = new User();
            user.setProjectType(projectType);
            user.setId(id);
            user.setMobile(mobile);
            user.setLoginName(loginName);
            user.setWxOpenId(wxOpenId);
            userDao.getUserFromMysql(user);
        }
    }


    @Override
    public void refreshUserAddress(RedisRefresh refresh) {
        String id = refresh.getId1();
        redisService.del(REDIS_USER_ADDRESS_ENTITY_ID, id);
        if (!TRIGGER_TYPE_DELETE.getId().equals(refresh.getTriggerType())) {
            userAddressDao.getUserAddressByIdFromRedis(id);
        } else {
            throw new RuntimeException("UserServiceImpl.refreshUserAddress refresh.id1 must not be null");
        }
    }


    /**
     * 删除redis
     *
     * @param projectType 项目类型
     * @param id          用户id
     * @param mobile      用户手机号
     * @param loginName   用户帐号
     * @param wxOpenId    用户微信openId
     * @author taoye
     */
    private void deleteRedis(String projectType, String id, String mobile, String loginName, String wxOpenId) {
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_USER_ENTITY_ID, id);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            redisService.del(REDIS_USER_ENTITY_PROJECT_TYPE_MOBILE, projectType + ":" + mobile);
        }
        if (StringUtil.isNotEmpty(loginName)) {
            redisService.del(REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME, projectType + ":" + loginName);
        }
        if (StringUtil.isNotEmpty(wxOpenId)) {
            redisService.del(REDIS_USER_ENTITY_LOGIN_WX_OPEN_ID, wxOpenId);
        }
    }
}