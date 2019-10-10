package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgCouponGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgCouponGoodsMapper {
    /**
     * @Description 保存GzgCouponGoods
     * @Author taoye
     */
    void insertGzgCouponGoods(GzgCouponGoods gzgCouponGoods);

    /**
     * @Description 删除GzgCouponGoods
     * @Author taoye
     */
    void deleteGzgCouponGoods(GzgCouponGoods gzgCouponGoods);

    /**
     * @Description 更新GzgCouponGoods
     * @Author taoye
     */
    int updateGzgCouponGoods(GzgCouponGoods gzgCouponGoods);

    /**
     * @Description 查询GzgCouponGoods
     * @Author taoye
     */
    GzgCouponGoods getGzgCouponGoods(GzgCouponGoods gzgCouponGoods);
}