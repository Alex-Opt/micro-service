package com.ly.mt.center.third.base.util;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class WeChatConfig {

    private byte[] certMetaData;

    public byte[] readCert(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("classpath:apiclient_cert.p12");
        try {
            this.certMetaData = IOUtils.toByteArray(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.certMetaData;
    }

    public byte[] getCertMetaData() {
        return certMetaData;
    }
}
