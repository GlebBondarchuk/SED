package com.bsu.sed.validator;

import com.bsu.sed.model.dto.PeopleDto;
import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author gbondarchuk
 */
@Component
public class PeopleDtoValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return PeopleDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PeopleDto peopleDto = (PeopleDto) target;

        String password = peopleDto.getPassword();
        String confirmPassword = peopleDto.getConfirmPassword();
        if (!password.equals(confirmPassword)) {
            errors.reject("label.password.notMatch");
        }

        String name = peopleDto.getName();
        boolean existByName = userService.existByName(name);
        if (existByName) {
            errors.reject("label.username.exists");
        }

        String login = peopleDto.getLogin();
        boolean existByLogin = userService.existByLogin(login);
        if (existByLogin) {
            errors.reject("label.login.exists");
        }
    }
}
