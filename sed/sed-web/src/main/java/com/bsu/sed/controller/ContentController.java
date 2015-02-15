package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/{id}")
    public ModelAndView getContentPage(@PathVariable("id") Long id) {
        return loadContentPage(id, false);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/edit")
    public ModelAndView editContentPage(@PathVariable("id") Long id) {
        return loadContentPage(id, true);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveContentPage(@PathVariable("id") Long id,
                                        @RequestParam("content") String content,
                                        @RequestParam("contentName") String contentName) {
        contentService.updateContent(id, contentName, content);
        return "success";
    }

    private ModelAndView loadContentPage(Long contentId, boolean edit) {
        ContentDto content = contentService.getContent(contentId);
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTENT_PAGE.getTileName());
        modelAndView.addObject("title", content.getName());
        modelAndView.addObject("content", content);
        modelAndView.addObject("edit", edit);
        return modelAndView;
    }
}
