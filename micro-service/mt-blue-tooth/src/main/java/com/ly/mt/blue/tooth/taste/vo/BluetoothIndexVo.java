package com.ly.mt.blue.tooth.taste.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 指标信息
 */
@ApiModel(value="指标详细信息")
public class BluetoothIndexVo {
    @ApiModelProperty(value = "指标id", required = true)
    private String id;
    @ApiModelProperty(value = "指标KEY indexKey:P1-P10代表口感十个参数/indexKey:light-亮度/indexKey:color-颜色/indexKey:model-模式/indexKey:mouthNumber-口数" +
            "indexKey:tasteModel-口感模式", required = true)
    private String indexKey;
    private String indexValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }
}