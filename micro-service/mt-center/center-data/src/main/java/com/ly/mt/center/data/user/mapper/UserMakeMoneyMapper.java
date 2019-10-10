package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserMakeMoney;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMakeMoneyMapper {
    /**
     * @Description 保存UserMakeMoney
     * @Author taoye
     */
    void insertUserMakeMoney(UserMakeMoney userMakeMoney);

    /**
     * @Description 删除UserMakeMoney
     * @Author taoye
     */
    void deleteUserMakeMoney(UserMakeMoney userMakeMoney);

    /**
     * @Description 更新UserMakeMoney
     * @Author taoye
     */
    int updateUserMakeMoney(UserMakeMoney userMakeMoney);

    /**
     * @Description 查询UserMakeMoney
     * @Author taoye
     */
    UserMakeMoney getUserMakeMoney(UserMakeMoney userMakeMoney);
}