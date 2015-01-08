package com.bsu.sed.controller;

import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
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

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("sed-login");
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/login/gleb", method = RequestMethod.GET)
    public ModelAndView login2() {
        String message = messageSource.getMessage("label.title", null, Locale.CHINESE);
        ModelAndView modelAndView = new ModelAndView("error.exception");
        modelAndView.addObject("exception", "Vise Versa");
        return modelAndView;
    }
}
