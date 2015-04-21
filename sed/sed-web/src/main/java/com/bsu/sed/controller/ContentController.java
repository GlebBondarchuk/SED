package com.bsu.sed.controller;

import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.persistent.Primary;
import com.bsu.sed.service.ContentService;
import com.bsu.sed.service.PrimaryService;
import com.bsu.sed.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;
    @Autowired
    private PrimaryService primaryService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping
    public ModelAndView getAll() {
        return new ModelAndView(Tiles.CONTENTS_PAGE.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getContentData(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "order", required = false) SortOrder order) {
        return contentService.getJson(limit, offset, search, sort, order);
    }


    @PreAuthorize("hasPermission(#id,'')")
    @RequestMapping(value = "/{id}")
    public ModelAndView getContentPage(@PathVariable("id") Long id) throws AccessDeniedException {
        return loadContentPage(id, false);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/edit")
    public ModelAndView editContentPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = loadContentPage(id, true);
        modelAndView.addObject("roles", Role.getRolesWithEmpty());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create")
    public RedirectView createPage() {
        Long id = contentService.createContent();
        return new RedirectView("/content/" + id + "/edit", true);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/delete")
    public RedirectView deletePage(@PathVariable("id") Long id) {
        contentService.deleteContent(id);
        return new RedirectView("/", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/delete")
    @ResponseBody
    public String removeSelectedNewsUrl(@RequestParam("ids") Long[] ids) {
        contentService.delete(Arrays.asList(ids));
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public String saveContentPage(@PathVariable("id") Long id,
                                  @RequestParam("content") String content,
                                  @RequestParam("contentName") String contentName,
                                  @RequestParam("role") Role role) {
        contentService.updateContent(id, contentName, content, role);
        return "success";
    }

    @RequestMapping("/educational")
    public ModelAndView getEducationalPage() {
        return getPrimaryPage(ContentKey.EDUCATIONAL_WORK);
    }

    @RequestMapping("/scientific")
    public ModelAndView getScientificPage() {
        return getPrimaryPage(ContentKey.SCIENTIFIC_WORK);
    }

    @RequestMapping("/conference")
    public ModelAndView getConferencePage() {
        return getPrimaryPage(ContentKey.CONFERENCE);
    }

    @RequestMapping("/main")
    public ModelAndView getMainPage() {
        return getPrimaryPage(ContentKey.MAIN_CONTENT);
    }

    private ModelAndView getPrimaryPage(ContentKey key) {
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTENT_PAGE.getTileName());
        Primary educational = primaryService.get(key);
        modelAndView.addObject("title", educational.getContent().getName());
        modelAndView.addObject("content", educational.getContent());
        return modelAndView;
    }

    private ModelAndView loadContentPage(Long contentId, boolean edit) {
        ContentDto content = contentService.getContent(contentId);
        if (content == null) {
            throw new RuntimeException("Page does not exist.");
        }
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTENT_PAGE.getTileName());
        modelAndView.addObject("title", content.getName());
        modelAndView.addObject("content", content);
        modelAndView.addObject("edit", edit);
        return modelAndView;
    }
}
