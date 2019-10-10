package com.ly.mt.center.data.warehouse.mapper;

import com.ly.mt.center.data.warehouse.entity.WarehouseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseInfoMapper {
    /**
     * @Description 保存WarehouseInfo
     * @Author taoye
     */
    void insertWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * @Description 删除WarehouseInfo
     * @Author taoye
     */
    void deleteWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * @Description 更新WarehouseInfo
     * @Author taoye
     */
    int updateWarehouseInfo(WarehouseInfo warehouseInfo);

    /**
     * @Description 查询WarehouseInfo
     * @Author taoye
     */
    WarehouseInfo getWarehouseInfo(WarehouseInfo warehouseInfo);
}