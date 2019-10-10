package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetStore;
import com.ly.mt.center.data.cabinet.response.BdDataStaticsticVo;
import com.ly.mt.center.data.cabinet.response.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CabinetStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetStore record);

    int insertSelective(CabinetStore record);

    /**
     * 根据id获取店铺信息
     * @param id
     * @return
     */
    CabinetStore getCabinetStoreById(@Param("id") String id);

    int updateByPrimaryKeySelective(CabinetStore record);

    int updateByPrimaryKey(CabinetStore record);

    /**
     * 库管通过user_id查询自己所负责区域格子柜的相关信息
     *
     * @param map
     * @return
     */
    List<CabinetStore> getCabinetStoreByUserId(Map map);

    /**
     * 查询bd旗下的所有店铺
     * @param phoneNo
     * @return
     */
    @Select("SELECT id,name FROM cabinet_store WHERE `status` = 0 AND create_status = 0 AND id IN (SELECT store_id FROM cabinet_bussiness_coorperation WHERE bd_phone = #{phone} AND status =  0)")
    List<Map<String,Object>> findBdShops(@Param("phone") String phoneNo);

    /**
     * bd数据统计汇总详情信息
     * @param phoneNo
     * @return
     */
    Map<String, Object> findBdDataDetail(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("phone") String phoneNo);

    /**
     * bd统计店铺详情
     * @param phoneNo
     * @param fullStartTime
     * @param fullEndTime
     * @return
     */
    List<BdDataStaticsticVo> findBdDataStatistics(@Param("storeId")String store_id,@Param("phone") String phoneNo,@Param("startTime") String fullStartTime,@Param("endTime") String fullEndTime,@Param("stock_num")String stock_num);

    /**
     * 单个店铺的详情
     * @param phoneNo
     * @param fullStartTime
     * @param fullEndTime
     * @return
     */
    BdDataStaticsticVo findBdDataStatisticOne(@Param("storeId") String cabinet_store_id,@Param("phone") String phoneNo,@Param("startTime") String fullStartTime, @Param("endTime") String fullEndTime);

    List<Orders> findBdStoreOrders(@Param("storeId") String cabinet_store_id,@Param("startTime") String fullStartTime,@Param("endTime") String fullEndTime);




    @Update("update cabinet_store set create_status = #{create_status} where id = #{id}")
   int updateCreateStatusById(@Param("create_status") String create_status,@Param("id") String id);
}