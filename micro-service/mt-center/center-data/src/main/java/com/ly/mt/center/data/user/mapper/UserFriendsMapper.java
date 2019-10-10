package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserFriends;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFriendsMapper {
    /**
     * @Description 保存UserFriends
     * @Author taoye
     */
    void insertUserFriends(UserFriends userFriends);

    /**
     * @Description 删除UserFriends
     * @Author taoye
     */
    void deleteUserFriends(UserFriends userFriends);

    /**
     * @Description 更新UserFriends
     * @Author taoye
     */
    int updateUserFriends(UserFriends userFriends);

    /**
     * @Description 查询UserFriends
     * @Author taoye
     */
    UserFriends getUserFriends(UserFriends userFriends);
}