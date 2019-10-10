package com.ly.mt.center.third.gy.constant;

import jdk.nashorn.internal.lookup.Lookup;

/**
 * 快递100查询公司编号
 *
 * @author linan
 */
public enum Kd100Com {
    SHUN_FENG("shunfeng", "SF", "顺丰"),
    YUAN_TONG("yuantong", "YTO", "圆通"),
    ZHONG_TONG("zhongtong", "ZTO", "中通"),
    SHUN_FENG_TH("shunfeng", "SF_TH", "顺丰电商特惠"),
    SHEN_TONG("shentong", "STO", "申通"),
    TIAN_TIAN("tiantian", "TTKD", "天天"),
    ZHAI_JI_SONG("zhaijisong", "ZJS", "宅急送")
    ;


    private String com;
    private String gyCom;
    private String describe;

    Kd100Com(String com, String gyCom, String describe) {
        this.com = com;
        this.gyCom = gyCom;
        this.describe = describe;
    }

    public static String getCom(String gyCom){
        for(Kd100Com k : values()){
            if(k.gyCom.equals(gyCom)){
                return k.getCom();
            }
        }
        return null;
    }

    public String getCom() {
        return com;
    }

    public String getGyCom() {
        return gyCom;
    }
}