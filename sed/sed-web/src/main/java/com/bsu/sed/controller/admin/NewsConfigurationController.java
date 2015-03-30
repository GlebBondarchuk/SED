package com.bsu.sed.controller.admin;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.NewsUrlDto;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.service.news.NewsUrlService;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

/**
 * @author gbondarchuk
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin/news")
public class NewsConfigurationController {
    @Autowired
    private NewsUrlService newsUrlService;

    @RequestMapping("")
    public ModelAndView getNewsConfigurationPage() {
        List<NewsUrl> newsUrls = newsUrlService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.NEWS_URL_PAGE.getTileName());
        modelAndView.addObject("urls", newsUrls);
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateNewsConfiguration(@ModelAttribute("dto") NewsUrlDto dto) throws IOException, FeedException {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/news");
        if (dto.isNull()) {
            return modelAndView;
        }
        newsUrlService.save(dto);
        return modelAndView;
    }

    @RequestMapping("/remove/{id}")
    public RedirectView removeNewsUrl(@PathVariable("id") Long id) {
        newsUrlService.delete(id);
        return new RedirectView("/admin/news", true);
    }
}
