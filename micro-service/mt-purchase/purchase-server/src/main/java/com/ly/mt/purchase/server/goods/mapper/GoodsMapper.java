package com.ly.mt.purchase.server.goods.mapper;

import com.ly.mt.core.common.entity.purchase.GoodsCategroyInfo;
import com.ly.mt.core.common.entity.purchase.GoodsSkuInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类目
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-17 23:47:47
 */
@Mapper
public interface GoodsMapper {

    /**
     * 根据指定类目父id查询所有一级子节点
     * @param parentId
     * @return
     */
    List<GoodsCategroyInfo> selectCategroyListByParentId(@Param("parentId") Long parentId);

    /**
     * 根据类目id查询销售榜单
     * @param cid
     * @return
     */
    List<GoodsSkuInfoVO> selectListTop5ByCid(@Param("cid") Long cid);
}
