package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgSkuPicture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgSkuPictureMapper {
    /**
     * @Description 保存GzgSkuPicture
     * @Author taoye
     */
    void insertGzgSkuPicture(GzgSkuPicture gzgSkuPicture);

    /**
     * @Description 删除GzgSkuPicture
     * @Author taoye
     */
    void deleteGzgSkuPicture(GzgSkuPicture gzgSkuPicture);

    /**
     * @Description 更新GzgSkuPicture
     * @Author taoye
     */
    int updateGzgSkuPicture(GzgSkuPicture gzgSkuPicture);

    /**
     * @Description 查询GzgSkuPicture
     * @Author taoye
     */
    GzgSkuPicture getGzgSkuPicture(GzgSkuPicture gzgSkuPicture);
}