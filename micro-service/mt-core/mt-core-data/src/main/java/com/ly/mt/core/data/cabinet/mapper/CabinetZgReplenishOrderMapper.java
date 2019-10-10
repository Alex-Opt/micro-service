package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetZgReplenishOrder;
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
     * 查询展柜补货列表
     * @param
     * @return
     */
    List<CabinetZgReplenishOrder> getCabinetZgReplenish();
}
