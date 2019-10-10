package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgReplenishRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgReplenishRecordMapper {
    /**
     * @Description 保存GzgReplenishRecord
     * @Author taoye
     */
    void insertGzgReplenishRecord(GzgReplenishRecord gzgReplenishRecord);

    /**
     * @Description 删除GzgReplenishRecord
     * @Author taoye
     */
    void deleteGzgReplenishRecord(GzgReplenishRecord gzgReplenishRecord);

    /**
     * @Description 更新GzgReplenishRecord
     * @Author taoye
     */
    int updateGzgReplenishRecord(GzgReplenishRecord gzgReplenishRecord);

    /**
     * @Description 查询GzgReplenishRecord
     * @Author taoye
     */
    GzgReplenishRecord getGzgReplenishRecord(GzgReplenishRecord gzgReplenishRecord);
}