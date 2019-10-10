package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerTrees;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerTreesMapper {
    /**
     * @Description 保存LodeRunnerTrees
     * @Author taoye
     */
    void insertLodeRunnerTrees(LodeRunnerTrees lodeRunnerTrees);

    /**
     * @Description 删除LodeRunnerTrees
     * @Author taoye
     */
    void deleteLodeRunnerTrees(LodeRunnerTrees lodeRunnerTrees);

    /**
     * @Description 更新LodeRunnerTrees
     * @Author taoye
     */
    int updateLodeRunnerTrees(LodeRunnerTrees lodeRunnerTrees);

    /**
     * @Description 查询LodeRunnerTrees
     * @Author taoye
     */
    LodeRunnerTrees getLodeRunnerTrees(LodeRunnerTrees lodeRunnerTrees);
}