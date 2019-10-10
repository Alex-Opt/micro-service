package com.ly.mt.center.data.lode.mapper;

import com.ly.mt.center.data.lode.entity.LodeRunnerClues;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LodeRunnerCluesMapper {
    /**
     * @Description 保存LodeRunnerClues
     * @Author taoye
     */
    void insertLodeRunnerClues(LodeRunnerClues lodeRunnerClues);

    /**
     * @Description 删除LodeRunnerClues
     * @Author taoye
     */
    void deleteLodeRunnerClues(LodeRunnerClues lodeRunnerClues);

    /**
     * @Description 更新LodeRunnerClues
     * @Author taoye
     */
    int updateLodeRunnerClues(LodeRunnerClues lodeRunnerClues);

    /**
     * @Description 查询LodeRunnerClues
     * @Author taoye
     */
    LodeRunnerClues getLodeRunnerClues(LodeRunnerClues lodeRunnerClues);
}