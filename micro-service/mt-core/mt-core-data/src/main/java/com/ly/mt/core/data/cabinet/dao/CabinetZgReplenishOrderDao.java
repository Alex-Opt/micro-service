package com.ly.mt.core.data.cabinet.dao;

import com.ly.mt.core.data.cabinet.entity.CabinetZgReplenishOrder;

import java.util.List;

public interface CabinetZgReplenishOrderDao {

    /**
     * 查询每个BD需要补充的展柜数量
     *
     * @return
     */
    List<CabinetZgReplenishOrder> getCabinetZgReplenish();

}
