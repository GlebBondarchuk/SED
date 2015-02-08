package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.PeopleService;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.utils.JsonUtils;
import com.bsu.sed.validator.UserDetailsDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author gbondarchuk
 */

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SystemAttributeService systemAttributeService;
    @Autowired
    private UserService userService;
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private UserDetailsDtoValidator userDetailsDtoValidator;

    @RequestMapping(value = "/system")
    public ModelAndView getSystemPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.SYSTEM_PAGE.getTileName());
        List<SystemAttribute> systemAttributes = systemAttributeService.getAttributes();
        modelAndView.addObject("attributes", systemAttributes);
        return modelAndView;
    }

    @RequestMapping(value = "/system/data")
    @ResponseBody
    public String getSystemAttributesData() {
        List<SystemAttribute> systemAttributes = systemAttributeService.getAll();
        return JsonUtils.systemAttributesToJson(systemAttributes);
    }

    @RequestMapping(value = "/users")
    public ModelAndView getUsersPage() {
        return new ModelAndView(Tiles.USERS_PAGE.getTileName());
    }

    @RequestMapping(value = "/users/data")
    @ResponseBody
    public String getUsersData(@RequestParam("order") SortOrder order,
                               @RequestParam("limit") int limit,
                               @RequestParam("offset") int offset) {
//        List<User> users = userService.find(order, limit, offset);
        List<User> users = userService.getAll();
        return JsonUtils.usersToJson(users);
    }

    @RequestMapping(value = "/users/remove/{id}")
    @ResponseBody
    public String removeUser(@PathVariable Long id) {
        userService.delete(id);
        return "";
    }

    @RequestMapping(value = "/users/add")
    public ModelAndView getNewUserPage() {
        String emailMsk = systemAttributeService.get(SystemAttributeKey.EMAIL_MASK);
        ModelAndView modelAndView = new ModelAndView(Tiles.SIGN_UP_PAGE.getTileName());
        modelAndView.addObject("roles", Role.values());
        modelAndView.addObject("postURL", "/admin/users/add");
        modelAndView.addObject("emailMask", emailMsk);
        return modelAndView;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ModelAndView saveUser(@Valid @ModelAttribute UserDto user, BindingResult result) {
        userDetailsDtoValidator.validate(user, result);
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        User created = userService.create(user);
        People people = peopleService.getByUsername(created.getName());
        modelAndView.addObject("user", created);
        modelAndView.addObject("people", people);
        return modelAndView;
    }
}
