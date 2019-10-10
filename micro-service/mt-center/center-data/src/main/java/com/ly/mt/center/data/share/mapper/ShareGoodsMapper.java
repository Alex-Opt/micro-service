package com.ly.mt.center.data.share.mapper;

import com.ly.mt.center.data.share.entity.ShareGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShareGoodsMapper {
    /**
     * @Description 保存ShareGoods
     * @Author taoye
     */
    void insertShareGoods(ShareGoods shareGoods);

    /**
     * @Description 删除ShareGoods
     * @Author taoye
     */
    void deleteShareGoods(ShareGoods shareGoods);

    /**
     * @Description 更新ShareGoods
     * @Author taoye
     */
    int updateShareGoods(ShareGoods shareGoods);

    /**
     * @Description 查询ShareGoods
     * @Author taoye
     */
    ShareGoods getShareGoods(ShareGoods shareGoods);
}