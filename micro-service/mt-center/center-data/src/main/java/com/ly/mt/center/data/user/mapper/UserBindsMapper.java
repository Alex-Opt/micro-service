package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserBinds;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBindsMapper {
    /**
     * @Description 保存UserBinds
     * @Author taoye
     */
    void insertUserBinds(UserBinds userBinds);

    /**
     * @Description 删除UserBinds
     * @Author taoye
     */
    void deleteUserBinds(UserBinds userBinds);

    /**
     * @Description 更新UserBinds
     * @Author taoye
     */
    int updateUserBinds(UserBinds userBinds);

    /**
     * @Description 查询UserBinds
     * @Author taoye
     */
    UserBinds getUserBinds(UserBinds userBinds);
}