package com.ly.mt.cabinet.c.programme.service;

import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgProgrammeService {
    /**
     * 绑定终端设备信息
     * @param tid
     * @throws Exception
     */
     void bindRunjinDevice(String tid) throws Exception;

    /**
     * 查询终端在线状态
     * @param tid
     * @return
     * @throws Exception
     */
     Boolean isOnline(String tid) throws Exception;

    /**
     * 发出打开柜门命令
     * @param orderId
     * @param tid
     * @param hd
     * @param gzgPayTypeEnum
     * @return
     * @throws Exception
     */
     ResponseJson openRujinDevice(String orderId, String tid, String hd, GzgPayTypeEnum gzgPayTypeEnum) throws Exception;

    /**
     * 设备补货打开柜门
     * @param tid
     * @return
     * @throws Exception
     */
    ResponseJson maintainDevice(String tid) throws  Exception;

}
