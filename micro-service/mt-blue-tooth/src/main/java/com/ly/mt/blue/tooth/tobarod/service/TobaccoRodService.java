package com.ly.mt.blue.tooth.tobarod.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 烟杆操作接口
 */
public interface TobaccoRodService {

    /**
     * 用户绑定烟杆
     * @throws Exception
     */
    ResponseJson bindTobaccoRod(String name,String macAddress) throws Exception;

    /**
     * 用户解绑烟杆
     * @throws Exception
     */
    ResponseJson unbindTobaccoRod(String id) throws Exception;

    /**
     * 修改烟杆名称
     * @throws Exception
     */
    ResponseJson modifyTobaccoRod(String id,String name) throws Exception;

    /**
     * 获取用户绑定烟杆列表
     * @throws Exception
     */
    ResponseJson getTobaccoRodList() throws Exception;

    /**
     * 烟杆设置儿童锁
     * @throws Exception
     */
    ResponseJson settingChildLock(String action,String id) throws Exception;

    /**
     * 查询烟杆电池电量详情
     * @throws Exception
     */
    ResponseJson getElectricQuantityDetail(String id) throws Exception;
}