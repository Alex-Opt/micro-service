package com.ly.mt.gzg.b.web.config.request;

import java.util.List;

public class RegisterReqVO {
    private List<Long> phones;

    public void setPhones(List<Long> phones) {
        this.phones = phones;
    }

    public List<Long> getPhones() {
        return phones;
    }
}
