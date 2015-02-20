package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.StudentDto;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.StudentService;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.utils.ErrorUtils;
import com.bsu.sed.validator.StudentDtoValidator;
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
    private StudentDtoValidator studentDtoValidator;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemAttributeService systemAttributeService;
    @Autowired
    private ErrorUtils errorUtils;

    @RequestMapping("")
    public ModelAndView getSignUpPage() {
        String emailMask = systemAttributeService.get(SystemAttributeKey.EMAIL_MASK);
        ModelAndView modelAndView = new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
        modelAndView.addObject("role", Role.STUDENT);
        modelAndView.addObject("emailMask", emailMask);
        modelAndView.addObject("postURL", "/signUp");
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView SignUp(@Valid @ModelAttribute StudentDto dto, BindingResult result) {
        studentDtoValidator.validate(dto, result);
        ModelAndView modelAndView = getSignUpPage();
        if (result.hasErrors()) {
            modelAndView.addObject("exception", errorUtils.getErrors(result));
            modelAndView.addObject("dto", dto);
            return modelAndView;
        }
        studentService.createStudent(dto);
        modelAndView.addObject("success", "Please, open <a href=\"http://webmail.bsu.by\" target=\"_blank\" class=\"alert-link\">email</a> to complete registration");
        return modelAndView;
    }

    @RequestMapping(value = "/accept/{id}/{login}")
    public ModelAndView accept(@PathVariable("id") Long id,
                               @PathVariable("login") String login) {
        User user = userService.accept(id, login);
        ModelAndView modelAndView;
        if (user.getRole().in(Role.TEACHER, Role.ADMIN)) {
            modelAndView = new ModelAndView("redirect:/people/" + user.getLogin());
        } else {
            modelAndView = new ModelAndView("redirect:/student/" + user.getLogin());
        }
        return modelAndView;
    }
}
