package com.ly.mt.core.base.dict;

/**
 * cabinetMessage
 * 消息类型-messageType
 *
 * @author wanghongliang
 */
public enum CabinetMessageType {
    MESSAGE_TYPE_GZG("0", "格子柜"),
    MESSAGE_TYPE_ZG("1", "展柜");

    private final String id;
    private final String name;

    CabinetMessageType(String id, String name) {
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