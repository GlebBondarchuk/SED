package com.bsu.sed.controller;

import com.bsu.sed.model.User;
import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for Login page
 *
 * @author gbondarchuk
 */
@Controller("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        User user = userService.getByLogin("system");
        ModelAndView modelAndView = new ModelAndView("sed-main");
        return modelAndView;
    }
}
