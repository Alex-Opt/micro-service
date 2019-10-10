package com.ly.mt.gzg.b.server.base.entity;

import lombok.Data;

@Data
public class GzgChannelCode {
    private long id;
    private String barCode;
    private String skuCode;
    private int state;//0未出售 1 已出售
    private String sixNineCode;
}
