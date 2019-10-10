package com.ly.mt.core.data.user.mapper;

import com.ly.mt.core.data.user.entity.UserHbView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserHbView操作接口
 *
 * @author taoye
 */
@Mapper
public interface UserHbViewMapper {
    /**
     * 查询List<UserHbView>
     *
     * @param userHbView 查询条件
     * @return List<UserHbView>
     * @author taoye
     */
    List<UserHbView> listUserHbView(UserHbView userHbView);
}