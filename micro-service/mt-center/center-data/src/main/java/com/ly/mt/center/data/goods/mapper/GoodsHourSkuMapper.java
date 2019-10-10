package com.ly.mt.center.data.goods.mapper;

import com.ly.mt.center.data.goods.entity.GoodsHourSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsHourSkuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsHourSku record);

    int insertSelective(GoodsHourSku record);

    GoodsHourSku selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsHourSku record);

    int updateByPrimaryKey(GoodsHourSku record);

    List<GoodsHourSku>  selectGoodsHourSkuList(@Param("list") List<String> list);


    GoodsHourSku getGoodsHourSku(GoodsHourSku record);
}