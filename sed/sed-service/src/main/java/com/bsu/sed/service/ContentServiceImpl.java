package com.bsu.sed.service;

import com.bsu.sed.dao.ContentDao;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.persistent.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

/**
 * @author gbondarchuk
 */

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Override
    public void updateContent(Long id, String contentName, String html) {
        Content content = contentDao.load(id);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));
        content.setName(contentName);
        contentDao.update(content);
    }

    @Override
    public ContentDto getContent(Long id) {
        Content content = contentDao.load(id);
        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setName(content.getName());
        contentDto.setUpdateDate(content.getUpdateDate());
        if (content.getContent() != null) {
            contentDto.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return contentDto;
    }

    @Override
    public void deleteContent(Long id) {
        contentDao.delete(id);
    }
}
