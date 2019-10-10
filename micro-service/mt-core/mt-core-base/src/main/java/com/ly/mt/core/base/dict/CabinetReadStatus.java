package com.ly.mt.core.base.dict;

/**
 * cabinetMessage
 * 是否已读-read_status
 *
 * @author wanghongliang
 */
public enum CabinetReadStatus {
    READ_STATUS_NO("0", "未读"),
    READ_STATUS_YES("1", "已读");

    private final String id;
    private final String name;

    CabinetReadStatus(String id, String name) {
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