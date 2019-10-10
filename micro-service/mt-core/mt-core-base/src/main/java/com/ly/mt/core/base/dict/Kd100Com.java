package com.ly.mt.core.base.dict;

/**
 * 快递100查询公司编号
 *
 * @author taoye
 */
public enum Kd100Com {
    YOU_ZHENG_GUO_NEI("youzhengguonei", "邮政包裹/平邮"),
    YOU_ZHENG_GUO_JI("youzhengguoji", "国际包裹"),
    EMS("ems", "EMS"),
    EMS_GUO_JI("emsguoji", "EMS-国际件"),
    BJ_EMS_TCKJ("bjemstckj", "北京EMS"),
    SHUN_FENG("shunfeng", "顺丰"),
    SHEN_TONG("shentong", "申通"),
    YUAN_TONG("yuantong", "圆通"),
    ZHONG_TONG("zhongtong", "中通"),
    HUI_TONG_KUAI_DI("huitongkuaidi", "汇通"),
    YUN_DA("yunda", "韵达"),
    ZHAI_JI_SONG("zhaijisong", "宅急送"),
    TIAN_TIAN("tiantian", "天天"),
    DE_BANG_WU_LIU("debangwuliu", "德邦"),
    GUO_TONG_KUAI_DI("guotongkuaidi", "国通"),
    ZENG_YI_SU_DI("zengyisudi", "增益"),
    SUER("suer", "速尔"),
    ZTKY("ztky", "中铁物流"),
    ZHONG_TIE_WU_LIU("zhongtiewuliu", "中铁快运"),
    GAN_ZHONG_NENG_DA("ganzhongnengda", "能达"),
    YOU_SHU_WU_LIU("youshuwuliu", "优速"),
    QUAN_FENG_KUAI_DI("quanfengkuaidi", "全峰"),
    JD("jd", "京东");


    private String com;
    private String describe;

    Kd100Com(String com, String describe) {
        this.com = com;
        this.describe = describe;
    }

    public String getCom() {
        return com;
    }
}