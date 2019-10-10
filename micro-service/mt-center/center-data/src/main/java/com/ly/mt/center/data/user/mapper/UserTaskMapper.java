package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTaskMapper {
    /**
     * @Description 保存UserTask
     * @Author taoye
     */
    void insertUserTask(UserTask userTask);

    /**
     * @Description 删除UserTask
     * @Author taoye
     */
    void deleteUserTask(UserTask userTask);

    /**
     * @Description 更新UserTask
     * @Author taoye
     */
    int updateUserTask(UserTask userTask);

    /**
     * @Description 查询UserTask
     * @Author taoye
     */
    UserTask getUserTask(UserTask userTask);
}