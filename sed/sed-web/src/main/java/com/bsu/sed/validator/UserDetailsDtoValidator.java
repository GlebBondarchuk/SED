package com.bsu.sed.validator;

import com.bsu.sed.model.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
* Validator for UserDetailDto.
*
* @author gbondarchuk
*/
@Component
public class UserDetailsDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        String password = userDto.getPassword();
        String confirmPassword = userDto.getConfirmPassword();
        if(!password.equals(confirmPassword)) {
            errors.reject("Passwords not match");
        }
    }
}
