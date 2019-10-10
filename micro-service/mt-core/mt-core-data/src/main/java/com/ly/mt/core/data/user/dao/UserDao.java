package com.ly.mt.core.data.user.dao;

import com.ly.mt.core.base.eaeyui.Datagrid;
import com.ly.mt.core.base.eaeyui.Page;
import com.ly.mt.core.data.user.entity.User;

import java.util.List;

/**
 * User操作接口
 *
 * @author taoye
 */
public interface UserDao {
    /**
     * 新增User
     *
     * @param user 新增数据
     * @author taoye
     */
    void insertUser(User user);

    /**
     * 根据id更新User
     *
     * @param user 更新条件和更新数据
     * @return 更新条数
     * @author taoye
     */
    int updateUser(User user);

    /**
     * 从redis根据id查询User
     * redis不存在则查询mysql
     *
     * @param id 用户ID
     * @return User
     * @author taoye
     */
    User getUserByIdFromRedis(String id);

    /**
     * 从redis根据projectType、loginName查询User
     * redis不存在则查询mysql
     *
     * @param projectType 项目类型
     * @param loginName   用户帐号
     * @return User
     * @author taoye
     */
    User getUserByProjectTypeAndLoginNameFromRedis(String projectType, String loginName);

    /**
     * 从redis根据roleId查询List<User>
     * redis不存在则查询mysql
     *
     * @param roleId 角色id
     * @return List<User>
     * @author taoye
     */
    List<User> listUserByRoleIdFromRedis(String roleId);

    /**
     * 从mysql查询User
     *
     * @param user 查询条件
     * @return User
     * @author taoye
     */
    User getUserFromMysql(User user);

    /**
     * easyui datagrid 查询
     *
     * @param user 查询条件
     * @param page 分页条件
     * @return Datagrid
     * @author taoye
     */
    Datagrid loadDatagridFromMysql(User user, Page page);
}