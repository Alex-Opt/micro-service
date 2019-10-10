package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.gzg.GzgReplenishRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GzgReplenishRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgReplenishRecord record);

    int insertSelective(GzgReplenishRecord record);

    GzgReplenishRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgReplenishRecord record);

    int updateByPrimaryKey(GzgReplenishRecord record);

    List<GzgReplenishRecord> selectByUserId(@Param("userId") long userId);

    List<GzgReplenishRecord> selectByUserIdAndStatus(@Param("userId") long userId,@Param("status") int status);

    GzgReplenishRecord selectByOrderId(long orderId);

    List<GzgReplenishRecord> selectByStatus(@Param("status") int status);

    GzgReplenishRecord selectByReplenishOrderId(@Param("replenishOrderId") long replenishOrderId);
}