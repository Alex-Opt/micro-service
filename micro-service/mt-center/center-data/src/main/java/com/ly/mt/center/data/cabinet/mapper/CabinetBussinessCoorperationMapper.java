package com.ly.mt.center.data.cabinet.mapper;
import	java.lang.reflect.Parameter;


import com.ly.mt.center.data.cabinet.entity.CabinetBussinessCoorperation;
import com.ly.mt.center.data.cabinet.entity.CabinetStoreProperty;
import com.ly.mt.center.data.cabinet.enums.CabinetStoreCreateStatusEnum;
import com.ly.mt.center.data.cabinet.response.BdStoreMsgRespVO;
import com.ly.mt.center.data.cabinet.response.CreateCooperationResVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CabinetBussinessCoorperationMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetBussinessCoorperation record);

    int insertSelective(CabinetBussinessCoorperation record);

    CabinetBussinessCoorperation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CabinetBussinessCoorperation record);

    int updateByPrimaryKey(CabinetBussinessCoorperation record);

    CabinetBussinessCoorperation selectByStoreId(@Param("storeId") String storeId);

    CreateCooperationResVO getCoorperationInfo(@Param("cabinetStoreId") String cabinetStoreId);

    List<BdStoreMsgRespVO> selectPageByStatus(@Param("createStatus") String createStatus, @Param("bdPhone") String bdPhone,@Param("type") int type,@Param("condition") String condition);

    List<BdStoreMsgRespVO> search(@Param("condition")String condition,@Param("bd_phone") String bd_phone,@Param("type") int type);

    CabinetBussinessCoorperation selectByStoreIdAndStatus(CabinetBussinessCoorperation record);
}