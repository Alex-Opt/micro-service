package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.WechatUser;
import com.ly.mt.core.common.entity.hd.vo.WechatSimpMsgVo;
import com.ly.mt.core.common.entity.hd.vo.WechatUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WechatUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);

    @Select("select * from wechat_user where id = #{id}")
    WechatUserVo findById(@Param("id") Long id);

    @Select("select * from wechat_user where open_id = #{openId}")
    WechatUserVo findByOpenId(@Param("openId") String openId);

    @Select("SELECT wechat_nickname,wechat_heade_Img_url FROM wechat_user WHERE id IN(" +
            "SELECT wechat_id FROM wechat_help_master WHERE activity_id = #{activityId} and wechat_master_id = #{wechatMasterId})")
    List<WechatSimpMsgVo> findByActivityIdAndWechatMasterId(@Param("activityId") Long activityId, @Param("wechatMasterId") Long wechatMasterId);

    @Select("select count(*) from wechat_user")
    Long findUserCount();
}