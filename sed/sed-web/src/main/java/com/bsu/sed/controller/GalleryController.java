package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.Gallery;
import com.bsu.sed.service.GalleryService;
import com.bsu.sed.service.SystemAttributeService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bsu.sed.model.SystemAttributeKey.GALLERY_PAGE_LIMIT;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/gallery")
public class GalleryController {
    private static final int GALLERY_COLUMNS = 3;

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private SystemAttributeService systemAttributeService;

    @RequestMapping(value = "/{limit}/{offset}")
    public ModelAndView getGalleryPage(@PathVariable("limit") Integer limit,
                                       @PathVariable("offset") Integer offset) {
        List<Gallery> galleries = galleryService.find(limit, offset);
        ModelAndView modelAndView = new ModelAndView(Tiles.GALLERY_PAGE.getTileName());
        modelAndView.addObject("galleries", separate(galleries));
        modelAndView.addObject("count", galleryService.count());
        return modelAndView;
    }

    @RequestMapping
    public ModelAndView getGallery() {
        int limit = systemAttributeService.getInt(GALLERY_PAGE_LIMIT);
        ModelAndView modelAndView = getGalleryPage(limit, 0);
        modelAndView.addObject("limit", limit);
        modelAndView.addObject("offset", 0);
        return modelAndView;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        galleryService.upload(multipartFile);
        return new RedirectView("/gallery", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id) {
        ;
        galleryService.delete(id);
        return new RedirectView("/gallery", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public RedirectView edit(@PathVariable("id") Long id,
                             @RequestParam("description") String description) {
        galleryService.update(id, description);
        return new RedirectView("/gallery", true);
    }


    private List<List<Gallery>> separate(List<Gallery> galleries) {
        if (CollectionUtils.isEmpty(galleries)) {
            return new ArrayList<>();
        }
        int parts = galleries.size() / GALLERY_COLUMNS;
        List<List<Gallery>> result = new ArrayList<>();
        if (galleries.size() > GALLERY_COLUMNS) {
            result = Lists.partition(galleries, GALLERY_COLUMNS);
        } else {
            result.add(galleries);
        }
        return result;
    }
}
