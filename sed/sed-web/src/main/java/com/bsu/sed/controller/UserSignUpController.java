package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.validator.UserDetailsDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/signUp")
public class UserSignUpController {

    @Autowired
    private UserDetailsDtoValidator userDetailsDtoValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping("")
    public ModelAndView getSignUpPage() {
        String emailMsk = systemAttributeService.get(SystemAttributeKey.EMAIL_MASK);
        ModelAndView modelAndView = new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
        modelAndView.addObject("roles", Role.values());
        modelAndView.addObject("postURL", "/signUp");
        modelAndView.addObject("emailMask", emailMsk);
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView SignUp(@Valid @ModelAttribute UserDto user, BindingResult result) {
        userDetailsDtoValidator.validate(user, result);
        ModelAndView modelAndView = new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        userService.create(user);
        modelAndView.addObject("success", "Please, open <a href=\"http://webmail.bsu.by\" target=\"_blank\" class=\"alert-link\">email</a> to complete registration");
        return modelAndView;
    }

    @RequestMapping(value = "/accept/{encodedPassword}")
    public ModelAndView accept(@PathVariable String encodedPassword) {
        User user = userService.accept(encodedPassword);
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
