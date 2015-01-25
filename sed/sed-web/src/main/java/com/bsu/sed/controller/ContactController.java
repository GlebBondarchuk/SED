package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @RequestMapping("")
    public ModelAndView getContactPage() {
         return new ModelAndView(Tiles.CONTACT_PAGE.getTileName());
    }
}
