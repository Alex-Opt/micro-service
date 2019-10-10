package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsSpuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsSpuInfoMapper {
    /**
     * @Description 插入GoodsSpuInfo
     * @Author taoye
     */
    void insertGoodsSpuInfo(GoodsSpuInfo goodsSpuInfo);

    /**
     * @Description 根据id删除GoodsSpuInfo
     * @Author taoye
     */
    void deleteGoodsSpuInfoById(GoodsSpuInfo goodsSpuInfo);

    /**
     * @Description 根据id更新GoodsSpuInfo
     * @Author taoye
     */
    int updateGoodsSpuInfoById(GoodsSpuInfo goodsSpuInfo);

    /**
     * @Description 根据条件查询GoodsSpuInfo
     * @Author taoye
     */
    GoodsSpuInfo getGoodsSpuInfo(GoodsSpuInfo goodsSpuInfo);

    /**
     * @Description 根据id查询GoodsSpuInfo
     * @Author taoye
     */
    GoodsSpuInfo getGoodsSpuInfoById(GoodsSpuInfo goodsSpuInfo);

    /**
     * 根据分类id查询商品spu数据
     * @param goodsSpuInfo
     * @return
     */
    List<GoodsSpuInfo> getGoodsSpuInfoByCategroy(GoodsSpuInfo goodsSpuInfo);

    /**
     * 分页查询一小时达商品spu
     * @return
     */
    List<GoodsSpuInfo> queryHourSpu();

    /**
     * 查询商品月销量
     * @param spuId
     * @param earlyMonthStr 本月初的值
     * @param nowTimeStr 现在的值
     * @return
     */
    String getGoodsSellerNumberByMonth(@Param("spuId")Long spuId,@Param("earlyMonthStr")String earlyMonthStr,@Param("nowTimeStr")String nowTimeStr);

    /**
     * 查询top5商品
     * @param goodsSpuInfo
     * @return
     */
    List<GoodsSpuInfo> getListTop5ByCid(GoodsSpuInfo goodsSpuInfo);

   /** 查询有效的商品数据
     * @return
     */
    List<GoodsSpuInfo> queryGoodsSpuInfo();

    /**
     * 到家b-根据分类id查询商品spu数据
     * @param goodsSpuInfo
     * @return
     */
    List<GoodsSpuInfo> getSpuListForShop(GoodsSpuInfo goodsSpuInfo);


    /**
     * 根据spuId的集合查询出对应的商品信息集合
     * @param list
     * @return
     */
    List<GoodsSpuInfo> getGoodsSpuInfoBySpuIdList(@Param("list")List list);
}