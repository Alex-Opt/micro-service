package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgInfoMapper {
    /**
     * @Description 插入GzgInfo
     * @Author taoye
     */
    void insertGzgInfo(GzgInfo gzgInfo);

    /**
     * @Description 根据id删除GzgInfo
     * @Author taoye
     */
    void deleteGzgInfoById(GzgInfo gzgInfo);

    /**
     * @Description 根据id更新GzgInfo
     * @Author taoye
     */
    int updateGzgInfoById(GzgInfo gzgInfo);

    /**
     * @Description 根据条件查询GzgInfo
     * @Author taoye
     */
    GzgInfo getGzgInfoByCode(GzgInfo gzgInfo);

    /**
     * @Description 根据id查询GzgInfo
     * @Author taoye
     */
    GzgInfo getGzgInfoById(GzgInfo gzgInfo);
}