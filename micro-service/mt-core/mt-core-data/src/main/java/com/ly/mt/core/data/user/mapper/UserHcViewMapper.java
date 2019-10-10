package com.ly.mt.core.data.user.mapper;

import com.ly.mt.core.data.user.entity.UserHcView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * UserHcView操作接口
 *
 * @author taoye
 */
@Mapper
public interface UserHcViewMapper {
    /**
     * 查询List<UserHcView>
     *
     * @param userHcView 查询条件
     * @return List<UserHcView>
     * @author taoye
     */
    List<UserHcView> listUserHcView(UserHcView userHcView);
}