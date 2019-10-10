package com.ly.mt.core.util;

public class FilterUtil {
    public static boolean checkFilter(String uri, String filterStr) {
        String[] filters = filterStr.split(",");
        for (String filter : filters) {
            if (uri.startsWith(filter)) {
                return true;
            }
        }
        return false;
    }
}