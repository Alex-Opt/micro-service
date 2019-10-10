package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerUserTrees;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerUserTreesMapper {
    /**
     * @Description 保存LodeRunnerUserTrees
     * @Author taoye
     */
    void insertLodeRunnerUserTrees(LodeRunnerUserTrees lodeRunnerUserTrees);

    /**
     * @Description 删除LodeRunnerUserTrees
     * @Author taoye
     */
    void deleteLodeRunnerUserTrees(LodeRunnerUserTrees lodeRunnerUserTrees);

    /**
     * @Description 更新LodeRunnerUserTrees
     * @Author taoye
     */
    int updateLodeRunnerUserTrees(LodeRunnerUserTrees lodeRunnerUserTrees);

    /**
     * @Description 查询LodeRunnerUserTrees
     * @Author taoye
     */
    LodeRunnerUserTrees getLodeRunnerUserTrees(LodeRunnerUserTrees lodeRunnerUserTrees);
}