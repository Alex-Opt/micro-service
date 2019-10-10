package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgReplenishOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgReplenishOrderMapper {
    /**
     * @Description 保存GzgReplenishOrder
     * @Author taoye
     */
    void insertGzgReplenishOrder(GzgReplenishOrder gzgReplenishOrder);

    /**
     * @Description 删除GzgReplenishOrder
     * @Author taoye
     */
    void deleteGzgReplenishOrder(GzgReplenishOrder gzgReplenishOrder);

    /**
     * @Description 更新GzgReplenishOrder
     * @Author taoye
     */
    int updateGzgReplenishOrder(GzgReplenishOrder gzgReplenishOrder);

    /**
     * @Description 查询GzgReplenishOrder
     * @Author taoye
     */
    GzgReplenishOrder getGzgReplenishOrder(GzgReplenishOrder gzgReplenishOrder);
}