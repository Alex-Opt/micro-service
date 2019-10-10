package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerUserCodes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerUserCodesMapper {
    /**
     * @Description 保存LodeRunnerUserCodes
     * @Author taoye
     */
    void insertLodeRunnerUserCodes(LodeRunnerUserCodes lodeRunnerUserCodes);

    /**
     * @Description 删除LodeRunnerUserCodes
     * @Author taoye
     */
    void deleteLodeRunnerUserCodes(LodeRunnerUserCodes lodeRunnerUserCodes);

    /**
     * @Description 更新LodeRunnerUserCodes
     * @Author taoye
     */
    int updateLodeRunnerUserCodes(LodeRunnerUserCodes lodeRunnerUserCodes);

    /**
     * @Description 查询LodeRunnerUserCodes
     * @Author taoye
     */
    LodeRunnerUserCodes getLodeRunnerUserCodes(LodeRunnerUserCodes lodeRunnerUserCodes);
}