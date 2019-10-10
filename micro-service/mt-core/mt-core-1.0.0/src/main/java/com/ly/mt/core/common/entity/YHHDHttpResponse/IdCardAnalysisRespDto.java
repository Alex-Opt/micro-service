package com.ly.mt.core.common.entity.YHHDHttpResponse;

/**
 * 身份证解析返回值dto
 */
public class IdCardAnalysisRespDto {

    //返回结果值
    private String code;

    //返回结果描述
    private String message;

    //服务交易编码
    private String serialNo;

    //返回数据
    private RetDate data;

    public IdCardAnalysisRespDto() {
    }

    public IdCardAnalysisRespDto(String code, String message, String serialNo, RetDate data) {
        this.code = code;
        this.message = message;
        this.serialNo = serialNo;
        this.data = data;
    }

    public class RetDate {
        private String ocr_img_type;
        private String ocr_message;
        private String name;
        private String idcard;
        private String address;
        private String gender;
        private String ethnic;
        private String issue_authority;
        private String valid_period;
        private String photo;

        public RetDate() {
        }

        public String getOcr_img_type() {
            return ocr_img_type;
        }

        public void setOcr_img_type(String ocr_img_type) {
            this.ocr_img_type = ocr_img_type;
        }

        public String getOcr_message() {
            return ocr_message;
        }

        public void setOcr_message(String ocr_message) {
            this.ocr_message = ocr_message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEthnic() {
            return ethnic;
        }

        public void setEthnic(String ethnic) {
            this.ethnic = ethnic;
        }

        public String getIssue_authority() {
            return issue_authority;
        }

        public void setIssue_authority(String issue_authority) {
            this.issue_authority = issue_authority;
        }

        public String getValid_period() {
            return valid_period;
        }

        public void setValid_period(String valid_period) {
            this.valid_period = valid_period;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public RetDate getData() {
        return data;
    }

    public void setData(RetDate data) {
        this.data = data;
    }
}
