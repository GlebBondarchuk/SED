package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping
    public ModelAndView getNewsPage() {
        List<News> newsList = newsService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.LIST_NEWS_PAGE.getTileName());
        modelAndView.addObject("newsList", newsList);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping("/add")
    public ModelAndView getAddNewsPage() {
        return new ModelAndView(Tiles.ADD_NEWS.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addNews(@RequestParam("content") String content,
                                @RequestParam("contentName") String contentName) {
        newsService.create(contentName, content);
        return "success";
    }
}
