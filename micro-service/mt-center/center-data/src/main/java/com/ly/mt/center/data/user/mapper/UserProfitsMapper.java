package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserProfits;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfitsMapper {
    /**
     * @Description 保存UserProfits
     * @Author taoye
     */
    void insertUserProfits(UserProfits userProfits);

    /**
     * @Description 删除UserProfits
     * @Author taoye
     */
    void deleteUserProfits(UserProfits userProfits);

    /**
     * @Description 更新UserProfits
     * @Author taoye
     */
    int updateUserProfits(UserProfits userProfits);

    /**
     * @Description 查询UserProfits
     * @Author taoye
     */
    UserProfits getUserProfits(UserProfits userProfits);
}