package com.ly.mt.core.common.dict;

import io.swagger.models.auth.In;
import org.springframework.util.StringUtils;

/**
 *@Description 淘金人类型
 *@Author  zhuyh
 */
public enum LodeRunnerTpEnum {
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


    LodeRunnerTpEnum(Integer id, String tp) {
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
    public static LodeRunnerTpEnum getIdByTp(String tp){
        if (StringUtils.isEmpty(tp)){
            return null;
        }
        for (LodeRunnerTpEnum tpEnum : LodeRunnerTpEnum.values()){
            if (tpEnum.getTp().equals(tp)){
                return tpEnum;
            }
        }
        return null;
    }
}
