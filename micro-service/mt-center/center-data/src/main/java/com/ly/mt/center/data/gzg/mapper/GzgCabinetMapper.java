package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgCabinet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GzgCabinetMapper {
    /**
     * @Description 保存GzgCabinet
     * @Author taoye
     */
    void insertGzgCabinet(GzgCabinet gzgCabinet);

    /**
     * @Description 删除GzgCabinet
     * @Author taoye
     */
    void deleteGzgCabinet(GzgCabinet gzgCabinet);

    /**
     * @Description 更新GzgCabinet
     * @Author taoye
     */
    int updateGzgCabinet(GzgCabinet gzgCabinet);

    /**
     * @Description 查询GzgCabinet
     * @Author taoye
     */
    GzgCabinet getGzgCabinet(GzgCabinet gzgCabinet);

    /**
     * 通过格子柜code值查新格子柜信息并按照降序排序
     * @param gzgCabinet
     * @return
     */
    List<GzgCabinet> getGzgCabinetBySellNo(GzgCabinet gzgCabinet);
}