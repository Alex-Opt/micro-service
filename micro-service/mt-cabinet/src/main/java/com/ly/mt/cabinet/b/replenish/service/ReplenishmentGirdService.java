package com.ly.mt.cabinet.b.replenish.service;

import com.ly.mt.core.base.entity.ResponseJson;
import org.springframework.web.multipart.MultipartFile;

/**
 * 格子柜补货流程
 */
public interface ReplenishmentGirdService {
    /**
     * 生成补货订单
     * 根据货柜类型 生成格子柜订单和展柜订单
     * @param orderId
     * @return
     */
    ResponseJson createReplenishOrder(String orderId);

    /**
     * BD补货订单列表
     * @param status
     * @return
     */
    ResponseJson replenishOrderListBd(String status);

    /**
     * BD货柜效验
     * @param scanCabinetCode
     * @param replenishId
     * @return
     */
    ResponseJson cabinetValidate(String scanCabinetCode,String replenishId);

    /**
     * BD补货提交审核
     * @param replenishId
     * @param file
     * @return
     */
    ResponseJson sumbitAuditBd(String replenishId, MultipartFile file);
}
