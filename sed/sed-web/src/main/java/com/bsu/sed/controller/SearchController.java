package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.SearchDto;
import com.bsu.sed.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping
    public ModelAndView getSearchPage() {
        return new ModelAndView(Tiles.SEARCH_PAGE.getTileName());
    }

    @RequestMapping(value = "/do")
    public ModelAndView doSearch(@RequestParam("query") String query) {
        ModelAndView modelAndView = new ModelAndView(Tiles.SEARCH_PAGE.getTileName());
        List<SearchDto> results = searchService.search(query);
        modelAndView.addObject("results", results);
        return modelAndView;
    }
}
