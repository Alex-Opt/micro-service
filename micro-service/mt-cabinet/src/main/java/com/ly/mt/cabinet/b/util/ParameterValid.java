package com.ly.mt.cabinet.b.util;

import com.ly.mt.cabinet.b.common.constant.RuleConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterValid {
    public static boolean valid(BindingResult bindingResult){
        return bindingResult.hasErrors();
    }

    public static boolean phoneNoRuleValid(String phoneNo){
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phoneNo.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
    }

    public static boolean phoneNoValid(String phoneNo){
        if (StringUtils.length(phoneNo) != 11){
            return false;
        }else {
            Pattern p = Pattern.compile(RuleConstant.phoneRule);
            Matcher m = p.matcher(phoneNo);
            return m.matches();
        }
    }
}
