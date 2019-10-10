package com.ly.mt.center.data.user.mapper;

import com.ly.mt.center.data.user.entity.UserConnections;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserConnectionsMapper {
    /**
     * @Description 保存UserConnections
     * @Author taoye
     */
    void insertUserConnections(UserConnections userConnections);

    /**
     * @Description 删除UserConnections
     * @Author taoye
     */
    void deleteUserConnections(UserConnections userConnections);

    /**
     * @Description 更新UserConnections
     * @Author taoye
     */
    int updateUserConnections(UserConnections userConnections);

    /**
     * @Description 查询UserConnections
     * @Author taoye
     */
    UserConnections getUserConnections(UserConnections userConnections);
}