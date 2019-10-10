package com.ly.mt.center.data.platform.mapper;

import com.ly.mt.center.data.platform.entity.PlatformLogistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformLogisticsMapper {
    /**
     * @Description 保存PlatformLogistics
     * @Author taoye
     */
    void insertPlatformLogistics(PlatformLogistics platformLogistics);

    /**
     * @Description 删除PlatformLogistics
     * @Author taoye
     */
    void deletePlatformLogistics(PlatformLogistics platformLogistics);

    /**
     * @Description 更新PlatformLogistics
     * @Author taoye
     */
    int updatePlatformLogistics(PlatformLogistics platformLogistics);

    /**
     * @Description 查询PlatformLogistics
     * @Author taoye
     */
    PlatformLogistics getPlatformLogistics(PlatformLogistics platformLogistics);
}