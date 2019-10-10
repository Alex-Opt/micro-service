package com.ly.mt.center.data.cabinet.response;

public class CreateCoorpResVo {
    private String store_id;
    private String contract_id;
    private String showcase_imei;

    public String getShowcase_imei() {
        return showcase_imei;
    }

    public void setShowcase_imei(String showcase_imei) {
        this.showcase_imei = showcase_imei;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }
}
