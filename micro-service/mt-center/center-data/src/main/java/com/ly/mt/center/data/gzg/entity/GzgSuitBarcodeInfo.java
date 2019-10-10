package com.ly.mt.center.data.gzg.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhanglifeng
 * @date 2019-08-27
 * 六号格子套装商品的条码信息表实体类
 */
@ApiModel
public class GzgSuitBarcodeInfo {

    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @ApiModelProperty(value = "商品套装的sku_id", required = true)
    private String suit_spu_id;

    @ApiModelProperty(value = "套装包含的商品的sku_id", required = true)
    private String sku_id;

    @ApiModelProperty(value = "套装包含的商品的条形码编号", required = true)
    private String barcode;

    @ApiModelProperty(value = "创建时间", required = true)
    private String create_time;

    @ApiModelProperty(value = "修改时间", required = true)
    private String modify_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuit_spu_id() {
        return suit_spu_id;
    }

    public void setSuit_spu_id(String suit_spu_id) {
        this.suit_spu_id = suit_spu_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}
