package com.bsu.sed.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author gbondarchuk
 */
@Component
public class ErrorUtils {
    @Autowired
    private MessageUtils messageUtils;

    public String getErrors(BindingResult bindingResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            String errorCode = error.getCode();
            String errorMessage = messageUtils.getMessage(errorCode);
            stringBuilder.append(errorMessage).append("\n");
        }
        return stringBuilder.toString();
    }
}
