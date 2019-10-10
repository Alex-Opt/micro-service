package com.ly.mt.blue.tooth.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description user
 * @Author whl
 */
@ApiModel(value="身份证扫描结果")
public class BlueToothCertficationOcrResultVo {
    @ApiModelProperty(value = "身份证正面图片URL", required = true)
    private String idCardFrontUrl;
    @ApiModelProperty(value = "身份证反面图片URL", required = true)
    private String idCardBackUrl;
    @ApiModelProperty(value = "姓名", required = true)
    private String cardName;
    @ApiModelProperty(value = "证件号", required = true)
    private String cardId;

    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}