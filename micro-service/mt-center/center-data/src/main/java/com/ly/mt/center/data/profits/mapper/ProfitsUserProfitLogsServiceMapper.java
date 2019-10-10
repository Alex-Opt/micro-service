package com.ly.mt.center.data.profits.mapper;

import com.ly.mt.center.data.profits.entity.ProfitsUserProfitsLogsParam;
import com.ly.mt.center.data.profits.entity.ProfitsUserProfitsLogsVO;
import com.ly.mt.center.data.profits.entity.ProfitsUserRankVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfitsUserProfitLogsServiceMapper {

    /**
    * @Description: 查询好友下单后赚钱的金额
    * @Author:         zhuyh
    */
    List<ProfitsUserRankVo> getFriendsOrderRank(ProfitsUserRankVo vo);

    /**
    * @Description:    查询人员的日志
    * @Author:         zhuyh
    * @CreateDate:     2019/7/5 21:43
    */
    List<ProfitsUserProfitsLogsVO> getLogs(ProfitsUserProfitsLogsParam vo);
}
