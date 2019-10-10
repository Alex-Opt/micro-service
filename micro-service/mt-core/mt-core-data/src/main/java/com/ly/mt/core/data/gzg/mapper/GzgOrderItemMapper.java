package com.ly.mt.core.data.gzg.mapper;

import com.ly.mt.core.data.gzg.entity.GzgOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * GzgOrderItem操作接口
 *
 * @author taoye
 */
@Mapper
public interface GzgOrderItemMapper {
    /**
     * 查询List<GzgOrderItem>
     *
     * @author taoye
     */
    List<GzgOrderItem> listGzgOrderItem(GzgOrderItem gzgOrderItem);
}