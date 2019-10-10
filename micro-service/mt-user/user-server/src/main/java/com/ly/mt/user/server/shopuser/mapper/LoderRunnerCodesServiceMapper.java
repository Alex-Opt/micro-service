package com.ly.mt.user.server.shopuser.mapper;


import com.ly.mt.core.common.entity.shop.LoderRunnerCodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoderRunnerCodesServiceMapper {


    int deleteByPrimaryKey(@Param("userId") Long userId);

    int insert(LoderRunnerCodes record);

    int insertSelective(LoderRunnerCodes record);

    LoderRunnerCodes selectByPrimaryKey(@Param("userId") Long userId);

    int updateByPrimaryKeySelective(LoderRunnerCodes record);

    int updateByPrimaryKey(LoderRunnerCodes record);
}