package com.bsu.sed.controller.admin;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * @author gbondarchuk
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/text")
public class TextController {
    @Autowired
    private TextService textService;

    @RequestMapping
    public ModelAndView getAll() {
        return new ModelAndView(Tiles.TEXT_PAGE.getTileName());
    }

    @RequestMapping(value = "/data")
    @ResponseBody
    public String getContentData(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "order", required = false) SortOrder order) {
        return textService.getJson(limit, offset, search, sort, order);
    }

    @RequestMapping("/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(Tiles.EDIT_TEXT_PAGE.getTileName());
        modelAndView.addObject("text", textService.get(id));
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public RedirectView edit(@PathVariable("id") Long id,
                             @RequestParam("text") String text) {
        textService.update(id, text);
        return new RedirectView("/text", true);
    }
}
