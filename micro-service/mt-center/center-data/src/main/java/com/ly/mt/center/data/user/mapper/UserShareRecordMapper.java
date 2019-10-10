package com.ly.mt.center.data.user.mapper;


import com.ly.mt.center.data.user.entity.UserShareRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserShareRecordMapper {
    int deleteByPrimaryKey(@Param("id")Long id);

    int insert(UserShareRecord record);

    int insertSelective(UserShareRecord record);

    UserShareRecord selectByPrimaryKey(@Param("id")Long id);

    int updateByPrimaryKeySelective(UserShareRecord record);

    int updateByPrimaryKey(UserShareRecord record);

    int addUserShareRecord(@Param("userId") Long userId,@Param("skuId") Long skuId);
}