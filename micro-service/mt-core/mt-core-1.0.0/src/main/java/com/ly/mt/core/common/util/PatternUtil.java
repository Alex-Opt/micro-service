package com.ly.mt.core.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {

    public static boolean pattern(String rule,String value){
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
