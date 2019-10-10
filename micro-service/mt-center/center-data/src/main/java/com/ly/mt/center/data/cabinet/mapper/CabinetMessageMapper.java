package com.ly.mt.center.data.cabinet.mapper;

import com.ly.mt.center.data.cabinet.entity.CabinetMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 格子柜B-补货单-持久层
 * @author wanghongliang
 * @date 2019-09-16
 */
@Mapper
public interface CabinetMessageMapper {
    /**
     * 新增一条消息
     * @param cabinetMessage
     * @return
     */
    int insertCabinetMessage(CabinetMessage cabinetMessage);

    /**
     * 更新消息
     * @param cabinetMessage
     * @return
     */
    int updateCabinetMessageById(CabinetMessage cabinetMessage);

    /**
     * 获取根据用户消息
     * @param userId
     * @return
     */
    List<CabinetMessage> getCabinetMessage(@Param("userId") String userId);

}
