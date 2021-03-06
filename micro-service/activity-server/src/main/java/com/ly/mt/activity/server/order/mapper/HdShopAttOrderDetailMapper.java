package com.ly.mt.activity.server.order.mapper;

import com.ly.mt.core.common.entity.hd.HdShopAttOrderDetail;
import com.ly.mt.core.common.entity.hd.model.HdShopOrderDetailGoodsModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HdShopAttOrderDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    int insert(HdShopAttOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    int insertSelective(HdShopAttOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    HdShopAttOrderDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HdShopAttOrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_order_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HdShopAttOrderDetail record);

    /**
     * v1.
     * 查询订单详情接口带商品信息的
     * @param orderIds
     * @return
     */
    List<HdShopOrderDetailGoodsModel> findDetailsByOrders(@Param("orderIds") List<String> orderIds);


    /**
     * v2.
     * 查询订单详情接口带商品信息，整合front_categort的
     * @param orderIds
     * @return
     */
    List<HdShopOrderDetailGoodsModel> findDetailFrontByOrders(@Param("orderIds")List<String> orderIds);

    /**
     * 查询价格
     * @param skuId
     * @return
     */
    @Select("select market_price from goods_sku_info where id = #{skuId}")
    Double findSkuPrice(@Param("skuId") Long skuId);
}