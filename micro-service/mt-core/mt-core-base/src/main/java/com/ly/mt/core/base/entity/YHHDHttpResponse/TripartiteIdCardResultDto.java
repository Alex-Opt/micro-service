package com.ly.mt.core.base.entity.YHHDHttpResponse;

/**
 * 三方身份证ORC扫描结果
 * code Number 接口返回的状态码(参考下表)
 * message String 接口返回的提示信息
 * serialNo String 流水号
 * data
     * ocr_img_type String OCR 解析结果值
     * ocr_message String OCR 解析结果描述
     * name String 识别出的姓名
     * idcard String 识别出的身份证号
     * address String 识别出的身份证地址
     * gender String 识别出的行吧
     * ethnic String 识别出的民族
     * issue_authority String 识别出的发证机关
     * valid_period String 识别出的有效期
    * photo String 提取出的身份证头像
 * 成功
 *{
 * "code": 200,
 * "message": "",
 * "serialNo": "20180726000000002",
 * "data": { "ocr_img_type": "1", "ocr_message": "xxxxxxx",
 * "name": "xx", "idcard": "xxxxxx",
 * "address": "xxxxxx",
 * "gender": "xxxxxx",
 * "ethnic": "xxxxxx",
 * "issue_authority": "xxxxxx",
 * "valid_period": "xxxxxx" "photo": "base64str",
 * } }
 * 失败: {
 * "code": 502,
 * "message": "解析失败",
 * "serialNo": "20180726000000002"
 * }
 * */
public class TripartiteIdCardResultDto {

    //返回结果值
    /**
     * 200 请求成功
     * 400 请求的参数异常（必填参数为空或格式不正确）
     * 401 请求未授权（请确认账号已经开通并且 token 有效）
     * 403 权益已经到期（账号有效期已过期）
     * 404 接口不存在（请检查接口地址是否正确）
     * 405 请求方式不支持（请检查请求接口的方式，仅支持 POST）
     * 406 请求内容解析失败（请求的参数密文无效或不是有效的 JSON）
     * 408 请求结果超时（系统服务繁忙）
     * 410 服务接口已升级,不再支持（请联系技术支持）
     * 415 请求内容格式不支持（报文请求的 Content-type 解析失败）
     * 429 权益次数不足（账号可调用该接口次数已用完）
     * 500 数据查询错误（请联系技术支持）
     * 502 数据查询失败（请联系技术支持）
     * 503 数据查询失败,请重试（请联系技术支持）
     * 504 数据查询错误,请重试（请联系技术支持）
     */
    private String  code;

    //返回结果描述
    private String  message;

    //服务交易编码
    private String serialNo;

    //返回数据
    private data data;

    public TripartiteIdCardResultDto() {}

    public TripartiteIdCardResultDto(String code, String message, String serialNo, data data) {
        this.code = code;
        this.message = message;
        this.serialNo = serialNo;
        this.data = data;
    }

/**
 * /**
 *  * 三方身份证ORC扫描结果
 *  * code Number 接口返回的状态码(参考下表)
 *  * message String 接口返回的提示信息
 *  * serialNo String 流水号
 *  * data
 *      * ocr_img_type String OCR 解析结果值
 *      * ocr_message String OCR 解析结果描述
 *      * name String 识别出的姓名
 *      * idcard String 识别出的身份证号
 *      * address String 识别出的身份证地址
 *      * gender String 识别出的行吧
 *      * ethnic String 识别出的民族
 *      * issue_authority String 识别出的发证机关
 *      * valid_period String 识别出的有效期
 *     * photo String 提取出的身份证头像
 */
    public class data {
        private  String ocrImgType;
        private String ocrMessage;
        private String name;
        private String idcard;
        private String address;
        private String gender;
        private String ethnic;
        private String issueAuthority;//识别出的发证机关
        private String validPeriod;
        private String photo;

        public data() {
        }

        public data(String ocrImgType, String ocrMessage, String name, String idcard, String address, String gender, String ethnic, String issueAuthority, String validPeriod, String photo) {
            this.ocrImgType = ocrImgType;
            this.ocrMessage = ocrMessage;
            this.name = name;
            this.idcard = idcard;
            this.address = address;
            this.gender = gender;
            this.ethnic = ethnic;
            this.issueAuthority = issueAuthority;
            this.validPeriod = validPeriod;
            this.photo = photo;
        }

        public String getOcrImgType() {
            return ocrImgType;
        }

        public void setOcrImgType(String ocrImgType) {
            this.ocrImgType = ocrImgType;
        }

        public String getOcrMessage() {
            return ocrMessage;
        }

        public void setOcrMessage(String ocrMessage) {
            this.ocrMessage = ocrMessage;
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

        public String getIssueAuthority() {
            return issueAuthority;
        }

        public void setIssueAuthority(String issueAuthority) {
            this.issueAuthority = issueAuthority;
        }

        public String getValidPeriod() {
            return validPeriod;
        }

        public void setValidPeriod(String validPeriod) {
            this.validPeriod = validPeriod;
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

    public TripartiteIdCardResultDto.data getData() {
        return data;
    }

    public void setData(TripartiteIdCardResultDto.data data) {
        this.data = data;
    }
}
