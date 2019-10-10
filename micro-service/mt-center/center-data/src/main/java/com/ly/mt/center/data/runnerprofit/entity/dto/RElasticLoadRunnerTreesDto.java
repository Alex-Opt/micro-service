package com.ly.mt.center.data.runnerprofit.entity.dto;

import java.util.Map;

/**
 * @description
 * 简单弹性赚钱人树对象
 * @author panjingtian
 * @date 2019/6/30 10:55 AM
 */
public class RElasticLoadRunnerTreesDto {


    /**
     * 当前用户userid
     */
    private Long userId;

    /**
     * 向上5级人树结构
     * <级别，上级userid>
     * key为上数第几级别，value为对应的userid
     */
    private Map<Integer,Long> trees;


    public RElasticLoadRunnerTreesDto(Long userId, Map<Integer, Long> trees) {
        this.userId = userId;
        this.trees = trees;
    }

    public RElasticLoadRunnerTreesDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Integer, Long> getTrees() {
        return trees;
    }

    public void setTrees(Map<Integer, Long> trees) {
        this.trees = trees;
    }
}
