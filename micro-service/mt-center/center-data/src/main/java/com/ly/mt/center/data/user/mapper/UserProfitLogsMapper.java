package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserProfitLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProfitLogsMapper {
    /**
     * @Description 保存UserProfitLogs
     * @Author taoye
     */
    void insertUserProfitLogs(UserProfitLogs userProfitLogs);

    /**
     * @Description 删除UserProfitLogs
     * @Author taoye
     */
    void deleteUserProfitLogs(UserProfitLogs userProfitLogs);

    /**
     * @Description 更新UserProfitLogs
     * @Author taoye
     */
    int updateUserProfitLogs(UserProfitLogs userProfitLogs);

    /**
     * @Description 查询UserProfitLogs
     * @Author taoye
     */
    UserProfitLogs getUserProfitLogs(UserProfitLogs userProfitLogs);
}