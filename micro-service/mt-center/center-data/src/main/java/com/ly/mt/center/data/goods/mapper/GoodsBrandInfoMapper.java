package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsBrandInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsBrandInfoMapper {
    /**
     * @Description 保存GoodsBrandInfo
     * @Author taoye
     */
    void insertGoodsBrandInfo(GoodsBrandInfo goodsBrandInfo);

    /**
     * @Description 删除GoodsBrandInfo
     * @Author taoye
     */
    void deleteGoodsBrandInfo(GoodsBrandInfo goodsBrandInfo);

    /**
     * @Description 更新GoodsBrandInfo
     * @Author taoye
     */
    int updateGoodsBrandInfo(GoodsBrandInfo goodsBrandInfo);

    /**
     * @Description 查询GoodsBrandInfo
     * @Author taoye
     */
    GoodsBrandInfo getGoodsBrandInfo(GoodsBrandInfo goodsBrandInfo);
}