package com.ly.mt.user.server.shopuser.mapper;

import com.ly.mt.core.common.entity.shop.*;
import com.ly.mt.core.common.entity.user.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ShopInfoServiceMapper {


    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(ShopInfo record);

    ShopInfo selectByPrimaryKey(@Param("id") Long id);

    int updateBasicInfoById(ShopInfoVo record);

    /**
     * @Description 根据用户名/手机号获取用户信息
     * @Author taoye
     */
    User getUser(UserLoginVo user);

    /**
     * 根据手机号查询用户是否存在
     * @param mobile
     * @return
     */
    ShopInfo selectByMobile(@Param("mobile") String mobile);

    /**
     * B端手机号为注册用户向user表中插入一条用户信息
     * @param record
     * @return
     */
    int bRegisterUser(User record);

    /**
     * 向捞金人树插入一条记录
     * @param record
     * @return
     */
    int insertLodeRunnerTree(LodeRunnerTrees record);

    /**
     * 根据用户Id寻找淘金人等级
     * @param userId
     * @return
     */
    LodeRunnerTrees selectLoadRunnerTreeByUserId(Long  userId);


    List<LodeRunnerConfigs> selectLodeRunnerConFigs();

    /**
     *根据邀请码查询淘金者专属邀请码表
     */
    LoderRunnerCodes selectLodeRunnerCodesByCode(LoderRunnerCodes record);


    /**
     * 将邀请数量为空的店铺设置为0
     * @return
     */
    int beZeroNumsIfNull(LoderRunnerCodes record);

    /**
     * 增加成功邀请数量
     * @param record
     * @return
     */
    int addInviteNum(LoderRunnerCodes record);


    /**
     * 设置密码
     * @return
     */
    int setPassword(@Param("userId") String userId, @Param("password") String password);

    /**
     * 插入一条b端收货地址
     */
    int inserNewShopAddress(ShopAddress record);


    /**
     * 更新b端发货地址
     */
    int updateShopAddressById(ShopAddress record);


    /**
     *根据shopId获取userId
     */

    ShopInfo selecShopInfoByShopId(@Param("shopId")Long shoId);

    /**
     * 根据电话号码查询B端店铺
     * @param
     * @return
     */
    ShopInfo getShopUser(ShopInfo record);

    /**
     * 查询店铺地址
     * @param record
     * @return
     */
    ShopAddress getShopAddress(ShopAddress record);

    /**
     * 根据shopId获取营业执照
     * @param shopId
     * @return
     */
    ShopLicenses getShopLicensesByShopId(@Param("shopId") Long shopId);

    /**
     * 更新用户营业执照
     * @param record
     * @return
     */
    int updateLicensesByShopId(ShopLicenses record);

    /**
     * 插入新的营业执照
     * @param record
     * @record
     */
    int InsertNewLicenses(ShopLicenses record);

    /**
     * 更新身份信息
     * @param record
     * @return
     */
    int updateIdCardByShopId(IdCardVo record);


    /**
     * B端店铺审核通过
     * @param shopId
     * @return
     */
    int updateShopStatus(@Param("shopId") Long shopId);


    /**
     * 绑定微信
     * @param record
     * @return
     */
    int bindWechat(User record);

    /**
     * 新增用户反馈
     * @param userFeedback
     * @return
     */
    int addUserFeedback(UserFeedback userFeedback);

    /**
     * 新增用户反馈图片
     * @param userFeedbackImages
     * @return
     */
    int addUserFeedbackImages(UserFeedbackImages userFeedbackImages);

}