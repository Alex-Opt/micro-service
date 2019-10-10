package com.ly.mt.cabinet.b.common.dict;
import org.apache.commons.lang.StringUtils;

import	java.util.Set;
/**
 * 店铺类型
 */
public enum StoreTypeEnum {
    A(1,"网吧网咖"),
    B(2,"酒店"),
    C(3,"酒吧"),
    D(4,"饭店"),
    E(5,"便利店"),
    F(6,"饮品店"),
    G(7,"咖啡馆"),
    H(8,"茶楼"),
    I(9,"彩票"),
    J(10,"KTV"),
    K(11,"足浴店"),
    L(12,"4S店"),
    M(13,"礼品店精品店"),
    N(14,"3C通讯店"),
    O(15,"桌球"),
    P(16,"桌游"),
    Q(17,"写字楼1楼大厅"),
    R(18,"烟酒店"),
    S(19,"汽车保养维修店"),
    T(20,"运动馆健身房"),
    U(21,"其他");


    int key;
    String value;

    StoreTypeEnum(int key,String value){
        this.key = key;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static String getByKey(int key) {
        for (StoreTypeEnum entry : StoreTypeEnum.values()){
            if (entry.getKey() == key){
                return entry.getValue();
            }
        }
        return null;
    }

    public static int getByValue(String val){
        for (StoreTypeEnum entry : StoreTypeEnum.values()){
            if (StringUtils.equals(entry.getValue(),val)){
                return entry.getKey();
            }
        }
        return -1;
    }
}
