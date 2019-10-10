package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrder;
import com.ly.mt.center.data.cabinet.entity.CabinetZgReplenishOrderReason;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *展柜-补货订单理由-持久层
 * @author wanghongliang
 * @date 2019-09-18
 */
@Mapper
public interface CabinetZgReplenishOrderReasonMapper {
    /**
     * 批量新增展柜补货订单理由
     * @param cabinetZgReplenishOrderReason
     * @return
     */
    int insertBatch(List<CabinetZgReplenishOrderReason> cabinetZgReplenishOrderReason);

    /**
     * 更新展柜补货订单理由
     * @param cabinetZgReplenishOrderReason
     * @return
     */
    int updateCabinetZgReplenishReasonById(CabinetZgReplenishOrderReason cabinetZgReplenishOrderReason);

    /**
     * 查询展柜补货列表理由
     * @param
     * @return
     */
    List<CabinetZgReplenishOrderReason> getCabinetZgReplenishReasonByCondtion(CabinetZgReplenishOrderReason cabinetZgReplenishOrderReason);
}
