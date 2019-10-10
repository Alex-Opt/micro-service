package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *展柜-补货订单-持久层
 * @author wanghongliang
 * @date 2019-09-18
 */
@Mapper
public interface CabinetZgReplenishOrderMapper {
    /**
     * 新增一条展柜补货订单
     * @param cabinetZgReplenishOrder
     * @return
     */
    int insert(CabinetZgReplenishOrder cabinetZgReplenishOrder);

    /**
     * 更新展柜补货订单
     * @param cabinetZgReplenishOrder
     * @return
     */
    int updateCabinetZgReplenishById(CabinetZgReplenishOrder cabinetZgReplenishOrder);

    /**
     * 查询展柜补货列表
     * @param
     * @return
     */
    List<CabinetZgReplenishOrder> getCabinetZgReplenishByCondtion(CabinetZgReplenishOrder cabinetZgReplenishOrder);
}
