package com.bsu.sed.controller;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/system")
    public ModelAndView getSystemPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.SYSTEM_PAGE.getTileName());
        List<SystemAttribute> systemAttributes = systemAttributeService.getAttributes();
        modelAndView.addObject("attributes", systemAttributes);
        return modelAndView;
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
        return JsonUtils.toJson(users);
    }

    @RequestMapping(value = "/users/remove/{id}")
    @ResponseBody
    public String removeUser(@PathVariable Long id) {
        userService.delete(id);
        return "";
    }
}
