package com.ly.mt.core.common.dict;

/**
 * 小B抢发货单的状态枚举类
 */
public enum BattleOrderStatusEnum {
    BATTLE_NOT_GRAB("1","可抢,尚未被抢"),
    BATTLE_ALREADY_OTHER_GRAB("2","已被别人抢"),
    BATTLE_ALREADY_SELF_GRAB("3","已抢到自己手中"),
    BATTLE_GIVE_UP_GRAB("4","放弃抢"),;

    private final String id;
    private final String name;

    BattleOrderStatusEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }}
