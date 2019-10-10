package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserWalletLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserWalletLogsMapper {
    /**
     * @Description 保存UserWalletLogs
     * @Author taoye
     */
    void insertUserWalletLogs(UserWalletLogs userWalletLogs);

    /**
     * @Description 删除UserWalletLogs
     * @Author taoye
     */
    void deleteUserWalletLogs(UserWalletLogs userWalletLogs);

    /**
     * @Description 更新UserWalletLogs
     * @Author taoye
     */
    int updateUserWalletLogs(UserWalletLogs userWalletLogs);

    /**
     * @Description 查询UserWalletLogs
     * @Author taoye
     */
    UserWalletLogs getUserWalletLogs(UserWalletLogs userWalletLogs);
}