package com.ly.mt.center.third.fn.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * urlencode工具类
 */
public class FnUrlUtil {
    private static FnUrlUtil instance = new FnUrlUtil();

    private FnUrlUtil() {
    }

    public static FnUrlUtil getInstance() {
        return instance;
    }

    private String DEFAULT = "utf-8";

    public String urlEncode(String source) throws UnsupportedEncodingException {
        return urlEncode(source, DEFAULT);
    }

    public String urlEncode(String source, String type) throws UnsupportedEncodingException {
        return URLEncoder.encode(source, type);
    }
}