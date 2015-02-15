package com.bsu.sed.validator;

import com.bsu.sed.model.dto.StudentDto;
import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for UserDetailDto.
 *
 * @author gbondarchuk
 */
@Component
public class StudentDtoValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentDto studentDto = (StudentDto) target;

        String password = studentDto.getPassword();
        String confirmPassword = studentDto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            errors.reject("label.password.notMatch");
        }

        String name = studentDto.getName();
        boolean existByName = userService.existByName(name);
        if (existByName) {
            errors.reject("label.username.exists");
        }

        String login = studentDto.getLogin();
        boolean existByLogin = userService.existByLogin(login);
        if (existByLogin) {
            errors.reject("label.login.exists");
        }
    }
}
