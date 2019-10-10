package com.ly.mt.cabinet.b.common.dict;

import org.apache.commons.lang.StringUtils;
/**
 * 人均消费金额
 */
public enum PerCapitaExpense {
    A(35,"50元以下/人"),
    B(36,"50-80元/人"),
    C(37,"80-100元/人"),
    D(38,"100-120元/人"),
    E(39,"120-150元/人"),
    F(40,"150-200元/人"),
    G(41,"200元以上/人")
    ;
    int key;
    String value;

    PerCapitaExpense(int key1, String value){
        this.key = key;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }

    public int getByValue(String value){
        for (PerCapitaExpense entry : PerCapitaExpense.values()){
            if (StringUtils.equals(entry.getValue(),value)){
                return entry.getKey();
            }
        }
        return -1;
    }

    public String getByKey(int key){
        for (PerCapitaExpense entry : PerCapitaExpense.values()){
            if (entry.getKey() == key){
                return entry.getValue();
            }
        }
        return null;
    }
}
