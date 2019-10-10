package com.ly.mt.center.data.rotation.mapper;

import com.ly.mt.center.data.rotation.entity.RotationInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RotationInfoMapper {
    /**
     * @Description 保存RotationInfo
     * @Author taoye
     */
    void insertRotationInfo(RotationInfo rotationInfo);

    /**
     * @Description 删除RotationInfo
     * @Author taoye
     */
    void deleteRotationInfo(RotationInfo rotationInfo);

    /**
     * @Description 更新RotationInfo
     * @Author taoye
     */
    int updateRotationInfo(RotationInfo rotationInfo);

    /**
     * @Description 查询RotationInfo
     * @Author taoye
     */
    RotationInfo getRotationInfo(RotationInfo rotationInfo);

    /**
     * @Description 查询RotationInfo集合
     * @Author taoye
     */
    List<RotationInfo> listRotationInfo();
}