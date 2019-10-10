package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerCodes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerCodesMapper {
    /**
     * @Description 保存LodeRunnerCodes
     * @Author taoye
     */
    void insertLodeRunnerCodes(LodeRunnerCodes lodeRunnerCodes);

    /**
     * @Description 删除LodeRunnerCodes
     * @Author taoye
     */
    void deleteLodeRunnerCodes(LodeRunnerCodes lodeRunnerCodes);

    /**
     * @Description 更新LodeRunnerCodes
     * @Author taoye
     */
    int updateLodeRunnerCodes(LodeRunnerCodes lodeRunnerCodes);

    /**
     * @Description 查询LodeRunnerCodes
     * @Author taoye
     */
    LodeRunnerCodes getLodeRunnerCodesByCode(LodeRunnerCodes lodeRunnerCodes);

    /**
     * 将淘金者的邀请数量加1
     * @param lodeRunnerCodes
     * @return
     */
    int addInviteNum(LodeRunnerCodes lodeRunnerCodes);
}