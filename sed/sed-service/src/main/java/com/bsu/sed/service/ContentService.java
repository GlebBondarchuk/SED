package com.bsu.sed.service;

import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.dto.ContentDto;

/**
 * @author gbondarchuk
 */
public interface ContentService extends BrowsableService {

    void updateContent(Long id, String contentName, String html, Role role);

    ContentDto getContent(Long id);

    void deleteContent(Long id);

    Long createContent();

    Role getRole(Long id);
}
