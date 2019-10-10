package com.ly.mt.core.data.user.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.base.util.StringUtil;
import com.ly.mt.core.data.base.service.impl.BaseDaoServiceImpl;
import com.ly.mt.core.data.user.dao.UserDao;
import com.ly.mt.core.data.user.entity.User;
import com.ly.mt.core.data.user.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ly.mt.core.redis.RedisKey.*;

/**
 * User操作接口
 *
 * @author taoye
 */
@Service
public class UserDaoImpl extends BaseDaoServiceImpl implements UserDao {
    @Resource
    UserMapper mapper;

    @Override
    public void insertUser(User user) {
        Assert.notNull(user, "UserDaoImpl.insertUser user must not be null");
        Assert.notNull(user.getId(), "UserDaoImpl.insertUser user.id must not be null");
        mapper.insertUser(user);
        setRedis(user);
    }


    @Override
    public int updateUser(User user) {
        Assert.notNull(user, "UserDaoImpl.insertUser user must not be null");
        String id = user.getId();
        Assert.notNull(id, "UserDaoImpl.insertUser user.id must not be null");
        User u = getUserByIdFromRedis(id);
        delRedis(u);
        return mapper.updateUser(user);
    }


    @Override
    public User getUserByIdFromRedis(String id) {
        Assert.notNull(id, "UserDaoImpl.getUserById id must not be null");
        String json = redisService.get(REDIS_USER_ENTITY_PROJECT_TYPE_ID, id);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), User.class);
        }
        User user = new User();
        user.setId(id);
        return getUserFromMysql(user);
    }


    @Override
    public User getUserByProjectTypeAndLoginNameFromRedis(String projectType, String loginName) {
        Assert.notNull(projectType, "UserDaoImpl.getUserByLoginNameFromRedis projectType must not be null");
        Assert.notNull(loginName, "UserDaoImpl.getUserByLoginNameFromRedis loginName must not be null");
        String json = redisService.get(REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME, projectType + ":" + loginName);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.toJavaObject(JSONObject.parseObject(json), User.class);
        }
        User user = new User();
        user.setLoginName(loginName);
        user.setProjectType(projectType);
        return getUserFromMysql(user);
    }


    @Override
    public List<User> listUserByRoleIdFromRedis(String roleId) {
        Assert.notNull(roleId, "UserDaoImpl.listUserByRoleIdFromRedis roleId must not be null");
        String json = redisService.get(REDIS_USER_LIST_ROLE_ID, roleId);
        if (StringUtil.isNotEmpty(json)) {
            return JSONObject.parseObject(json, new TypeReference<List<User>>() {
            });
        }
        List<User> list = mapper.listUserByRoleId(roleId);
        if (null != list && list.size() > 0) {
            redisService.setEntity(REDIS_USER_LIST_ROLE_ID, roleId);
            return list;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public User getUserFromMysql(User user) {
        user = mapper.getUser(user);
        if (null != user) {
            setRedis(user);
            return user;
        } else {
            return new User();
        }
    }


    @Override
    public Datagrid loadDatagridFromMysql(User user, Page page) {
        Assert.notNull(page, "UserDaoImpl.loadDatagridFromMysql page must not be null");
        PageHelper.startPage(page.getPage(), page.getRows());
        List<User> list = mapper.listUser(user);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        Datagrid datagrid = new Datagrid();
        datagrid.setTotal(pageInfo.getTotal());
        datagrid.setRows(list);
        return datagrid;
    }


    private void setRedis(User user) {
        String projectType = user.getProjectType();
        if (StringUtil.isEmpty(projectType)) {
            projectType = "0";
        }
        String id = user.getId();
        if (StringUtil.isNotEmpty(id)) {
            redisService.setEntity(REDIS_USER_ENTITY_PROJECT_TYPE_ID, projectType + ":" + id, user);
        }
        String mobile = user.getMobile();
        if (StringUtil.isNotEmpty(mobile)) {
            redisService.setEntity(REDIS_USER_ENTITY_PROJECT_TYPE_MOBILE, projectType + ":" + mobile, user);
        }
        String loginName = user.getLoginName();
        if (StringUtil.isNotEmpty(loginName)) {
            redisService.setEntity(REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME, projectType + ":" + loginName, user);
        }
        String wxOpenId = user.getWxOpenId();
        if (StringUtil.isNotEmpty(wxOpenId)) {
            redisService.setEntity(REDIS_USER_ENTITY_LOGIN_WX_OPEN_ID, wxOpenId, user);
        }
    }


    private void delRedis(User user) {
        String projectType = user.getProjectType();
        if (StringUtil.isEmpty(projectType)) {
            projectType = "0";
        }
        String id = user.getId();
        if (StringUtil.isNotEmpty(id)) {
            redisService.del(REDIS_USER_ENTITY_PROJECT_TYPE_ID, projectType + ":" + id);
        }
        String mobile = user.getMobile();
        if (StringUtil.isNotEmpty(mobile)) {
            redisService.del(REDIS_USER_ENTITY_PROJECT_TYPE_MOBILE, projectType + ":" + mobile);
        }
        String loginName = user.getLoginName();
        if (StringUtil.isNotEmpty(loginName)) {
            redisService.del(REDIS_USER_ENTITY_PROJECT_TYPE_LOGIN_NAME, projectType + ":" + loginName);
        }
        String wxOpenId = user.getWxOpenId();
        if (StringUtil.isNotEmpty(wxOpenId)) {
            redisService.del(REDIS_USER_ENTITY_LOGIN_WX_OPEN_ID, wxOpenId);
        }
        getUserFromMysql(user);
    }
}