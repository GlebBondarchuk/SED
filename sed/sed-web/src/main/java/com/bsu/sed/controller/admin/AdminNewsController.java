package com.bsu.sed.controller.admin;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/admin/news")
public class AdminNewsController {
    @Autowired
    private NewsService newsService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping
    public ModelAndView getAll() {
        return new ModelAndView(Tiles.ADMIN_NEWS_PAGE.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getContentData(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "order", required = false) SortOrder order) {
        return newsService.getJson(limit, offset, search, sort, order);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/remove")
    @ResponseBody
    public String removeSelectedNewsUrl(@RequestParam("ids") Long[] ids) {
        newsService.delete(Arrays.asList(ids));
        return "success";
    }
}
