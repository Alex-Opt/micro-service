package com.ly.mt.cabinet.c.cabinet;



import java.util.Date;


public class Cabinet {
    private Integer id;
    private Integer cabinetNo;
    private String gzgCode;
    private Date gmtCreate;
    private Date gmtModify;
    private String status;

    public Cabinet() {
    }

    public Cabinet(Integer id, Integer cabinetNo, String gzgCode, Date gmtCreate, Date gmtModify, String status) {
        this.id = id;
        this.cabinetNo = cabinetNo;
        this.gzgCode = gzgCode;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCabinetNo() {
        return cabinetNo;
    }

    public void setCabinetNo(Integer cabinetNo) {
        this.cabinetNo = cabinetNo;
    }

    public String getGzgCode() {
        return gzgCode;
    }

    public void setGzgCode(String gzgCode) {
        this.gzgCode = gzgCode;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", cabinetNo:" + cabinetNo +
                ", gzgCode:'" + gzgCode + '\'' +
                ", gmtCreate:" + gmtCreate +
                ", gmtModify:" + gmtModify +
                ", status:'" + status + '\'' +
                '}';
    }
}
