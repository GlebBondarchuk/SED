package com.bsu.sed.service;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface DocumentService extends BrowsableService {
    Document upload(MultipartFile file);

    Document getDocument(Long id);

    List<Document> find(int limit, int offset);

    void delete(Long id);

    void setRole(Long id, Role role);

    Role getRole(String fileName);
}
