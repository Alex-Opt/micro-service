package com.ly.mt.center.data.share.mapper;

import com.ly.mt.center.data.share.entity.ShareGoodsPictureDel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareGoodsPictureDelMapper {
    /**
     * @Description 保存ShareGoodsPictureDel
     * @Author taoye
     */
    void insertShareGoodsPictureDel(ShareGoodsPictureDel shareGoodsPictureDel);

    /**
     * @Description 删除ShareGoodsPictureDel
     * @Author taoye
     */
    void deleteShareGoodsPictureDel(ShareGoodsPictureDel shareGoodsPictureDel);

    /**
     * @Description 更新ShareGoodsPictureDel
     * @Author taoye
     */
    int updateShareGoodsPictureDel(ShareGoodsPictureDel shareGoodsPictureDel);

    /**
     * @Description 查询ShareGoodsPictureDel
     * @Author taoye
     */
    ShareGoodsPictureDel getShareGoodsPictureDel(ShareGoodsPictureDel shareGoodsPictureDel);
}