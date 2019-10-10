package com.ly.mt.core.data.basic.dao;

import com.ly.mt.core.data.basic.entity.BasicArea;
import com.ly.mt.core.data.basic.entity.BasicFunc;

import java.util.List;

/**
 * BasicArea操作接口
 *
 * @author taoye
 */
public interface BasicAreaDao {
    /**
     * 从redis根据id查询BasicArea
     * redis不存在则查询mysql
     *
     * @param mId 区域ID
     * @return BasicArea
     * @author taoye
     */
    BasicArea getBasicAredByMIdFromRedis(String mId);

    /**
     * 从redis根据mPid查询List<BasicArea>
     * redis不存在则查询mysql
     *
     * @param pid 区域父ID
     * @return List<BasicArea>
     * @author taoye
     */
    List<BasicArea> listBasicAreaByMPidFromRedis(String pid);
}