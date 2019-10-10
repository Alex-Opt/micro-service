package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserReals;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRealsMapper {
    /**
     * @Description 保存UserReals
     * @Author taoye
     */
    void insertUserReals(UserReals userReals);

    /**
     * @Description 删除UserReals
     * @Author taoye
     */
    void deleteUserReals(UserReals userReals);

    /**
     * @Description 更新UserReals
     * @Author taoye
     */
    int updateUserReals(UserReals userReals);

    /**
     * @Description 查询UserReals
     * @Author taoye
     */
    UserReals getUserReals(UserReals userReals);
}