package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserPunchCard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPunchCardMapper {
    /**
     * @Description 插入UserPunchCard
     * @Author taoye
     */
    void insertUserPunchCard(UserPunchCard userPunchCard);

    /**
     * @Description 根据id删除UserPunchCard
     * @Author taoye
     */
    void deleteUserPunchCardById(UserPunchCard userPunchCard);

    /**
     * @Description 根据id更新UserPunchCard
     * @Author taoye
     */
    int updateUserPunchCardById(UserPunchCard userPunchCard);

    /**
     * @Description 根据条件查询UserPunchCard
     * @Author taoye
     */
    UserPunchCard getUserPunchCard(UserPunchCard userPunchCard);

    /**
     * @Description 根据id查询UserPunchCard
     * @Author taoye
     */
    UserPunchCard getUserPunchCardById(UserPunchCard userPunchCard);
}