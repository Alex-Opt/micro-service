package com.ly.mt.core.data.cabinet.mapper;

import com.ly.mt.core.data.cabinet.entity.CabinetCategroy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CabinetCategroy操作接口
 *
 * @author taoye
 */
@Mapper
public interface CabinetCategroyMapper {
    /**
     * 新增CabinetCategroy
     *
     * @param cabinetCategroy 新增数据
     * @author taoye
     */
    void insertCabinetCategroy(CabinetCategroy cabinetCategroy);

    /**
     * 更新CabinetCategroy
     *
     * @param cabinetCategroy 更新条件和数据
     * @return 更新条数
     * @author taoye
     */
    int updateCabinetCategroy(CabinetCategroy cabinetCategroy);

    /**
     * 查询CabinetCategroy
     *
     * @param cabinetCategroy 查询条件
     * @return CabinetCategroy
     * @author taoye
     */
    CabinetCategroy getCabinetCategroy(CabinetCategroy cabinetCategroy);

    /**
     * 查询List<CabinetCategroy>
     *
     * @param cabinetCategroy 查询条件
     * @return List<CabinetCategroy>
     * @author taoye
     */
    List<CabinetCategroy> listCabinetCategroy(CabinetCategroy cabinetCategroy);
}