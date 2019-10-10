package com.ly.mt.cabinet.c.rujin.service;

import com.ly.mt.cabinet.c.enumEntity.GzgPayTypeEnum;
import com.ly.mt.cabinet.c.order.entity.GzgName;
import com.ly.mt.cabinet.c.order.entity.GzgOrderItem;
import com.ly.mt.cabinet.c.rujin.entity.RujinDoEnum;
import com.ly.mt.core.base.entity.ResponseJson;

import java.util.List;

public interface GzgRujinDeviceService {
    /**
     * 绑定终端设备信息
     * @param tid 雷炎对如金柜子命名
 *   * @param plan_name 雷炎对如金柜子的配货名称
     * @throws Exception
     */
    ResponseJson bindRunjinDevice(String tid,String plan_name) throws Exception;


    /**
     *操作终端货柜状态,若有故障或缺货，不可支付
     * @param tid
     * @param hd
     * @return
     */
    Boolean queryHgState(String tid ,String hd,String do1) throws Exception;

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
     * @return
     * @throws Exception
     */
     Boolean openRujinDevice(String orderId , String tid, String hd, GzgPayTypeEnum gzgPayTypeEnum) throws Exception;

    /**
     * 设备补货打开柜门
     * @param tid
     * @param cabinet_no 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门
     * @throws Exception
     */
    Boolean maintainDevice(String tid, String cabinet_no, RujinDoEnum rujinDoEnum) throws  Exception;

    /**
     * 设备补货打开柜门
     * @param tname
     * @param cabinet_no 多个货道号可以用“-” 区分，如： 1-3-5-6，表示 1 号， 3 号， 5 号，6 号 4 个货道同时开门
     * @throws Exception
     */
    ResponseJson maintainDeviceByTname(String tname, String cabinet_no, RujinDoEnum rujinDoEnum) throws  Exception;

    /**
     * 清除货道故障
     * @param tid
     * @param hd
     * @param rujinDoEnum
     * @return
     * @throws Exception
     */
    public Boolean hgState(String tid, String hd,RujinDoEnum rujinDoEnum)throws Exception;

    /**
     * 获取订单详情
     * @param orderNo
     * @return
     */
    List<GzgOrderItem> getGzgOrderItems(String orderNo);


    /**
     * 生成如今格子柜编号并保存数据库
     * @param id
     * @param name
     */
    String insertRuJinGzgName(String name);


    /**
     * 从数据库查询gezig编号
     * @return
     */
    List<GzgName> getGzgNameBatch(String start,String size);
}
