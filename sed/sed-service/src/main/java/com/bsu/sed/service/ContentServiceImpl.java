package com.bsu.sed.service;

import com.bsu.sed.dao.ContentDao;
import com.bsu.sed.dao.PrimaryDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.dto.ContentDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.Document;
import com.bsu.sed.utils.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */

@Service
@Transactional
public class ContentServiceImpl implements ContentService {
    private static final String DEFAULT_NAME = "";

    @Autowired
    private ContentDao contentDao;
    @Autowired
    private PrimaryDao primaryDao;

    @Override
    public void updateContent(Long id, String contentName, String html, Role role) {
        Content content = contentDao.load(id);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));
        content.setName(contentName);
        content.setRole(role);
        contentDao.update(content);
        ContentKey key =  primaryDao.get(id);
        if(key != null) {
            primaryDao.evict(key);
        }
    }

    @Override
    public ContentDto getContent(Long id) {
        Content content = contentDao.load(id);
        if(content == null) {
            return null;
        }
        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setName(content.getName());
        contentDto.setUpdateDate(content.getUpdateDate());
        contentDto.setStatic(content.isStatic());
        contentDto.setRole(content.getRole());
        if (content.getContent() != null) {
            contentDto.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return contentDto;
    }

    @Override
    public void deleteContent(Long id) {
        Content content = contentDao.load(id);
        if (content.isStatic()) {
            throw new RuntimeException("Static content can't be deleted");
        } else {
            contentDao.delete(id);
        }
    }

    @Override
    public Long createContent() {
        Content content = new Content();
        content.setName(DEFAULT_NAME);
        contentDao.create(content);
        return content.getId();
    }

    @Override
    public Role getRole(Long id) {
        Content content = contentDao.load(id);
        if(content == null) {
            return null;
        }
        return content.getRole();
    }

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("name", "content", "contentType");
        List<Content> contents = contentDao.find(searchFields, limit, offset, search, sort, order);
        long total = contentDao.count(searchFields, search);
        return JsonUtils.contentsToJson(contents, total);
    }

    @Override
    public void delete(List<Long> ids) {
        contentDao.delete(ids);
    }
}
