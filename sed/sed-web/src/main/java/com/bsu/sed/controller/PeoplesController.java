package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller for peoples page.
 *
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/peoples")
public class PeoplesController {
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("")
    public ModelAndView getPeoplesPage() {
        List<People> peoples = peopleService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.PEOPLES_PAGE.getTileName());
        modelAndView.addObject("peoples", peoples);
        return modelAndView;
    }
}
