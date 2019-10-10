package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetBussinessCoorperation;
import com.ly.mt.center.data.cabinet.entity.CabinetInfo;
import com.ly.mt.center.data.cabinet.response.Orders;
import com.ly.mt.center.data.gzg.entity.GzgOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GzgOrdersMapper {
    /**
     * @Description 插入GzgOrders
     * @Author taoye
     */
    void insertGzgOrders(GzgOrder gzgOrders);

    /**
     * @Description 根据id删除GzgOrders
     * @Author taoye
     */
    void deleteGzgOrders(GzgOrder gzgOrders);

    /**
     * @Description 根据id更新GzgOrders
     * @Author taoye
     */
    int updateGzgOrders(GzgOrder gzgOrders);

    /**
     * @Description 根据条件查询GzgOrders
     * @Author taoye
     */
  //  GzgOrder getGzgOrders(GzgOrder gzgOrders);

    /**
     * @Description 根据id更新GzgOrders
     * @Author taoye
     */
    GzgOrder getGzgOrdersById(GzgOrder gzgOrders);


    /**
     * @Description 根据userid查询该用户订单信息
     * @Author gongjy
     */
    List<GzgOrder> getGzgOrdersList(GzgOrder gzgOrders);


    /**
     * 根据imei码查询店家的分成比例
     * @param imei
     * @return
     */
    @Select("SELECT  m.*  from  cabinet_info k, cabinet_bussiness_coorperation  m where k.store_id = m.store_id and k.imei = #{imei}  LIMIT 1 ")
    CabinetBussinessCoorperation getGzgOrderDivideScale(@Param("imei") String imei);

    /**
     * 店铺查询订单
     * @param imei
     * @param phoneNo
     * @param fullStartTime
     * @param fullEndTime
     * @return
     */
    List<Orders> findOrdersByOwner(@Param("imei") String imei,@Param("phoneNo") String phoneNo,@Param("status") int status,@Param("startTime") String fullStartTime,@Param("endTime") String fullEndTime);
}