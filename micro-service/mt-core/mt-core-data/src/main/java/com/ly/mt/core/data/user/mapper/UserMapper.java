package com.ly.mt.core.data.user.mapper;

import com.ly.mt.core.data.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User操作接口
 *
 * @author taoye
 */
@Mapper
public interface UserMapper {
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
     * 查询User
     *
     * @param user 查询条件
     * @return User
     * @author taoye
     */
    User getUser(User user);

    /**
     * 查询List<User>
     *
     * @param user 查询条件
     * @return List<User>
     */
    List<User> listUser(User user);

    /**
     * 根据roleId查询List<User>
     *
     * @param roleId 角色id
     * @return List<User>
     * @author taoye
     */
    List<User> listUserByRoleId(String roleId);
}