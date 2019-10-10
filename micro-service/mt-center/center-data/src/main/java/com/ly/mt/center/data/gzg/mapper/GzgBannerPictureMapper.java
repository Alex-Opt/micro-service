package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgBannerPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgBannerPictureMapper {
    /**
     * @Description 插入GzgBannerPicture
     * @Author taoye
     */
    void insertGzgBannerPicture(GzgBannerPicture gzgBannerPicture);

    /**
     * @Description 根据id删除GzgBannerPicture
     * @Author taoye
     */
    void deleteGzgBannerPicture(GzgBannerPicture gzgBannerPicture);

    /**
     * @Description 根据id更新GzgBannerPicture
     * @Author taoye
     */
    int updateGzgBannerPicture(GzgBannerPicture gzgBannerPicture);

    /**
     * @Description 根据条件查询GzgBannerPicture
     * @Author taoye
     */
    List<GzgBannerPicture> getGzgBannerPicture(GzgBannerPicture gzgBannerPicture);

    /**
     * @Description 根据id查询GzgBannerPicture
     * @Author taoye
     */
    GzgBannerPicture getGzgBannerPictureById(GzgBannerPicture gzgBannerPicture);
}