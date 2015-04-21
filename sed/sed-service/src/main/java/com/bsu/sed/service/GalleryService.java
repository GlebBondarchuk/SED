package com.bsu.sed.service;

import com.bsu.sed.model.persistent.Gallery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface GalleryService {
    List<Gallery> getAll();

    void upload(MultipartFile multipartFile);

    void delete(Long id);

    List<Gallery> find(int limit, int offset);

    long count();

    void update(Long id, String description);
}
