package com.ly.mt.core.data.basic.mapper;

import com.ly.mt.core.data.basic.entity.BasicArea;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * BasicArea操作接口
 *
 * @author taoye
 */
@Mapper
public interface BasicAreaMapper {
    /**
     * 查询BasicArea
     *
     * @param basicArea 查询条件
     * @return BasicArea
     * @author taoye
     */
    BasicArea getBasicArea(BasicArea basicArea);

    /**
     * 查询List<BasicArea>
     *
     * @param basicArea 查询条件
     * @return List<BasicArea>
     * @author taoye
     */
    List<BasicArea> listBasicArea(BasicArea basicArea);

}