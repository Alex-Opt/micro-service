package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserTaskFinished;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTaskFinishedMapper {
    /**
     * @Description 保存UserTaskFinished
     * @Author taoye
     */
    void insertUserTaskFinished(UserTaskFinished userTaskFinished);

    /**
     * @Description 删除UserTaskFinished
     * @Author taoye
     */
    void deleteUserTaskFinished(UserTaskFinished userTaskFinished);

    /**
     * @Description 更新UserTaskFinished
     * @Author taoye
     */
    int updateUserTaskFinished(UserTaskFinished userTaskFinished);

    /**
     * @Description 查询UserTaskFinished
     * @Author taoye
     */
    UserTaskFinished getUserTaskFinished(UserTaskFinished userTaskFinished);
}