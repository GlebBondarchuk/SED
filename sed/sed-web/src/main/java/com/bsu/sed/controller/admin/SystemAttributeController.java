package com.bsu.sed.controller.admin;

import com.bsu.sed.model.SystemAttributeCategory;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.SystemAttributeDto;
import com.bsu.sed.model.persistent.SystemAttribute;
import com.bsu.sed.service.SystemAttributeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        modelAndView.addObject("categorizedAttributes", categorizeAttributes(systemAttributes));
        return modelAndView;
    }

    @RequestMapping(value = "/system", method = RequestMethod.POST)
    public RedirectView updateSystemAttributes(@ModelAttribute("dto") SystemAttributeDto dto) {
        Map<SystemAttributeKey, String> descriptions = dto.getDescriptions();
        for (Map.Entry<SystemAttributeKey, String> entry : dto.getValues().entrySet()) {
            SystemAttributeKey key = entry.getKey();
            systemAttributeService.update(key, entry.getValue(), descriptions.get(key));
        }
        return new RedirectView("/admin/system", true);
    }

    private Map<SystemAttributeCategory, List<SystemAttribute>> categorizeAttributes(List<SystemAttribute> attributes) {
        Map<SystemAttributeCategory, List<SystemAttribute>> categorized = new HashMap<>();
        for (final SystemAttributeCategory category : SystemAttributeCategory.values()) {
            List<SystemAttribute> filtered = new ArrayList<>(attributes);
            CollectionUtils.filter(filtered, new Predicate() {
                @Override
                public boolean evaluate(Object o) {
                    return ((SystemAttribute) o).getCategory().equals(category);
                }
            });
            categorized.put(category, filtered);
        }
        return categorized;
    }
}
