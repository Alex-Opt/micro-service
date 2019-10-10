package com.ly.mt.center.data.gzg.mapper;

import com.ly.mt.center.data.gzg.entity.GzgBUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GzgBUserMapper {
    /**
     * @Description 保存GzgBUser
     * @Author taoye
     */
    void insertGzgBUser(GzgBUser gzgBUser);

    /**
     * @Description 删除GzgBUser
     * @Author taoye
     */
    void deleteGzgBUser(GzgBUser gzgBUser);

    /**
     * @Description 更新GzgBUser
     * @Author taoye
     */
    int updateGzgBUser(GzgBUser gzgBUser);

    /**
     * @Description 查询GzgBUser
     * @Author taoye
     */
    GzgBUser getGzgBUser(GzgBUser gzgBUser);
}