package com.ly.mt.core.base.dict;

import org.springframework.util.StringUtils;

/**
 *@Description 淘金人类型
 *@Author  zhuyh
 */
public enum LodeRunnerTp {
    /**
     * 邀请
     */
    INVITE(1, "invite"),

    /**
     *  通讯录
     */
    ADDRESS_BOOK(2, "address_book"),

    /**
     * 流量
     */
    TRAFFIC(3, "traffic"),

    /**
     * 正常注册
     */
    REGISTER(4, "register")
    ;

    private Integer id;

    private String tp;


    LodeRunnerTp(Integer id, String tp) {
        this.id = id;
        this.tp = tp;
    }

    public Integer getId() {
        return id;
    }

    public String getTp() {
        return tp;
    }
    /**
     *@Description 根据类别获取
     *@Author  zhuyh
     */
    public static LodeRunnerTp getIdByTp(String tp){
        if (StringUtils.isEmpty(tp)){
            return null;
        }
        for (LodeRunnerTp tpEnum : LodeRunnerTp.values()){
            if (tpEnum.getTp().equals(tp)){
                return tpEnum;
            }
        }
        return null;
    }
}
