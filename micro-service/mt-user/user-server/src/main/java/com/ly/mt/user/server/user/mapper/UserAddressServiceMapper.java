package com.ly.mt.user.server.user.mapper;

import com.ly.mt.core.common.entity.user.AddressCodeName;
import com.ly.mt.core.common.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhanglifeng
 * @description 用户中心-收货地址管理持久层
 * @date 2019-05-20
 */
@Mapper
public interface UserAddressServiceMapper {
    /**
     * 根据主键删除一条记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增一条收货地址
     *
     * @param record
     * @return
     */
    int insert(UserAddress record);

    /**
     * 活动页面插入一条收货地址
     * @param record
     * @return
     */
    int insertActivtyPromotion(UserAddress record);

    /**
     * 根据id查询一条收货地址
     *
     * @param id
     * @return
     */
    UserAddress selectByPrimaryKey(Long id);

    /**
     * 根据主键id更新一条地址记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserAddress record);

    /**
     * 根据主键设置为默认地址
     * @param record
     * @return
     */
    int updateDefaultAddress(UserAddress record);


    /**
     * 查询用户下所有收货地址列表
     *
     * @param userId
     * @return
     */
    List<UserAddress> listByUserId(@Param("userId") Long userId);

    /**
     * 获取用户的默认地址
     *
     * @param userId
     * @return
     */
    UserAddress getDefaultAddressByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id更新默认地址
     *
     * @param record
     * @return
     */
    int updateDefaultAddressByUserId(UserAddress record);

    /**
     * 根据id逻辑删除地址状态
     * @param userAddress
     * @return
     */
    int updateValidStatusById(UserAddress userAddress);

}
