package com.ly.mt.center.data.cabinet.requestdto;

/**
 * 格子柜管理中专对象
 */
public class SquareCabinatRequestDto extends BasePageInfoDto {

    /**
     * 用户手机号
     */
    private String phoneNo;

    /**
     * user表id
     */
    private Long userId;


    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
