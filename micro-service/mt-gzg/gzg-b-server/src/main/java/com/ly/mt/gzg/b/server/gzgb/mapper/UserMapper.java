package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.ly.mt.core.common.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByMobile(@Param("phone") String phone);
    @Update("update user set client_id=#{clientId} where id=#{id}")
    int updateClientIdById(User user);
}