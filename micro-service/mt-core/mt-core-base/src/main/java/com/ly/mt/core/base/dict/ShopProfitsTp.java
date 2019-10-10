package com.ly.mt.core.base.dict;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/15
 * @描述
 */
public enum ShopProfitsTp {
    GRAB("grab","抢单"),
    REWARD("reward","抢单奖励"),
    LODE("lode","淘金"),;

    private final String id;
    private final String name;

    ShopProfitsTp(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     *@Description 根据id查询枚举类型
     *@Author  zhuyh
     */
    public static ShopProfitsTp getById(String id){
        if (id == null){
            return null;
        }
        for (ShopProfitsTp tpEnum : ShopProfitsTp.values()){
            if (tpEnum.getId().equals(id)){
                return tpEnum;
            }
        }
        return null;
    }
}
