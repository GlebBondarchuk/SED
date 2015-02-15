package com.bsu.sed.controller.admin;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gbondarchuk
 */
@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequestMapping("/admin")
public class SystemAttributeController {
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping(value = "/system")
    public ModelAndView getSystemPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.SYSTEM_PAGE.getTileName());
        List<SystemAttribute> systemAttributes = systemAttributeService.getAttributes();
        modelAndView.addObject("attributes", systemAttributes);
        return modelAndView;
    }

    @RequestMapping(value = "/system", method = RequestMethod.POST)
    public ModelAndView updateSystemAttributes(@RequestParam("id") Long id,
                                               @RequestParam("value") String value,
                                               @RequestParam("description") String description) {

        systemAttributeService.update(id, value, description);
        return getSystemPage();
    }
}
