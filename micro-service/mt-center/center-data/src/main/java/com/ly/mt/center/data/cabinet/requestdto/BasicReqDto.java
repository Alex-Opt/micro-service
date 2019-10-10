package com.ly.mt.center.data.cabinet.requestdto;

public class BasicReqDto {
    private String owner_name;
    private String owner_phone;
    private String bd_phone;
    private String bd_name;
    private String showcase_imei;
    private int create_type;

    public int getCreate_type() {
        return create_type;
    }

    public void setCreate_type(int create_type) {
        this.create_type = create_type;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getBd_phone() {
        return bd_phone;
    }

    public void setBd_phone(String bd_phone) {
        this.bd_phone = bd_phone;
    }

    public String getBd_name() {
        return bd_name;
    }

    public void setBd_name(String bd_name) {
        this.bd_name = bd_name;
    }

    public String getShowcase_imei() {
        return showcase_imei;
    }

    public void setShowcase_imei(String showcase_imei) {
        this.showcase_imei = showcase_imei;
    }
}
