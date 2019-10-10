package com.ly.mt.user.server.shopuser.mapper;

import com.ly.mt.core.common.entity.shop.LodeRunnerTrees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LodeRunnerTreesServiceMapper {
    int deleteByPrimaryKey(@Param("id") Long id);


    int insert(LodeRunnerTrees record);

    int insertSelective(LodeRunnerTrees record);

    LodeRunnerTrees selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(LodeRunnerTrees record);

    int updateByPrimaryKey(LodeRunnerTrees record);
}