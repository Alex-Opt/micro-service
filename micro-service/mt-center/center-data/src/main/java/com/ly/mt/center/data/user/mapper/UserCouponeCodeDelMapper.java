package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserCouponeCodeDel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCouponeCodeDelMapper {
    /**
     * @Description 保存UserCouponeCodeDel
     * @Author taoye
     */
    void insertUserCouponeCodeDel(UserCouponeCodeDel userCouponeCodeDel);

    /**
     * @Description 删除UserCouponeCodeDel
     * @Author taoye
     */
    void deleteUserCouponeCodeDel(UserCouponeCodeDel userCouponeCodeDel);

    /**
     * @Description 更新UserCouponeCodeDel
     * @Author taoye
     */
    int updateUserCouponeCodeDel(UserCouponeCodeDel userCouponeCodeDel);

    /**
     * @Description 查询UserCouponeCodeDel
     * @Author taoye
     */
    UserCouponeCodeDel getUserCouponeCodeDel(UserCouponeCodeDel userCouponeCodeDel);
}