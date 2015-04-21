package com.bsu.sed.controller;

import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.service.PeopleService;
import com.bsu.sed.service.PrimaryService;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.TextService;
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
    private PrimaryService primaryService;
    @Autowired
    private TextService textService;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping(value = "/")
    public ModelAndView loadRootPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.MAIN_PAGE.getTileName());
        modelAndView.addObject("scientific", primaryService.get(ContentKey.SCIENTIFIC_WORK));
        modelAndView.addObject("educational", primaryService.get(ContentKey.EDUCATIONAL_WORK));
        modelAndView.addObject("conference", primaryService.get(ContentKey.CONFERENCE));
        modelAndView.addObject("departmentInfo", primaryService.get(ContentKey.DEPARTMENT_INFO));
        modelAndView.addObject("mainContent", primaryService.get(ContentKey.MAIN_CONTENT));

        modelAndView.addObject("scientificText", textService.get(TextKey.SCIENTIFIC_TEXT));
        modelAndView.addObject("educationalText", textService.get(TextKey.EDUCATIONAL_TEXT));
        modelAndView.addObject("conferenceText", textService.get(TextKey.CONFERENCE_TEXT));
        modelAndView.addObject("mainAddressText", textService.get(TextKey.MAIN_ADDRESS_TEXT));

//        modelAndView.addObject("images", systemAttributeService.getList(SystemAttributeKey.IMAGE_URLS));
        return modelAndView;
    }
}
