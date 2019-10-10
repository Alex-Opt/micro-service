package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSpuPicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSpuPictureMapper {
    /**
     * @Description 插入GoodsSpuPicture
     * @Author taoye
     */
    void insertGoodsSpuPicture(GoodsSpuPicture goodsSpuPicture);

    /**
     * @Description 根据id删除GoodsSpuPicture
     * @Author taoye
     */
    void deleteGoodsSpuPictureById(GoodsSpuPicture goodsSpuPicture);

    /**
     * @Description 根据id更新GoodsSpuPicture
     * @Author taoye
     */
    int updateGoodsSpuPictureById(GoodsSpuPicture goodsSpuPicture);

    /**
     * @Description 根据条件查询GoodsSpuPicture
     * @Author taoye
     */
    GoodsSpuPicture getGoodsSpuPicture(GoodsSpuPicture goodsSpuPicture);

    /**
     * @Description 根据id查询GoodsSpuPicture
     * @Author taoye
     */
    GoodsSpuPicture getGoodsSpuPictureById(GoodsSpuPicture goodsSpuPicture);

    /**
     * 根据spu id查询图片数据
     * @param goodsSpuPicture
     * @return
     */
    List<GoodsSpuPicture> getGoodsSpuPictureBySpuId(GoodsSpuPicture goodsSpuPicture);
}