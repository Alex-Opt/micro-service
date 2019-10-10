package com.ly.mt.center.data.shop.mapper;

import com.ly.mt.center.data.shop.entity.ShopGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopGradeMapper {
    /**
     * @Description 保存ShopGrade
     * @Author taoye
     */
    void insertShopGrade(ShopGrade shopGrade);

    /**
     * @Description 删除ShopGrade
     * @Author taoye
     */
    void deleteShopGrade(ShopGrade shopGrade);

    /**
     * @Description 更新ShopGrade
     * @Author taoye
     */
    int updateShopGrade(ShopGrade shopGrade);

    /**
     * @Description 查询ShopGrade
     * @Author taoye
     */
    ShopGrade getShopGrade(ShopGrade shopGrade);
}