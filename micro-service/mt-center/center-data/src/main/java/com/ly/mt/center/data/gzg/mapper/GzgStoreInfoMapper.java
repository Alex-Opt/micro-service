package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgStoreInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgStoreInfoMapper {
    /**
     * @Description 保存GzgStoreInfo
     * @Author taoye
     */
    void insertGzgStoreInfo(GzgStoreInfo gzgStoreInfo);

    /**
     * @Description 删除GzgStoreInfo
     * @Author taoye
     */
    void deleteGzgStoreInfo(GzgStoreInfo gzgStoreInfo);

    /**
     * @Description 更新GzgStoreInfo
     * @Author taoye
     */
    int updateGzgStoreInfo(GzgStoreInfo gzgStoreInfo);

    /**
     * @Description 查询GzgStoreInfo
     * @Author taoye
     */
    GzgStoreInfo getGzgStoreInfo(GzgStoreInfo gzgStoreInfo);
}