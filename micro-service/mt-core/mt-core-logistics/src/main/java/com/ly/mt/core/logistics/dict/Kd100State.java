package com.ly.mt.core.logistics.dict;

public enum Kd100State {
    STATE_ON_THE_WAY("0", "在途"),
    STATE_COLLECT("1", "揽收"),
    STATE_DIFFICULT("2", "疑难"),
    STATE_SIGN_FOR("3", "签收"),
    STATE_BACK_FOR("4", "退签"),
    STATE_DELIVERY("5", "派件"),
    STATE_RETURN("6", "退回");

    private final String id;
    private final String name;

    Kd100State(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getNameById(String id) {
        for (Kd100State kd100State : Kd100State.values()) {
            if (kd100State.getId().equals(id)) {
                return kd100State.getName();
            }
        }
        return null;
    }
}