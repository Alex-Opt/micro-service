package com.ly.mt.cabinet.b.common.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("订单管理-查询条件")
public class StoreAdminOrdersRequestBody extends DataStarusticsRequestBody {

    @ApiModelProperty("货柜号，全量为''")
    private String imei;

    @ApiModelProperty("订单状态（0:待支付,1:已完成,2:超时,3:退款中,4:已退款,5:支付完开柜门失败,6:支付失败,7:支付完成等待开启柜门）")
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
}
