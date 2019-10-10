package com.ly.mt.center.data.profits.mapper;

import com.ly.mt.center.data.profits.entity.ProfitsUserDetails;
import com.ly.mt.center.data.profits.entity.ProfitsUserParamVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfitsUserProfitsServiceMapper {
    /**
    * @Description: 查询用户赚钱的详情
    * @Author:         zhuyh
    */
    ProfitsUserDetails selectDetailsByUId(ProfitsUserParamVo paramVo);
}
