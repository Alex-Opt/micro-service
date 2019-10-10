package com.ly.mt.purchase.server.member.mapper;

import com.ly.mt.core.common.entity.purchase.MemberInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * B端会员信息
 *
 * @author xiaobei-ihmhny
 * @date 2019-06-16 21:47:47
 */
@Mapper
public interface MemberInfoMapper {

    /**
     * 根据用户user_id查询出当前用户的会员信息
     * @param userId
     * @return
     */
    MemberInfoVO selectInfoByUserId(@Param("userId") Long userId);
}
