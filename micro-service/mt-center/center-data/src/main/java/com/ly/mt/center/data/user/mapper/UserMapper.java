package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * @Description 插入User
     * @Author taoye
     */
    void insertUser(User user);

    /**
     * @Description 删除User
     * @Author taoye
     */
    void deleteUser(User user);

    /**
     * @Description 更新User
     * @Author taoye
     */
    int updateUser(User user);

    /**
     * @Description 查询User
     * @Author taoye
     */
    User getUser(User user);
    /**
     * @Description 查询User
     * @Author WHL 更换登录账号时只需要根据id去查询/不可根据登录账号去查,因为此时入参已是最新手机号
     */
    User getUserById(User user);

    /**
     * @Description 更新User
     * @Author WHL 更新登录账号时只需要根据id去查询/不可根据登录账号去查,因为此时入参已是最新手机号
     */
    int updateUserById(User user);
    /**
     * @Description 蓝牙-根据条件查询User
     * @Author taoye
     */
    List<User> getUserByCondtions(User user);

    int setPassword(User user);

    User getByPhoneNo(@Param("phone") String phone);

    @Select("select user_name from user where id = #{userId}")
    String findBDNameByUserId(@Param("userId") long userId);
}