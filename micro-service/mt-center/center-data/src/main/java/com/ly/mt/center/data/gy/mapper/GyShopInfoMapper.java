package com.ly.mt.center.data.gy.mapper;

import com.ly.mt.center.data.gy.entity.GyShopInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GyShopInfoMapper {
    /**
     * @Description 保存GyShopInfo
     * @Author taoye
     */
    void insertGyShopInfo(GyShopInfo gyShopInfo);

    /**
     * @Description 删除GyShopInfo
     * @Author taoye
     */
    void deleteGyShopInfo(GyShopInfo gyShopInfo);

    /**
     * @Description 更新GyShopInfo
     * @Author taoye
     */
    int updateGyShopInfo(GyShopInfo gyShopInfo);

    /**
     * @Description 查询GyShopInfo
     * @Author taoye
     */
    GyShopInfo getGyShopInfo(GyShopInfo gyShopInfo);
}