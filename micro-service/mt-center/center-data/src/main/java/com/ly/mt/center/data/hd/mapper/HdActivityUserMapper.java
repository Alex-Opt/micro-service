package com.ly.mt.center.data.hd.mapper;

import com.ly.mt.center.data.hd.entity.HdActivityUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HdActivityUserMapper {
    /**
     * @Description 保存HdActivityUser
     * @Author taoye
     */
    void insertHdActivityUser(HdActivityUser hdActivityUser);

    /**
     * @Description 删除HdActivityUser
     * @Author taoye
     */
    void deleteHdActivityUser(HdActivityUser hdActivityUser);

    /**
     * @Description 更新HdActivityUser
     * @Author taoye
     */
    int updateHdActivityUser(HdActivityUser hdActivityUser);

    /**
     * @Description 查询HdActivityUser
     * @Author taoye
     */
    HdActivityUser getHdActivityUser(HdActivityUser hdActivityUser);
}