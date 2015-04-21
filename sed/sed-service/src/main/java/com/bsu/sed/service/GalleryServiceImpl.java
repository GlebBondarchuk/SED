package com.bsu.sed.service;

import com.bsu.sed.dao.GalleryDao;
import com.bsu.sed.model.persistent.Document;
import com.bsu.sed.model.persistent.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryDao galleryDao;
    @Autowired
    private DocumentService documentService;

    @Override
    public List<Gallery> getAll() {
        return galleryDao.getAll();
    }

    @Override
    public void upload(MultipartFile multipartFile) {
        Document document = documentService.upload(multipartFile);
        Gallery gallery = new Gallery();
        gallery.setDocument(document);
        galleryDao.create(gallery);
    }

    @Override
    public void delete(Long id) {
        Gallery gallery = galleryDao.load(id);
        documentService.delete(gallery.getDocument().getId());
        galleryDao.delete(id);
    }

    @Override
    public List<Gallery> find(int limit, int offset) {
        return galleryDao.find(limit, offset);
    }

    @Override
    public long count() {
        return galleryDao.count();
    }

    @Override
    public void update(Long id, String description) {
        Gallery gallery = galleryDao.load(id);
        gallery.setDescription(description);
        galleryDao.update(gallery);
    }
}
