package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFeedbackMapper {
    /**
     * @Description 保存UserFeedback
     * @Author taoye
     */
    void insertUserFeedback(UserFeedback userFeedback);

    /**
     * @Description 删除UserFeedback
     * @Author taoye
     */
    void deleteUserFeedback(UserFeedback userFeedback);

    /**
     * @Description 更新UserFeedback
     * @Author taoye
     */
    int updateUserFeedback(UserFeedback userFeedback);

    /**
     * @Description 查询UserFeedback
     * @Author taoye
     */
    UserFeedback getUserFeedback(UserFeedback userFeedback);
}