package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgOrderItemsMapper {
    /**
     * @Description 插入GzgOrderItems
     * @Author taoye
     */
    void insertGzgOrderItems(GzgOrderItem gzgOrderItems);

    /**
     * @Description 根据id删除GzgOrderItems
     * @Author taoye
     */
    void deleteGzgOrderItemsById(GzgOrderItem gzgOrderItems);

    /**
     * @Description 根据id更新GzgOrderItems
     * @Author taoye
     */
    int updateGzgOrderItemsById(GzgOrderItem gzgOrderItems);

    /**
     * @Description 根据条件查询GzgOrderItems
     * @Author taoye
     */
    List<GzgOrderItem> getGzgOrderItems(GzgOrderItem gzgOrderItems);

    /**
     * @Description 根据id查询GzgOrderItems
     * @Author taoye
     */
    List<GzgOrderItem> getGzgOrderItemsById(GzgOrderItem gzgOrderItems);

    /**
     * 根据订单号和sku更改订单明细
     * @param gzgOrderItems
     * @return
     */
    int updateGzgOrderItemsByOrderId(GzgOrderItem gzgOrderItems);

    int getSellNumBySkuId(GzgOrderItem gzgOrderItems);
}