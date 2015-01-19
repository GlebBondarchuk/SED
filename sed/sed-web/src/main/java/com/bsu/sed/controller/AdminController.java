package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gbondarchuk
 */

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping(value = "/system")
    public ModelAndView getSystemPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.SYSTEM_PAGE.getTileName());
        List<SystemAttribute> systemAttributes = systemAttributeService.getAttributes();
        modelAndView.addObject("attributes", systemAttributes);
        return modelAndView;
    }
}
