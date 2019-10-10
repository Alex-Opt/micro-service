package com.ly.mt.core.base.dict;

/**
 * redis缓存刷新状态枚举类
 * redis_refresh.refresh_status
 *
 * @author taoye
 */
public enum RedisRefreshStatus {
    REDIS_REFRESH_STATUS_WAIT("1", "等待处理"),
    REDIS_REFRESH_STATUS_FINISH("2", "处理完成"),
    REDIS_REFRESH_STATUS_ERROR("3", "处理失败");

    private final String id;
    private final String name;

    RedisRefreshStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}