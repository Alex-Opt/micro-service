package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserWallets;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserWalletsMapper {
    /**
     * @Description 保存UserWallets
     * @Author taoye
     */
    void insertUserWallets(UserWallets userWallets);

    /**
     * @Description 删除UserWallets
     * @Author taoye
     */
    void deleteUserWallets(UserWallets userWallets);

    /**
     * @Description 更新UserWallets
     * @Author taoye
     */
    int updateUserWallets(UserWallets userWallets);

    /**
     * @Description 查询UserWallets
     * @Author taoye
     */
    UserWallets getUserWallets(UserWallets userWallets);
}