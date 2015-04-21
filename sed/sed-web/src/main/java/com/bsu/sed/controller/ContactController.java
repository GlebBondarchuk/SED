package com.bsu.sed.controller;

import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.service.MailService;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private MailService mailService;
    @Autowired
    private TextService textService;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping
    public ModelAndView getContactPage() {
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTACT_PAGE.getTileName());
        modelAndView.addObject("contactDetails", textService.get(TextKey.CONTACT_DETAILS));
        modelAndView.addObject("mapURL", systemAttributeService.get(SystemAttributeKey.MAP_URL));
        return modelAndView;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView sendMessage(@ModelAttribute ContactDto dto) {
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTACT_PAGE.getTileName());
        mailService.sendContactMessage(dto);
        modelAndView.addObject("success", "Successfully sent");
        return modelAndView;
    }
}
