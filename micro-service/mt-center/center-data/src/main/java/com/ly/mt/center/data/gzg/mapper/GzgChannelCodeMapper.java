package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgChannelCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgChannelCodeMapper {
    /**
     * @Description 保存GzgChannelCode
     * @Author taoye
     */
    void insertGzgChannelCode(GzgChannelCode gzgChannelCode);

    /**
     * @Description 删除GzgChannelCode
     * @Author taoye
     */
    void deleteGzgChannelCode(GzgChannelCode gzgChannelCode);

    /**
     * @Description 更新GzgChannelCode
     * @Author taoye
     */
    int updateGzgChannelCode(GzgChannelCode gzgChannelCode);

    /**
     * @Description 查询GzgChannelCode
     * @Author taoye
     */
    GzgChannelCode getGzgChannelCode(GzgChannelCode gzgChannelCode);
}