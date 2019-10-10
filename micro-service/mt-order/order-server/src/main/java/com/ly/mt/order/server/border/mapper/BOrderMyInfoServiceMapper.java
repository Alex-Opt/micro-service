package com.ly.mt.order.server.border.mapper;

import com.ly.mt.core.base.entity.goods.GoodsSpuInfo;
import com.ly.mt.core.base.entity.purchase.ShopPurchasesDiscount;
import com.ly.mt.core.base.entity.shop.*;
import com.ly.mt.core.base.entity.user.User;
import com.ly.mt.core.base.entity.user.UserFeedback;
import com.ly.mt.core.base.entity.user.UserFeedbackImages;
import com.ly.mt.core.base.entity.user.UserUpdateLoginNameLogs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我的模块-持久层
 * @author zhanglifeng
 * @date 2019-06-14
 */
@Mapper
public interface BOrderMyInfoServiceMapper {

    /**
     * 查询用户店铺的订单列表
     * @param shopId
     * @param userId
     * @param id
     * @return
     */
    List<ShopPurchases> getShopPurchaseListByPrimaryKey(@Param("shopId")Long shopId, @Param("userId")Long userId,@Param("id")Long id);

    /**
     * 查询店铺的库存信息
     * @param shopId
     * @return
     */
    List<ShopStocks> selectShopStocksByShopId(Long shopId);


    /**
     * 查询一个进货单的进货详情
     * @param shopPurchasesId
     * @return
     */
    List<ShopPurchasesItems> selectShopPurchaseItemListByPurchaseId(@Param("shopPurchasesId")Long shopPurchasesId);


    List<ShopPurchasesItemVo> selectShopPurchaseItemListAndPictureByPurchaseId(@Param("shopPurchasesId") Long shopPurchasesId);

    /**
     * 查询一个进货单使用的优惠信息
     * @param shopPurchasesId
     * @param userId
     * @param shopId
     * @return
     */
    List<ShopPurchasesDiscount> getShopPurchaseDiscountByPurchaseId(@Param("shopPurchasesId")Long shopPurchasesId,@Param("userId")Long userId, @Param("shopId")Long shopId);

    /**
     * 查询用户的优惠券信息
     * @param userId
     * @param shopId
     * @return
     */
    List<ShopCoupon> getShopCouponByUserIdAndShopId(@Param("userId")Long userId, @Param("shopId")Long shopId);



    /**
     * 查询店铺默认地址
     * @param shopId
     * @return
     */
    ShopAddress getShopDefaultAddressByShopId(Long shopId);


    /**
     * 根据主键id查询一条店铺地址信息
     * @param id
     * @return
     */
    ShopAddress getShopAddressById(Long id);

    /**
     * 更新店铺地址信息
     * @param shopAddress
     * @return
     */
    int updateShopAddressByPrimaryKey(ShopAddress shopAddress);

    /**
     * 根据用户id查询历史修改记录
     * @param userId
     * @return
     */
    UserUpdateLoginNameLogs getUpdateTimesByUserId(Long userId);

    /**
     * 修改用户的用户
     * @param user
     * @return
     */
    int updateUserInfoByPrimaryKey(User user);

    /**
     * 新增一条修改用户名操作的日志
     * @param userUpdateLoginNameLogs
     * @return
     */
    int insertUpdateLogs(UserUpdateLoginNameLogs userUpdateLoginNameLogs);

    /**
     * 查询登录名是否存在
     * @param loginName
     * @return
     */
    String queryLoginNameExists(String loginName);

    /**
     * 根据id获取店铺信息
     * @param id
     * @return
     */
    ShopInfo getShopInfoByPrimaryKey(Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User getUserByByPrimaryKey(Long id);

    /**
     * 根据userID,shopId查询店铺的地址信息
     * @param userId
     * @param shopId
     * @return
     */
    List<ShopAddress> getShopAddressByUserIdAndShopId(@Param("userId")Long userId, @Param("shopId")Long shopId);

    /**
     * 新增店铺地址
     * @param shopAddress
     * @return
     */
    int insertShopAddress(ShopAddress shopAddress);

    /**
     * 更新地址默认状态
     * @param userId
     * @param shopId
     * @param isDefault
     * @param id
     * @return
     */
    int updateShopNotDefaultAddress(@Param("userId")Long userId, @Param("shopId")Long shopId,@Param("isDefault")Integer isDefault,@Param("id")Long id);

    /**
     * 查询手机号是否已经存在
     * @param mobile
     * @return
     */
    String validateMobileIsBind(String mobile);

    /**
     * 修改手机号字段值
     * @param id
     * @param mobile
     * @return
     */
    int modifyShopMobile(@Param("id")Long id,@Param("mobile")String mobile);



    /**
     * 查询手机号是否已绑定过其他账号
     */
    int  countUserNumByMobile(@Param("mobile") String mobile);


    ShopStocks selectSpuInfoBySpuId(@Param("spuId") Long spuId);


    List<ShopStocks> selectSpuPictureBySouId(@Param("spuId") Long spuId);
}
