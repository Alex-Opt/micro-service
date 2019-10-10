package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserUpdateLoginNameLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUpdateLoginNameLogsMapper {
    /**
     * @Description 保存UserUpdateLoginNameLogs
     * @Author taoye
     */
    void insertUserUpdateLoginNameLogs(UserUpdateLoginNameLogs userUpdateLoginNameLogs);

    /**
     * @Description 删除UserUpdateLoginNameLogs
     * @Author taoye
     */
    void deleteUserUpdateLoginNameLogs(UserUpdateLoginNameLogs userUpdateLoginNameLogs);

    /**
     * @Description 更新UserUpdateLoginNameLogs
     * @Author taoye
     */
    int updateUserUpdateLoginNameLogs(UserUpdateLoginNameLogs userUpdateLoginNameLogs);

    /**
     * @Description 查询UserUpdateLoginNameLogs
     * @Author taoye
     */
    UserUpdateLoginNameLogs getUserUpdateLoginNameLogs(UserUpdateLoginNameLogs userUpdateLoginNameLogs);
}