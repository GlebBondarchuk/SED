package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.dto.SearchDto;
import com.bsu.sed.service.SearchService;
import com.bsu.sed.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.bsu.sed.model.SystemAttributeKey.SEARCH_PAGE_LIMIT;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping(value = "/do/{limit}/{offset}")
    public ModelAndView doSearch(@PathVariable("limit") Integer limit,
                                 @PathVariable("offset") Integer offset,
                                 @RequestParam("query") String query) {
        List<SearchDto> results = searchService.search(query, limit, offset);
        ModelAndView modelAndView = new ModelAndView(Tiles.SEARCH_PAGE.getTileName());
        modelAndView.addObject("results", results);
        modelAndView.addObject("query", query);
        modelAndView.addObject("count", 1000);
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView getSearchPage() {
        int limit = systemAttributeService.getInt(SEARCH_PAGE_LIMIT);
        ModelAndView modelAndView = doSearch(limit, 0, null);
        modelAndView.addObject("limit", limit);
        modelAndView.addObject("offset", 0);
        return modelAndView;
    }
}
