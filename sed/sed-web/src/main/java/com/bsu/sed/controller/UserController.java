package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.service.ContentService;
import com.bsu.sed.service.PeopleService;
import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private PeopleService peopleService;


    @RequestMapping(value = "/{username}")
    public ModelAndView getUserPage(@PathVariable("username") String username) {
        People people = peopleService.getByUsername(username);
        if (people == null) {
            ModelAndView modelAndView = new ModelAndView(Tiles.EXCEPTION_PAGE.getTileName());
            modelAndView.addObject("exception", "User Not Found");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        modelAndView.addObject("people", people);
        return modelAndView;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable("username") String username) {
        ModelAndView modelAndView = getUserPage(username);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/edit/main", method = RequestMethod.POST)
    public ModelAndView saveChanges(@PathVariable("username") String username,
                                    @ModelAttribute UserDto user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        People people = peopleService.update(user, username);
        modelAndView.addObject("edit", true);
        modelAndView.addObject("people", people);
        modelAndView.addObject("success", "Successfully updated.");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/edit/tab/{id}", method = RequestMethod.GET)
    public ModelAndView getEditTabUserPage(@PathVariable("username") String username, @PathVariable("id") Long contentId) {
        ContentDto content = contentService.getContent(contentId);
        ModelAndView modelAndView = new ModelAndView(Tiles.EDIT_TAB.getTileName());
        modelAndView.addObject("username", username);
        modelAndView.addObject("content", content);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/edit/tab/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTab(@PathVariable("username") String username, @PathVariable("id") Long contentId) {
        contentService.deleteContent(contentId);
        ModelAndView modelAndView = getUserPage(username);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/edit/tab/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateEditUserPage(@PathVariable("id") Long contentId,
                                     @RequestParam("content") String content,
                                     @RequestParam("contentName") String contentName) {
        contentService.updateContent(contentId, contentName, content);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/addTab", method = RequestMethod.POST)
    @ResponseBody
    public String addTabUserPage(@PathVariable("username") String username,
                                 @RequestParam("contentName") String contentName,
                                 @RequestParam("content") String content) {
        peopleService.addContent(contentName, content, username);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{username}/addTab")
    public ModelAndView addTabUserPage(@PathVariable("username") String username) {
        ModelAndView modelAndView = new ModelAndView(Tiles.ADD_NEW_TAB.getTileName());
        modelAndView.addObject("username", username);
        return modelAndView;
    }

}
