package com.ly.mt.center.data.vote.mapper;

import com.ly.mt.center.data.vote.entity.VoteCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface VoteCityMapper {
    /**
     * @Description 保存VoteCity
     * @Author taoye
     */
    void insertVoteCity(VoteCity voteCity);

    /**
     * @Description 删除VoteCity
     * @Author taoye
     */
    void deleteVoteCity(VoteCity voteCity);

    /**
     * @Description 更新VoteCity
     * @Author taoye
     */
    int updateVoteCity(VoteCity voteCity);

    /**
     * @Description 查询VoteCity
     * @Author taoye
     */
    VoteCity getVoteCity(VoteCity voteCity);


    @Select("select * from vote_city where user_id = #{userId} and  area_id = #{areaId}")
    VoteCity findByUserIdAndAreaId(@Param("userId") Long userId, @Param("areaId") Long areaId);

    /**
     * 查询区域下的投票数
     * @return
     */
    List<Map<String, Object>> findCountArea(@Param("areaId")Long areaId);

}