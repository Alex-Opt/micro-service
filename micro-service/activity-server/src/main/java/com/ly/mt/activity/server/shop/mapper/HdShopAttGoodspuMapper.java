package com.ly.mt.activity.server.shop.mapper;

import com.ly.mt.core.common.entity.hd.vo.FrontCategoryVo;
import com.ly.mt.core.common.entity.hd.vo.FrontSkuVo;
import com.ly.mt.core.common.entity.hd.HdShopAttGoodspu;
import com.ly.mt.core.common.entity.hd.model.HdGoodsSkuModel;
import com.ly.mt.core.common.entity.hd.vo.HdGoodsSpuInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description
 *
 * 门店活动商店页面下边门店活动集合对应的商品表
 *
 * @author panjingtian
 * @date 2019/6/14 3:41 PM
 */
@Mapper
public interface HdShopAttGoodspuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    int insert(HdShopAttGoodspu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    int insertSelective(HdShopAttGoodspu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    HdShopAttGoodspu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(HdShopAttGoodspu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hd_shop_att_goods_spu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(HdShopAttGoodspu record);

    /**
     * 查询活动门店旗下商品
     * @param activityid
     * @return
     */
    List<HdGoodsSpuInfoVo> findByActivityShopId(@Param("activityid") Long activityid);

    /**
     * 查询商品spu下的所有sku商品
     * @param list
     * @return
     */
    List<HdGoodsSkuModel> findSkuInSpuId(@Param("list") List<Long> list);

    /**
     * v2更新
     * 活动前端展示商品信息
     * @param activityId
     * @return
     */
    @Select("   SELECT * from front_category WHERE id in(" +
            "select DISTINCT(front_category_id)  from  hd_shop_att_goods_front where activity_id = #{activityId} )")
    List<FrontCategoryVo> findFrontByActivityId(@Param("activityId")Long activityId);

    /**
     * 查询前端显示的sku
     * @param fIds
     * @return
     */
    List<FrontSkuVo> findFrontSkus(@Param("list") List<Long> fIds);
}