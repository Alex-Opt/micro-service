package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerConfigs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LodeRunnerConfigsMapper {
    /**
     * @Description 保存LodeRunnerConfigs
     * @Author taoye
     */
    void insertLodeRunnerConfigs(LodeRunnerConfigs lodeRunnerConfigs);

    /**
     * @Description 删除LodeRunnerConfigs
     * @Author taoye
     */
    void deleteLodeRunnerConfigs(LodeRunnerConfigs lodeRunnerConfigs);

    /**
     * @Description 更新LodeRunnerConfigs
     * @Author taoye
     */
    int updateLodeRunnerConfigs(LodeRunnerConfigs lodeRunnerConfigs);

    /**
     * @Description 查询LodeRunnerConfigs
     * @Author taoye
     */
    LodeRunnerConfigs getLodeRunnerConfigs(LodeRunnerConfigs lodeRunnerConfigs);

    /**
     * 获取所有
     * @return
     */
    List<LodeRunnerConfigs> getAllLodeRunnerConfigs();
}