package com.ly.mt.center.data.load.mapper;

import com.ly.mt.center.data.load.entity.LoadRunnerPersonnels;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoadRunnerPersonnelsMapper {
    /**
     * @Description 保存LoadRunnerPersonnels
     * @Author taoye
     */
    void insertLoadRunnerPersonnels(LoadRunnerPersonnels loadRunnerPersonnels);

    /**
     * @Description 删除LoadRunnerPersonnels
     * @Author taoye
     */
    void deleteLoadRunnerPersonnels(LoadRunnerPersonnels loadRunnerPersonnels);

    /**
     * @Description 更新LoadRunnerPersonnels
     * @Author taoye
     */
    int updateLoadRunnerPersonnels(LoadRunnerPersonnels loadRunnerPersonnels);

    /**
     * @Description 查询LoadRunnerPersonnels
     * @Author taoye
     */
    LoadRunnerPersonnels getLoadRunnerPersonnels(LoadRunnerPersonnels loadRunnerPersonnels);
}