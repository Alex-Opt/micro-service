package com.ly.mt.cabinet.b.replenish.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="商品spu信息")
public class GoodsSpuInfoVo {
    @ApiModelProperty(value = "主键id", required = true)
    private String id;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品代码")
    private String code;
    @ApiModelProperty("类目id")
    private String cid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}