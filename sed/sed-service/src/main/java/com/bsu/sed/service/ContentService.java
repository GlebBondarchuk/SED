package com.bsu.sed.service;

import com.bsu.sed.model.dto.ContentDto;

/**
 * @author gbondarchuk
 */
public interface ContentService {

    void updateContent(Long id, String contentName, String html);

    ContentDto getContent(Long id);

    void deleteContent(Long id);
}
