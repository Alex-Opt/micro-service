package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgDeviceCheckLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgDeviceCheckLogMapper {
    /**
     * @Description 保存GzgDeviceCheckLog
     * @Author taoye
     */
    void insertGzgDeviceCheckLog(GzgDeviceCheckLog gzgDeviceCheckLog);

    /**
     * @Description 删除GzgDeviceCheckLog
     * @Author taoye
     */
    void deleteGzgDeviceCheckLog(GzgDeviceCheckLog gzgDeviceCheckLog);

    /**
     * @Description 更新GzgDeviceCheckLog
     * @Author taoye
     */
    int updateGzgDeviceCheckLog(GzgDeviceCheckLog gzgDeviceCheckLog);

    /**
     * @Description 查询GzgDeviceCheckLog
     * @Author taoye
     */
    GzgDeviceCheckLog getGzgDeviceCheckLog(GzgDeviceCheckLog gzgDeviceCheckLog);
}