package com.ly.mt.core.base.entity.YHHDHttpResponse;

/**
 * 人相对比返回结果dto
 *//** @deprecated */
public class PortraintCompareRespDto {
    //返回码
    private Integer code;

    //返回信息说明
    private String message;

    //交易流水号
    private String serialNo;

    //返回数据
    private Data data;

    public PortraintCompareRespDto() {}

    public PortraintCompareRespDto(Integer code, String message, String serialNo, Data data) {
        this.code = code;
        this.message = message;
        this.serialNo = serialNo;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        private String status;
        private String address;
        private String gender;
        private String birthday;
        private String score;
        private String result;
        private String code;

        public Data() {}

        public Data(String status, String address, String gender, String birthday, String score, String result, String code) {
            this.status = status;
            this.address = address;
            this.gender = gender;
            this.birthday = birthday;
            this.score = score;
            this.result = result;
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
