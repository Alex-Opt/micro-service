package com.ly.mt.cabinet.b.common.request;

import com.ly.mt.cabinet.b.common.dto.BasePageInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WarehouseKeeperVo extends BasePageInfoDto {

    @ApiModelProperty("0：全部,1:待使用,2:已上线")//'0:待创建  1:已创建  2:已使用'
    private String create_status;

    @ApiModelProperty("搜索关键字")
    private String search_key;

    public String getCreate_status() {
        return create_status;
    }

    public void setCreate_status(String create_status) {
        this.create_status = create_status;
    }

    public String getSearch_key() {
        return search_key;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    @Override
    public String toString() {
        return "WarehouseKeeperVo{" +
                "create_status='" + create_status + '\'' +
                ", search_key='" + search_key + '\'' +
                '}';
    }
}





