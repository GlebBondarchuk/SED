package com.bsu.sed.service;

import com.bsu.sed.dao.PrimaryDao;
import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class PrimaryServiceImpl implements PrimaryService {
    @Autowired
    private PrimaryDao primaryDao;

    @Override
//    @Cacheable(value = "primaryCache")
    public Primary get(ContentKey key) {
        Primary primary = primaryDao.get(key);
        if (primary == null) {
            return null;
        }
        Content content = primary.getContent();
        if (content == null) {
            return primary;
        }
        if (content.getContent() != null) {
            content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return primary;
    }

    @Override
    @Cacheable(value = "primaryCache")
    public Primary getHint(ContentKey key) {
        return primaryDao.get(key);
    }
}
