package com.ly.mt.center.data.cabinet.requestdto;

import org.springframework.util.StringUtils;

/**
 * 店铺管理员请求订单查询
 */
public class CabinetStoreAdminRestDto extends SquareDataStatisticsRequestDto {

    private String imei;

    private String status;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIntStatus(){
        if (!StringUtils.isEmpty(this.status))
            return Integer.valueOf(this.status);
        return 0;
    }
}
