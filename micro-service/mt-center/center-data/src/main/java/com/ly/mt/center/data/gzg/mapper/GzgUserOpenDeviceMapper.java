package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgBannerPicture;
import com.ly.mt.center.data.gzg.entity.GzgUserOpenDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface GzgUserOpenDeviceMapper {
    /**
     * @Description 根据phone查询 GzgUserOpenDevice
     * @Author
     */
    @Select("select * from gzg_user_open_device where phone = #{phone}")
    GzgUserOpenDevice getGzgUserOpenDeviceByPhone(@Param("phone") String  phone);

}