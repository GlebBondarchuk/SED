package com.bsu.sed.controller;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.Document;
import com.bsu.sed.service.DocumentService;
import com.bsu.sed.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping
    public ModelAndView getAll() {
        return new ModelAndView(Tiles.DOCUMENTS_PAGE.getTileName());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/data")
    @ResponseBody
    public String getDocumentsData(@RequestParam("limit") int limit,
                                   @RequestParam("offset") int offset,
                                   @RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "sort", required = false) String sort,
                                   @RequestParam(value = "order", required = false) SortOrder order) {
        return documentService.getJson(limit, offset, search, sort, order);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView upload(MultipartRequest request) {
        List<MultipartFile> files = request.getFiles("files");
        for (MultipartFile file : files) {
            documentService.upload(file);
        }
        return new RedirectView("/documents", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/access/{id}", method = RequestMethod.POST)
    public RedirectView upload(@PathVariable("id") Long id,
                               @RequestParam("role") Role role) {
        documentService.setRole(id, role);
        return new RedirectView("/documents", true);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        documentService.delete(id);
        return "success";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteSelected(@RequestParam("ids") Long[] ids) {
        documentService.delete(Arrays.asList(ids));
        return "success";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping("/download/{id}")
    @ResponseBody
    public byte[] download(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            Document document = documentService.getDocument(id);
            File file = new File(document.getPath());

            String fileName = URLEncoder.encode(document.getName(), "UTF-8");

            response.setContentType(document.getContentType());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setContentLength((int) file.length());
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
