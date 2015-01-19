package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.service.UserService;
import com.bsu.sed.validator.UserDetailsDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Controller for sign up user.
 *
 * @author gbondarchuk
 */
@Controller
public class UserSignUpController {

    @Autowired
    private UserDetailsDtoValidator userDetailsDtoValidator;
    @Autowired
    private UserService userService;

    @RequestMapping("/signUp")
    public ModelAndView getSignUpPage() {
        return new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView SignUp(@Valid @ModelAttribute UserDto user, BindingResult result) {
        userDetailsDtoValidator.validate(user, result);
        ModelAndView modelAndView = new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        userService.create(user);
        return modelAndView;
    }
}
