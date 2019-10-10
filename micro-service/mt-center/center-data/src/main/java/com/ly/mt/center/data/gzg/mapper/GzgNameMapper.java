package com.ly.mt.center.data.gzg.mapper;


import com.ly.mt.center.data.gzg.entity.GzgName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GzgNameMapper {

    int deleteByPrimaryKey(Long id);

    int insert(GzgName record);

    List<GzgName> getGzgNameBatch(@Param("start")int start,@Param("size") int size);

    int insertSelective(GzgName record);

    GzgName selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgName record);

    int updateByPrimaryKey(GzgName record);
}