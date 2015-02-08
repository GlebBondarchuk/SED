package com.bsu.sed.controller;

import com.bsu.sed.model.MailMessage;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.dto.ContactDto;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.service.sender.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private MailBuilder mailBuilder;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping("")
    public ModelAndView getContactPage() {
        return new ModelAndView(Tiles.CONTACT_PAGE.getTileName());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView sendMessage(@ModelAttribute ContactDto dto) {
        ModelAndView modelAndView = new ModelAndView(Tiles.CONTACT_PAGE.getTileName());
        String adminEmail = systemAttributeService.get(SystemAttributeKey.EMAIL);
        MailMessage mailMessage = mailBuilder.buildContactMessage(dto, adminEmail);
        mailSender.send(mailMessage);
        modelAndView.addObject("success", "Successfully sent");
        return modelAndView;
    }
}
