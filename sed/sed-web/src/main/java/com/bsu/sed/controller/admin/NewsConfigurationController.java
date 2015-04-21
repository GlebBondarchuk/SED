package com.bsu.sed.controller.admin;

import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.NewsUrlDto;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.service.news.NewsUrlService;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/news-url")
public class NewsConfigurationController {
    @Autowired
    private NewsUrlService newsUrlService;

    @RequestMapping
    public ModelAndView getNewsConfigurationPage() {
        List<NewsUrl> newsUrls = newsUrlService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.NEWS_URL_PAGE.getTileName());
        modelAndView.addObject("urls", newsUrls);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getContentData(@RequestParam("limit") int limit,
                                 @RequestParam("offset") int offset,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "order", required = false) SortOrder order) {
        return newsUrlService.getJson(limit, offset, search, sort, order);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editNewsUrl(@PathVariable("id") Long id) {
        NewsUrl url = newsUrlService.get(id);
        ModelAndView modelAndView = new ModelAndView(Tiles.NES_URL_EDIT.getTileName());
        modelAndView.addObject("url", url);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/add")
    public ModelAndView editNewsUrl() {
        return new ModelAndView(Tiles.NES_URL_EDIT.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView updateNewsConfiguration(@RequestParam("id") Long id,
                                                @RequestParam("url") String url,
                                                @RequestParam("searchWords") String searchWords,
                                                @RequestParam(value = "disabled", required = false) boolean disabled) throws IOException, FeedException {
        NewsUrlDto dto = new NewsUrlDto(id, url, searchWords, disabled);
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("redirect:/admin/news-url");
            newsUrlService.save(dto);
            return modelAndView;
        } catch (RuntimeException e) {
            modelAndView = new ModelAndView(Tiles.NES_URL_EDIT.getTileName());
            modelAndView.addObject("url", dto);
            modelAndView.addObject("edit", true);
        }
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/remove/{id}")
    public RedirectView removeNewsUrl(@PathVariable("id") Long id) {
        newsUrlService.delete(id);
        return new RedirectView("/admin/news-url", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/remove")
    @ResponseBody
    public String removeSelectedNewsUrl(@RequestParam("ids") Long[] ids) {
        newsUrlService.delete(Arrays.asList(ids));
        return "success";
    }
}
