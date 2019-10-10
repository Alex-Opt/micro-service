package com.ly.mt.cabinet.b.common.exception;

import java.util.Formatter;

/**
 * aes加解密异常
 */
public class AESException extends RuntimeException {

    public AESException(String format,Object... args) {
        super( new Formatter().format(format, args).toString());    }
}
