package com.ly.mt.cabinet.b.common.exception;

import java.util.Formatter;

public class AuthLoginException extends RuntimeException {

    public AuthLoginException(String format,Object... args) {
        super( new Formatter().format(format, args).toString());
    }
}
