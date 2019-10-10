package com.ly.mt.core.common.entity.hd.request;

import com.ly.mt.core.common.entity.hd.dto.HdActivityUserDto;
import com.ly.mt.core.common.entity.hd.dto.HdgoodsSkuRequestBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 活动用户下单请求体
 */

@ApiModel("用户下单请求体")
public class UserDownOrderRequestBody {

    /**
     * sku对象
     */
    @ApiModelProperty("用户信息")
    private HdActivityUserDto hdActivityUserDto;

    /**
     * 购买商品集合
     */
    @ApiModelProperty("购买的商品集合")
    private List<HdgoodsSkuRequestBody> hdGoodsSkuModel;


    @ApiModelProperty(value = "手机验证码")
    private String dynamicCode;

    public HdActivityUserDto getHdActivityUserDto() {
        return hdActivityUserDto;
    }

    public void setHdActivityUserDto(HdActivityUserDto hdActivityUserDto) {
        this.hdActivityUserDto = hdActivityUserDto;
    }

    public List<HdgoodsSkuRequestBody> getHdGoodsSkuModel() {
        return hdGoodsSkuModel;
    }

    public void setHdGoodsSkuModel(List<HdgoodsSkuRequestBody> hdGoodsSkuModel) {
        this.hdGoodsSkuModel = hdGoodsSkuModel;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }
}
