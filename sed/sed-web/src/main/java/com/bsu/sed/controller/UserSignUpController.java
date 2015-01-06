package com.bsu.sed.controller;

import com.bsu.sed.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for sign up user.
 *
 * @author gbondarchuk
 */
@Controller
public class UserSignUpController {

    @RequestMapping("/signUp")
    public ModelAndView getSignUpPage() {
        return new ModelAndView("sed-sign-up");
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ModelAndView SignUp(@ModelAttribute UserDto user) {

        return new ModelAndView("sed-main");
    }
}
