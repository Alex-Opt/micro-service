package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserFeedbackImages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFeedbackImagesMapper {
    /**
     * @Description 保存UserFeedbackImages
     * @Author taoye
     */
    void insertUserFeedbackImages(UserFeedbackImages userFeedbackImages);

    /**
     * @Description 删除UserFeedbackImages
     * @Author taoye
     */
    void deleteUserFeedbackImages(UserFeedbackImages userFeedbackImages);

    /**
     * @Description 更新UserFeedbackImages
     * @Author taoye
     */
    int updateUserFeedbackImages(UserFeedbackImages userFeedbackImages);

    /**
     * @Description 查询UserFeedbackImages
     * @Author taoye
     */
    UserFeedbackImages getUserFeedbackImages(UserFeedbackImages userFeedbackImages);
}