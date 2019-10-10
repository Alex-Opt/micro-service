package com.ly.mt.center.data.basic.mapper;

import com.ly.mt.center.data.basic.entity.BasicUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BasicUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicUserRole record);

    BasicUserRole selectByPrimaryKey(Long id);

    List<BasicUserRole> selectAll();//

    int updateByPrimaryKey(BasicUserRole record);

    @Select("select * from basic_user_role where user_id=#{user_id}")
    BasicUserRole selectByUserId(@Param("user_id") Long user_id);
}