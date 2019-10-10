package com.ly.mt.center.data.basic.mapper;

import com.ly.mt.center.data.basic.entity.BasicArea;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasicAreaMapper {
    /**
     * @Description 保存BasicArea
     * @Author taoye
     */
    void insertBasicArea(BasicArea basicArea);

    /**
     * @Description 删除BasicArea
     * @Author taoye
     */
    void deleteBasicArea(BasicArea basicArea);

    /**
     * @Description 更新BasicArea
     * @Author taoye
     */
    int updateBasicArea(BasicArea basicArea);

    /**
     * @Description 查询BasicArea
     * @Author taoye
     */
    BasicArea getBasicArea(BasicArea basicArea);
}