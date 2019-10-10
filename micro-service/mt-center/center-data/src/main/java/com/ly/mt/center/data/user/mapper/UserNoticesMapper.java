package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserNotices;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserNoticesMapper {
    /**
     * @Description 保存UserNotices
     * @Author taoye
     */
    void insertUserNotices(UserNotices userNotices);

    /**
     * @Description 删除UserNotices
     * @Author taoye
     */
    void deleteUserNotices(UserNotices userNotices);

    /**
     * @Description 更新UserNotices
     * @Author taoye
     */
    int updateUserNotices(UserNotices userNotices);

    /**
     * @Description 查询UserNotices
     * @Author taoye
     */
    UserNotices getUserNotices(UserNotices userNotices);
}