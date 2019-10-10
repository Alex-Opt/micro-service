package com.ly.mt.core.base.dict;

/**
 * 项目类型枚举
 * basic_role.project_type
 * user.project_type
 *
 * @author taoye
 */
public enum ProjectType {
    PROJECT_TYPE_GZG_B("101", "格子柜B"),
    PROJECT_TYPE_GZG_C("102", "格子柜C"),
    PROJECT_TYPE_HOME_B("201", "到家B"),
    PROJECT_TYPE_HOME_C("202", "到家C"),
    PROJECT_TYPE_BLUETOOTH("301", "蓝牙APP"),
    PROJECT_TYPE_ADVERTISMENT("401", "广告活动"),
    PROJECT_TYPE_MIS("1000", "运营系统");

    private final String id;
    private final String name;

    ProjectType(String id, String name) {
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
