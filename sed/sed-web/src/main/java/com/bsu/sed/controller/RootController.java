package com.bsu.sed.controller;

import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.service.PeopleService;
import com.bsu.sed.service.PrimaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for Root page
 *
 * @author gbondarchuk
 */
@Controller
public class RootController {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private PrimaryService primaryService;

    @RequestMapping(value = "/")
    public ModelAndView loadRootPage() {
        People head = peopleService.getHead();
        ModelAndView modelAndView = new ModelAndView(Tiles.MAIN_PAGE.getTileName());
        modelAndView.addObject("head", head);
        modelAndView.addObject("scientific", primaryService.get(ContentKey.SCIENTIFIC_WORK));
        modelAndView.addObject("educational", primaryService.get(ContentKey.EDUCATIONAL_WORK));
        modelAndView.addObject("conference", primaryService.get(ContentKey.CONFERENCE));
        modelAndView.addObject("departmentInfo", primaryService.get(ContentKey.DEPARTMENT_INFO));
        return modelAndView;
    }
}
