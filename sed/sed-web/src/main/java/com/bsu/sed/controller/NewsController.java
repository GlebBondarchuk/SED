package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.SearchDto;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.service.NewsService;
import com.bsu.sed.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private SearchService searchService;

    @RequestMapping
    public ModelAndView getListNewsPage() {
        List<News> newsList = newsService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.LIST_NEWS_PAGE.getTileName());
        modelAndView.addObject("newsList", newsList);
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView getNewsPage(@PathVariable("id") Long id) {
        News news = newsService.get(id);
        ModelAndView modelAndView = new ModelAndView(Tiles.NEWS_PAGE.getTileName());
        modelAndView.addObject("news", news);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping(value = "/{id}/edit")
    public ModelAndView editNews(@PathVariable("id") Long id) {
        News news = newsService.get(id);
        ModelAndView modelAndView = new ModelAndView(Tiles.ADD_NEWS_PAGE.getTileName());
        modelAndView.addObject("news", news);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ModelAndView updateNews(@PathVariable("id") Long id,
                                   @RequestParam("content") String content,
                                   @RequestParam("contentName") String contentName,
                                   @RequestParam("photo") String photo,
                                   @RequestParam("simpleText") String simpleText) {
        newsService.update(id, contentName, content, photo, simpleText);
        return getNewsPage(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping("/{id}/delete")
    @ResponseBody
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.delete(id);
        return "success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping("/add")
    public ModelAndView getAddNewsPage() {
        return new ModelAndView(Tiles.ADD_NEWS_PAGE.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addNews(@RequestParam("content") String content,
                                @RequestParam("contentName") String contentName,
                                @RequestParam("photo") String photo,
                                @RequestParam("simpleText") String simpleText) {
        newsService.create(contentName, content, photo, simpleText);
        return "success";
    }

    @RequestMapping("/search")
    public ModelAndView newsSearch(@RequestParam("query") String query) {
        ModelAndView modelAndView = new ModelAndView(Tiles.SEARCH_PAGE.getTileName());
        List<SearchDto> results = searchService.searchInNews(query);
        modelAndView.addObject("results", results);
        return modelAndView;
    }
}
