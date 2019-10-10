package com.ly.mt.core.data.gzg.mapper;

import com.ly.mt.core.data.gzg.entity.GzgOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GzgOrder操作接口
 *
 * @author taoye
 */
@Mapper
public interface GzgOrderMapper {
    /**
     * 查询GzgOrder
     *
     * @param gzgOrder 查询条件
     * @return GzgOrder
     * @author taoye
     */
    GzgOrder getGzgOrder(GzgOrder gzgOrder);

    /**
     * 查询List<GzgOrder>
     *
     * @param gzgOrder 查询条件
     * @return List<GzgOrder>
     * @author taoye
     */
    List<GzgOrder> listGzgOrder(GzgOrder gzgOrder);
}