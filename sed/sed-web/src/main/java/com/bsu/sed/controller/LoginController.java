package com.bsu.sed.controller;

import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Controller for Login page
 *
 * @author gbondarchuk
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
//        User user = userService.getByLogin("system");
        String message = messageSource.getMessage("label.title", null, Locale.CHINESE);
        ModelAndView modelAndView = new ModelAndView("sed-main");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirect() {
        return new ModelAndView("sed-main");
    }
}
