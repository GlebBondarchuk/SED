package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.User;
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
    private ContentService contentService;
    @Autowired
    private PeopleService peopleService;


    @RequestMapping(value = "/{id}")
    public ModelAndView getUserPage(@PathVariable("id") Long id) {
        People people = peopleService.getByUserId(id);
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
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = getUserPage(id);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/edit/main", method = RequestMethod.POST)
    public ModelAndView saveChanges(@PathVariable("id") Long id,
                                    @ModelAttribute UserDto user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        People people = peopleService.update(user, id);
        modelAndView.addObject("edit", true);
        modelAndView.addObject("people", people);
        modelAndView.addObject("success", "Successfully updated.");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{userId}/edit/tab/{id}", method = RequestMethod.GET)
    public ModelAndView getEditTabUserPage(@PathVariable("userId") Long userId,
                                           @PathVariable("id") Long contentId) {
        ContentDto content = contentService.getContent(contentId);
        ModelAndView modelAndView = new ModelAndView(Tiles.EDIT_TAB.getTileName());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("content", content);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{userId}/edit/tab/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTab(@PathVariable("userId") Long userId,
                                  @PathVariable("id") Long contentId) {
        contentService.deleteContent(contentId);
        ModelAndView modelAndView = getUserPage(userId);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{userId}/edit/tab/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateEditUserPage(@PathVariable("id") Long contentId,
                                     @RequestParam("content") String content,
                                     @RequestParam("contentName") String contentName) {
        contentService.updateContent(contentId, contentName, content);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{userId}/addTab", method = RequestMethod.POST)
    @ResponseBody
    public String addTabUserPage(@PathVariable("userId") Long userId,
                                 @RequestParam("contentName") String contentName,
                                 @RequestParam("content") String content) {
        peopleService.addContent(contentName, content, userId);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{userId}/addTab")
    public ModelAndView addTabUserPage(@PathVariable("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView(Tiles.ADD_NEW_TAB.getTileName());
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

}
