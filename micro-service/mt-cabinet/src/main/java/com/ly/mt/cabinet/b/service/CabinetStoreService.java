package com.ly.mt.cabinet.b.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.cabinet.b.common.request.WarehouseKeeperVo;
import com.ly.mt.core.base.entity.ResponseJson;


public interface CabinetStoreService {

    /**
     *  仓库人员通过自己的user_id获取所负责区域所有商家的信息
     * @return
     */
    ResponseJson getCabinetStoreByUserId(WarehouseKeeperVo warehouseKeeperVo ,String  user_id);


    /**
     *  仓库人员获取所有未使用商家信息
     * @return
     */
    ResponseJson getCabinetStoreNotUsed();

    /**
     *  仓库人员获取所有已上线商家信息
     * @return
     */
    ResponseJson getCabinetStoreIsOnline();


    /**
     *  仓库人员查看商户详情
     * @return
     */
    ResponseJson getCabinetStoreDetail(String storeId);


}
