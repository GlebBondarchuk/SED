package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
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
    @RequestMapping(value = "/")
    public ModelAndView loadRootPage() {
        return new ModelAndView(Tiles.MAIN_PAGE.getTileName());
    }
}
