package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.dto.CabinetInfoDefaultsDto;
import com.ly.mt.center.data.cabinet.entity.CabinetInfo;
import com.ly.mt.center.data.cabinet.requestdto.SquareCabinatRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CabinetInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CabinetInfo record);

    int insertSelective(CabinetInfo record);

    CabinetInfo selectByPrimaryKey(String id);

    CabinetInfo getCabinetInfoByImei(CabinetInfo record);

    int updateByPrimaryKeySelective(CabinetInfo record);

    int updateByPrimaryKey(CabinetInfo record);

    /**
     * 根据手机号查询格子柜信息
     * @param phone
     * @return
     */
    @Select("select * from cabinet_info where creator_phone = #{phone} and type in (1,3)")
    List<CabinetInfo> findByUserBdPhone(@Param("phone") String phone);


    /**
     * bd格子柜列表查询 排序方式 上下架 时间
     * @param param {@link SquareCabinatRequestDto}
     * @return
     */
    List<CabinetInfoDefaultsDto> findByInfoDefaults(SquareCabinatRequestDto param);

    /**
     * 查询指定格子柜合作信息详情
     *
     * @param imei
     * @param userId
     * @param phone
     * @return
     */
    Map<String, Object> findCoopDefault(@Param("imei") String imei,@Param("userId") Long userId,@Param("phone") String phone);

    /**
     * 查询指定展柜合作信息
     * @param imei
     * @param userId
     * @param phone
     * @return
     */
    Map<String,Object> findShowcaseCoopDefault(@Param("imei") String imei,@Param("userId") Long userId,@Param("phone") String phone);

    /**
     * 分页查bd格子柜
     * @param phone
     * @return
     */
    @Select("SELECT * FROM cabinet_info where store_id in (SELECT store_id FROM cabinet_bussiness_coorperation WHERE bd_phone = #{phone}) and `type` in (1,3)")
    List<CabinetInfo> findInfoBasePage(@Param("phone") String phone);

    /**
     * 指定格子柜下架
     * @param imei
     * @param phone
     * @param userId
     * @return
     */
    @Update("UPDATE cabinet_info  SET `status` = 1 ,create_status = 2  WHERE imei = #{imei}")
    Integer downSquareCabinet(@Param("imei") String imei,@Param("phone") String phone,@Param("userId") Long userId);

    /**
     * 查询柜子所有的配货方案
     * @return
     */
    List<String> getCabinetProgrammeList();

    @Select("select * from cabinet_info where imei = #{imei}")
    CabinetInfo findByImei(@Param("imei") String imei);

    /**
     * 查询展柜分页
     * @param phone
     * @return
     */
    @Select("SELECT * FROM cabinet_info where store_id in (SELECT store_id FROM cabinet_bussiness_coorperation WHERE bd_phone = #{phone}) and `type` in (2)")
    List<CabinetInfo> findShowcaseInfoBasePage(@Param("phone") String phone);

    /**
     * 查询展柜详情信息
     * @param param
     * @return
     */
    List<CabinetInfoDefaultsDto> findShowcaseCabinetsDefaults(SquareCabinatRequestDto param);


    /**
     * 通过店铺查看店铺下面的所有柜子信息
     * @param store_id
     * @return
     */
    @Select("select * from cabinet_info where store_id = #{store_id}")
    List<CabinetInfo> getCabinetInfoByStoreId(@Param("store_id") String store_id);

    @Select("select * from cabinet_info where imei=#{imei}")
    CabinetInfo selectByImei(@Param("imei") String imei);

    /**
     * 格子柜上架
     * @param imei
     * @param phone
     * @param userId
     * @return
     */
    @Update("UPDATE cabinet_info  SET `status` = 0 ,create_status = 0  WHERE imei = #{imei}")
    int upSquareCabinet(@Param("imei") String imei,@Param("phone") String phone,@Param("userId") Long userId);

    /**
     * 查询店主旗下的展柜
     * @param phoneNo
     * @return
     */
    @Select("SELECT imei FROM cabinet_info WHERE  `status` = 0 AND  store_id IN(SELECT store_id FROM cabinet_bussiness_coorperation WHERE `status` = 0 AND owner_phone = #{phoneNo})")
    List<String> findsByStoreOwner(@Param("phoneNo") String phoneNo);
}