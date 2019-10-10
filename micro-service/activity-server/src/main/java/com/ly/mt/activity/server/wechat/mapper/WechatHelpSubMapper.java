package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.WechatHelpSub;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface WechatHelpSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatHelpSub record);

    int insertSelective(WechatHelpSub record);

    int updateByPrimaryKeySelective(WechatHelpSub record);

    int updateByPrimaryKeyWithBLOBs(WechatHelpSub record);

    int updateByPrimaryKey(WechatHelpSub record);

    /**
     * 根据助力主用户查询用户子任务完成进度
     * @param masterId
     * @return
     */
    @Select("SELECT * FROM wechat_help_sub WHERE help_master_id = #{masterId}")
    List<WechatHelpSub> findByMasterId(@Param("masterId") Long masterId);
}