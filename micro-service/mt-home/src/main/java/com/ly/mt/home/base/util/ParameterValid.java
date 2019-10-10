package com.ly.mt.home.base.util;

import org.springframework.validation.BindingResult;

public class ParameterValid {

    public static boolean parameterValid(BindingResult bindingResult) {
        return bindingResult.hasErrors();
    }
}
