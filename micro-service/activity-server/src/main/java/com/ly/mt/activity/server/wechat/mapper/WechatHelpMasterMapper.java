package com.ly.mt.activity.server.wechat.mapper;

import com.ly.mt.core.common.entity.hd.WechatHelpMaster;
import com.ly.mt.core.common.entity.hd.dto.WechatHelpMasterMsgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface WechatHelpMasterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatHelpMaster record);

    int insertSelective(WechatHelpMaster record);

    int updateByPrimaryKeySelective(WechatHelpMaster record);

    int updateByPrimaryKey(WechatHelpMaster record);

    @Select("select * from wechat_help_master where id=#{id}")
    WechatHelpMaster selectByPrimaryKey(Long id);

    /**
     * 根据活动id + 微信用户主表id查询
     */
    @Select("SELECT * FROM wechat_help_master WHERE activity_id = #{activityId} and wechat_id = #{wechatId}")
    List<WechatHelpMaster> findByActivityIdAndWechatId(@Param("activityId") Long activityId, @Param("wechatId") Long wechatId);

    /**
     * 查询sub助力账户
     */
    @Select("SELECT * FROM wechat_help_master WHERE activity_id = #{activityId} and wechat_master_id = #{wechatMasterId}")
    List<WechatHelpMaster> findByActivityIdAndWechatMasterId(@Param("activityId") Long activityId,@Param("wechatMasterId") Long wechatMasterId);

    /**
     * 查询带微信昵称与头像的详细信息
     * @param activityId
     * @param wechatId
     * @return
     */
    @Select("SELECT m.* ,w.wechat_heade_Img_url as wechat_heade_Img_url ,w.wechat_nickname AS wechat_nickname FROM wechat_help_master m LEFT JOIN wechat_user w ON m.wechat_id = w.id  WHERE activity_id = #{activityId} and wechat_id = #{wechatId}")
    List<WechatHelpMasterMsgDto> findByActivityIdAndWechatIdToMsg(@Param("activityId") Long activityId,@Param("wechatId") Long wechatId);


    /**
     * 根据活动id与openid更新数据
     * @param master
     * @return
     */
    int updateByActivityAndOpenId(WechatHelpMaster master);

    int updateTaskById(WechatHelpMaster master);

    /**
     * 更改手机号
     * @param phone
     * @param activityId
     * @param wechatId
     * @return
     */
    @Update("UPDATE wechat_help_master SET phone = #{phone} WHERE activity_id = #{activityId} and wechat_id = #{wechatId}")
    int updateByActivityIdAndWechatId(@Param("phone") String phone,@Param("activityId") Long activityId,@Param("wechatId") Long wechatId);

    @Update("UPDATE wechat_help_master SET wechat_master_id = #{masterId} where id = #{id}")
    Integer updateWechatMasterId(@Param("id") Long id,@Param("masterId") Long masterId);
    @Select("select * from wechat_help_master where wechat_id=#{wechatId}")
    WechatHelpMaster selectByWeChatId(@Param("wechatId") long wechatId);

    @Update("UPDATE wechat_help_master SET task_status = #{status} where id = #{id}")
    int updateStatus(@Param("id") long weChatHelpId,@Param("status") String s);
}