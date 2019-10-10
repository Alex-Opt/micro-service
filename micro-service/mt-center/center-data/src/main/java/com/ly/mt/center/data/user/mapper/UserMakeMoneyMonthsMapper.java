package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserMakeMoneyMonths;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMakeMoneyMonthsMapper {
    /**
     * @Description 保存UserMakeMoneyMonths
     * @Author taoye
     */
    void insertUserMakeMoneyMonths(UserMakeMoneyMonths userMakeMoneyMonths);

    /**
     * @Description 删除UserMakeMoneyMonths
     * @Author taoye
     */
    void deleteUserMakeMoneyMonths(UserMakeMoneyMonths userMakeMoneyMonths);

    /**
     * @Description 更新UserMakeMoneyMonths
     * @Author taoye
     */
    int updateUserMakeMoneyMonths(UserMakeMoneyMonths userMakeMoneyMonths);

    /**
     * @Description 查询UserMakeMoneyMonths
     * @Author taoye
     */
    UserMakeMoneyMonths getUserMakeMoneyMonths(UserMakeMoneyMonths userMakeMoneyMonths);
}