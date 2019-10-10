package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerUserConfigs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerUserConfigsMapper {
    /**
     * @Description 保存LodeRunnerUserConfigs
     * @Author taoye
     */
    void insertLodeRunnerUserConfigs(LodeRunnerUserConfigs lodeRunnerUserConfigs);

    /**
     * @Description 删除LodeRunnerUserConfigs
     * @Author taoye
     */
    void deleteLodeRunnerUserConfigs(LodeRunnerUserConfigs lodeRunnerUserConfigs);

    /**
     * @Description 更新LodeRunnerUserConfigs
     * @Author taoye
     */
    int updateLodeRunnerUserConfigs(LodeRunnerUserConfigs lodeRunnerUserConfigs);

    /**
     * @Description 查询LodeRunnerUserConfigs
     * @Author taoye
     */
    LodeRunnerUserConfigs getLodeRunnerUserConfigs(LodeRunnerUserConfigs lodeRunnerUserConfigs);
}