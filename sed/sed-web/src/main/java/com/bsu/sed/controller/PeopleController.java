package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.dto.PeopleDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.service.ContentService;
import com.bsu.sed.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

/**
 * Controller for peoples page.
 *
 * @author gbondarchuk
 */
@Controller
public class PeopleController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("/peoples")
    public ModelAndView getPeoplesPage() {
        List<People> peoples = peopleService.find();
        Iterator<People> iterator = peoples.iterator();
        while(iterator.hasNext()) {
            People people = iterator.next();
            if(people.getUser().getRole().in(Role.ADMIN)) {
                iterator.remove();
                break;
            }
        }
        ModelAndView modelAndView = new ModelAndView(Tiles.PEOPLES_PAGE.getTileName());
        modelAndView.addObject("peoples", peoples);
        return modelAndView;
    }

    @RequestMapping(value = "/people/{login:.+}")
    public ModelAndView getUserPage(@PathVariable("login") String login) {
        People people = peopleService.getByLogin(login);
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
    @RequestMapping(value = "/people/{login}/edit", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable("login") String login) {
        ModelAndView modelAndView = getUserPage(login);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{userLogin}/edit", method = RequestMethod.POST)
    public ModelAndView saveChanges(@PathVariable("userLogin") String login,
                                    @ModelAttribute PeopleDto dto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView(Tiles.USER_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        People people = peopleService.update(dto, login);
        modelAndView.addObject("edit", true);
        modelAndView.addObject("people", people);
        modelAndView.addObject("success", "Successfully updated.");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{login}/edit/tab/{id}", method = RequestMethod.GET)
    public ModelAndView getEditTabUserPage(@PathVariable("login") String login,
                                           @PathVariable("id") Long contentId) {
        ContentDto content = contentService.getContent(contentId);
        ModelAndView modelAndView = new ModelAndView(Tiles.EDIT_TAB.getTileName());
        modelAndView.addObject("login", login);
        modelAndView.addObject("content", content);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{login}/edit/tab/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTab(@PathVariable("login") String login,
                                  @PathVariable("id") Long contentId) {
        contentService.deleteContent(contentId);
        ModelAndView modelAndView = new ModelAndView("redirect:/people/" + login + "/edit");
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{login}/edit/tab/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String updateEditUserPage(@PathVariable("id") Long contentId,
                                     @RequestParam("content") String content,
                                     @RequestParam("contentName") String contentName) {
        contentService.updateContent(contentId, contentName, content, null);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{login}/addTab", method = RequestMethod.POST)
    @ResponseBody
    public String addTabUserPage(@PathVariable("login") String login,
                                 @RequestParam("contentName") String contentName,
                                 @RequestParam("content") String content) {
        peopleService.addContent(contentName, content, login);
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/people/{login}/addTab")
    public ModelAndView addTabUserPage(@PathVariable("login") String login) {
        ModelAndView modelAndView = new ModelAndView(Tiles.ADD_NEW_TAB.getTileName());
        modelAndView.addObject("login", login);
        return modelAndView;
    }

}
