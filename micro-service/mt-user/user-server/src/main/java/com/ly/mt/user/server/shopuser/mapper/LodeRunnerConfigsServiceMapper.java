package com.ly.mt.user.server.shopuser.mapper;

import com.ly.mt.core.common.entity.shop.LodeRunnerConfigs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LodeRunnerConfigsServiceMapper {

    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(LodeRunnerConfigs record);

    int insertSelective(LodeRunnerConfigs record);

    LodeRunnerConfigs selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(LodeRunnerConfigs record);

    int updateByPrimaryKey(LodeRunnerConfigs record);
}