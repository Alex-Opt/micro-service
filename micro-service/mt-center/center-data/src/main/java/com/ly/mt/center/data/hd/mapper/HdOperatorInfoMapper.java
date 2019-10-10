package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdOperatorInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdOperatorInfoMapper {
    /**
     * @Description 保存HdOperatorInfo
     * @Author taoye
     */
    void insertHdOperatorInfo(HdOperatorInfo hdOperatorInfo);

    /**
     * @Description 删除HdOperatorInfo
     * @Author taoye
     */
    void deleteHdOperatorInfo(HdOperatorInfo hdOperatorInfo);

    /**
     * @Description 更新HdOperatorInfo
     * @Author taoye
     */
    int updateHdOperatorInfo(HdOperatorInfo hdOperatorInfo);

    /**
     * @Description 查询HdOperatorInfo
     * @Author taoye
     */
    HdOperatorInfo getHdOperatorInfo(HdOperatorInfo hdOperatorInfo);
}