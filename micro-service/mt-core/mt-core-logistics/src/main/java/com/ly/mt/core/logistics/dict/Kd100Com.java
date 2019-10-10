package com.ly.mt.core.logistics.dict;

/**
 * 快递100查询公司编号
 *
 * @author taoye
 */
public enum Kd100Com {
    KD_COM_SF("shunfeng", "SF", "顺丰"),
    KD_COM_YT("yuantong", "YTO", "圆通"),
    KD_COM_ZT("zhongtong", "ZTO", "中通"),
    KD_COM_SFTH("shunfeng", "SF_TH", "顺丰电商特惠"),
    KD_COM_ST("shentong", "STO", "申通"),
    KD_COM_TT("tiantian", "TTKD", "天天"),
    KD_COM_ZJS("zhaijisong", "ZJS", "宅急送"),

    KD_COM_DB("debangwuliu", null,"德邦"),
    KD_COM_EMSBJ("bjemstckj", null,"北京EMS"),
    KD_COM_EMSGJ("emsguoji", null,"EMS-国际件"),
    KD_COM_YZGJ("youzhengguoji", null,"国际包裹"),
    KD_COM_YZGN("youzhengguonei", null,"邮政包裹/平邮"),
    KD_COM_HT("huitongkuaidi", null, "汇通"),
    KD_COM_GT("guotongkuaidi", null,"国通"),
    KD_COM_YD("yunda", null,"韵达"),
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

    public static String getNameByCode(String id) {
        for (Kd100Com kd100Com : Kd100Com.values()) {
            if (kd100Com.getCom().equals(id)) {
                return kd100Com.getDescribe();
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

    public String getDescribe() {
        return describe;
    }

}