package com.ly.mt.center.data.user.mapper;


import com.ly.mt.center.data.user.entity.UserCertification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserCertificationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCertification record);

    int insertSelective(UserCertification record);

    UserCertification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCertification record);

    int updateByPrimaryKey(UserCertification record);

    UserCertification selectByUserId(@Param("user_id") String user_id);

    UserCertification getUserCertificationByIdCardMobileName(UserCertification record);

    /**
     * 查询身份证是否已经使用过
     * @param cardId
     * @return
     */
    UserCertification getUserCertificationByIdCard(String cardId);

}