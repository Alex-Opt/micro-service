package com.ly.mt.order.server.activity.mapper;

import com.ly.mt.core.base.entity.user.UserPointGrade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPointGradeServiceMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPointGrade record);

    int insertSelective(UserPointGrade record);

    UserPointGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPointGrade record);

    int updateByPrimaryKey(UserPointGrade record);

    UserPointGrade selectGradeIdByUsrId(String userId);

}