package com.ly.mt.user.server.user.mapper;

import com.ly.mt.core.common.entity.basic.BasicFileVo;
import com.ly.mt.core.common.entity.shop.ShopInfo;
import com.ly.mt.core.common.entity.user.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoServiceMapper {
    /**
     * @Description 身份认证
     * @Author taoye
     */
    void authentication(UserInfoVo user);

    /**
     * @Description 头像修改
     * @Author taoye
     */
    void updateUserAvatarPic(BasicFileVo file);

    /**
     * @Description 绑定手机
     * @Author taoye
     */
    void updateUserMobile(UserInfoVo user);

    /**
     * @Description 根据用户id获取用户信息
     * @Author taoye
     */
    UserInfoVo getUserById(UserInfoVo user);

    /**
     * @Description 根据用户手机号获取用户信息
     * @Author taoye
     */
    List<UserInfoVo> getUserByMobile(UserInfoVo user);

    /**
     * @Description 修改信息
     * @Author taoye
     */
    void updateUserInfo(UserInfoVo user);

    /**
     * 更新clientId
     * @param userId
     * @param clientId
     */
    int updateClientId(@Param("userId") Long userId,@Param("clientId")String clientId);

    /**
     * @Description 修改密码
     * @Author taoye
     */
    void updatePassword(UserInfoVo userInfo);

    /**
     * @Description 补全用户信息
     * @Author taoye
     */
    void perfectInfo(UserInfoVo user);

    /**
     * @Description 根据loginName查询用户数据
     * @Author
     */
    List<UserInfoVo> queryUserByLoginName(UserInfoVo userInfo);

    /**
     * @Description 修改用户名
     * @Author
     */
    void updateLoginName(UserInfoVo userInfo);

    /**
     * 根据mId查询区域名称
     * @param mId
     * @return
     */
    String queryAreaByMid(String mId);

    /**
     * 根据电话号码查询B端店铺
     * @param record
     * @return
     */
    ShopInfo getShopUser(ShopInfo record);
}