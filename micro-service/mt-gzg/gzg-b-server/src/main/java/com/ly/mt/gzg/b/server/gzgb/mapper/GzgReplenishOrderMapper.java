package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgReplenishOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GzgReplenishOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgReplenishOrder record);

    int insertSelective(GzgReplenishOrder record);

    GzgReplenishOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgReplenishOrder record);

    int updateByPrimaryKey(GzgReplenishOrder record);

    List<GzgReplenishOrder>  selectByGzgCode(@Param("gzgId") long gzgId);

    GzgReplenishOrder selectByGzgCodeAndCabinetNo(long gzgCode, int cabinetNo);

    GzgReplenishOrder selectByGzgIdAndStatus();

    List<GzgReplenishOrder> selectByGzgId(@Param("gzgId") long gzgId);

    List<GzgReplenishOrder> selectByOrderId(@Param("gzgOrderId") long gzgOrderId);

    List<GzgReplenishOrder> selectByStatus(@Param("status") int status);
    Map<String,String> selectPictureBySkuId(@Param("skuId") long skuId);

    GzgReplenishOrder selectById(@Param("replenishOrderId") long replenishOrderId);
}