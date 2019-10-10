package com.ly.mt.center.data.basic.mapper;

import com.ly.mt.center.data.basic.entity.BasicRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BasicRoleMapper {

    int insert(BasicRole record);

    /**
     * 根据用户id查询角色类型
     * @param user_id
     * @return
     */
    String selectRoleTypeByUserId(@Param("user_id") String user_id);

    int updateByPrimaryKey(BasicRole record);

    @Select("select id from basic_role where role_type=#{roleType}")
    long selectByRoleType(@Param("roleType") String roleType);
}