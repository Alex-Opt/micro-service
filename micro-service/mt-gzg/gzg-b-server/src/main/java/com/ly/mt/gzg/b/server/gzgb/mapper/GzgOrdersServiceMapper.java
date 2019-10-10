package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgOrderItemVo;
import com.ly.mt.core.common.entity.gzg.GzgOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单主表持久层
 *
 * @Author gongjy
 * @Description
 * @Date 2019-05-21
 */
@Repository(value = "gzgOrdersServiceMapper")
@Mapper
public interface GzgOrdersServiceMapper {

    /**
     * 按非空参数新增一条订单主表信息
     *111
     * @param record
     * @return
     */
    int insert(GzgOrderVo record);


    /**
     * 批量插入订单明细表
     * @param list
     * @return
     */
    int batchInsertOrderItems(@Param("list") List<GzgOrderItemVo> list);


    /**
     * 根据订单主表id查询订单明细表查询商品信息
     *
     * @param orderId
     * @return
     */
    List<GzgOrderItemVo> getGzgOrderItemVoByOrderId(@Param("orderId") Long orderId);




    /**
     * 按照非空入参更新一条记录
     *
     * @param record
     * @return
     * @Author zhanglifeng
     * @Date 2019-05-21
     */
    int updateGzgOrderByPrimaryKey(GzgOrderVo record);

    /**
     * 通过订单编号获取订单信息
     * @param orderId
     * @return
     */
    GzgOrderVo selectByPrimaryKey(@Param("orderId") long orderId);

    List<Map<String, Object>> findOrders(Map<String, Object> param);

    GzgOrderItemVo selectItemByPrimaryKey(@Param("orderItemId") long orderItemId);


    Map<String, String> selectPictureBySkuId(@Param("skuId") long skuId);
}