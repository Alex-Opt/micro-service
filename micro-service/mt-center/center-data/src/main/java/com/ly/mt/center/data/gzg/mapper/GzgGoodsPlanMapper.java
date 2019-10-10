package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgGoodsPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GzgGoodsPlanMapper {

    List<GzgGoodsPlan> getGzgGoodsPlan(GzgGoodsPlan gzgGoodsPlan);


    int updateGzgGoodsPlanNum(GzgGoodsPlan gzgGoodsPlan);


    int updateGzgGoodsPlanNumByCondation(GzgGoodsPlan gzgGoodsPlan);

    int insertGzgGoodsPlan(GzgGoodsPlan gzgGoodsPlan);

    /**
     * 批量添加信息
     * @param list
     * @return
     */
    int insertBatchGzgGoodsPlan(@Param("list") List<GzgGoodsPlan> list);

    /**
     * 下架格子柜
     * @param imei
     * @return
     */
    @Update("UPDATE gzg_goods_plan SET state = 0 WHERE imei = #{imei}")
    int downCabinetByImei(@Param("imei") String imei);

    /**
     * 更新更新信息
     * @param gzgGoodsPlan
     * @return
     */
    int updateGzgGoodsPlan(GzgGoodsPlan gzgGoodsPlan);


    /**
     *  BD补货 更新展柜的库存-1 销售量+1
     * @param gzgGoodsPlan
     * @return
     */
    int updateGzgZgGoodsPlanNum(GzgGoodsPlan gzgGoodsPlan);

    /**
     *  展柜补货 在原库存基础上加上本次添加的库存
     * @param gzgGoodsPlan
     * @return
     */
    int updateGzgGoodsPlanStock(GzgGoodsPlan gzgGoodsPlan);

    /**
     *  BD补货 原先计划没有的情况下批量插入
     * @param list
     * @return
     */
    int insertBatchGzgGoodsPlans(List<GzgGoodsPlan> list);

    /**
     * 上架格子柜
     * @param imei
     * @return
     */
    @Update("UPDATE gzg_goods_plan SET state = 1 WHERE imei = #{imei}")
    int upCabinetByImei(@Param("imei") String imei);

    /**
     * 查询货柜中是包含套装商品的库存数量
     * @param imei
     * @return
     */
    int getGzgGoodsPlanSuitNum(@Param("imei") String imei);

    /**
     * 查询展柜top3是否都已经卖完
     * @param skuIdList
     * @param imei
     * @return
     */
    List<GzgGoodsPlan> getTop3GzgGoodsPlanStock(@Param("skuIdList") List<String> skuIdList,@Param("imei") String imei);

    @Update("update gzg_goods_plan set stock=0 where imei=#{imei}")
    int updateStockByImei(@Param("imei") String imei);
}