package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.Navigation;
import com.bsu.sed.service.NavigationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/map")
public class SiteMapController {

    @Autowired
    private NavigationService navigationService;

    @RequestMapping
    public ModelAndView getSiteMap() {
        List<Navigation> parents = navigationService.getParents();
        ModelAndView modelAndView = new ModelAndView(Tiles.SITE_MAP.getTileName());
        modelAndView.addObject("parents", parents);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/edit/{id}")
    public ModelAndView editSiteMap(@PathVariable("id") Long id,
                                    HttpServletRequest request) {
        Navigation item = navigationService.get(id);
        List<Navigation> parents = navigationService.getParentCandidates();
        ModelAndView modelAndView = new ModelAndView(Tiles.SITE_MAP_ADD.getTileName());
        modelAndView.addObject("item", item);
        modelAndView.addObject("parents", parents);
        modelAndView.addObject("baseURL", getBaseUrl(request));
        modelAndView.addObject("edit", true);
        modelAndView.addObject("roles", Role.getRolesWithEmpty());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public RedirectView updateSiteMap(@PathVariable("id") Long id,
                                      @RequestParam("text") String text,
                                      @RequestParam("url") String url,
                                      @RequestParam(value = "role") Role role,
                                      @RequestParam(value = "listNumber") int listNumber,
                                      @RequestParam("parent") Long parentId, HttpServletRequest request) {
        String relativeUrl = null;
        if (StringUtils.isNotBlank(url)) {
            relativeUrl = getRelativeUrl(request, url);
        }
        navigationService.update(id, text, relativeUrl, role, listNumber,  parentId);
        return new RedirectView("/map", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/add")
    public ModelAndView getSiteMapAddPage() {
        List<Navigation> parents = navigationService.getParentCandidates();
        ModelAndView modelAndView = new ModelAndView(Tiles.SITE_MAP_ADD.getTileName());
        modelAndView.addObject("parents", parents);
        modelAndView.addObject("roles", Role.getRolesWithEmpty());
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView addSiteMap(@RequestParam("text") String text,
                                   @RequestParam("url") String url,
                                   @RequestParam(value = "role") Role role,
                                   @RequestParam("parent") Long parentId, HttpServletRequest request) {
        String relativeUrl = null;
        if (StringUtils.isNotBlank(url)) {
            relativeUrl = getRelativeUrl(request, url);
        }
//      navigationService.updateParent(parentId);
        navigationService.save(text, relativeUrl, role, parentId);
        return new RedirectView("/map", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}")
    public RedirectView deleteSiteMap(@PathVariable("id") Long id) {
        navigationService.delete(id);
        return new RedirectView("/map", true);
    }

    private String getRelativeUrl(HttpServletRequest request, String url) {
        String baseURL = getBaseUrl(request);
        if (!url.contains(baseURL) || url.indexOf(baseURL) != 0) {
            throw new RuntimeException("URL should begin with " + baseURL);
        }
        return url.substring(baseURL.length(), url.length());
    }

    private String getBaseUrl(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        StringBuffer requestURL = request.getRequestURL();
        return requestURL.substring(0, requestURL.indexOf(servletPath));
    }
}
