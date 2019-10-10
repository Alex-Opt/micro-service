package com.ly.mt.gzg.b.server.base.constant;

import lombok.Data;

import java.util.Date;

@Data
public class GzgCabinet {
    private Integer id;
    private int cabinetNo;
    private String gzgCode;
    private Date gmtCreate;
    private Date gmtModify;
    private int status;
}
