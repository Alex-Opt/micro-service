package com.ly.mt.gzg.b.server.gzgb.mapper;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.entity.gzg.GzgBUserRelation;
import com.ly.mt.core.common.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GzgBUserRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzgBUserRelation record);

    int insertSelective(GzgBUserRelation record);

    GzgBUserRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzgBUserRelation record);

    int updateByPrimaryKey(GzgBUserRelation record);

    List<GzgBUserRelation> selectByUserId(@Param("userId") long userId);

    int delByGzglId(@Param("gzgId") Long gzgId);

    int batchInsert(@Param("list") List<GzgBUserRelation> list);

    List<User> findUserByGzgIdAndLikePhone(@Param("gzgId") long gzgId,@Param("mobile") String mobile);

    int delByGzgIdAndUserId(JSONObject jsonObject);

    List<User> findGzgUsers(@Param("gzgId") Long gzgId);
}