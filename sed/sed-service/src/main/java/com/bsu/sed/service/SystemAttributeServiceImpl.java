package com.bsu.sed.service;

import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class SystemAttributeServiceImpl implements SystemAttributeService {

    @Autowired
    private SystemAttributeDao systemAttributeDao;

    @Override
    public List<SystemAttribute> getAttributes() {
        return systemAttributeDao.getAttributesSortedByCategory();
    }

    @Override
    public void update(SystemAttribute attribute) {
        systemAttributeDao.update(attribute);
    }

    @Override
    public void update(SystemAttributeKey key, String value, String description) {
        SystemAttribute systemAttribute = systemAttributeDao.getAttribute(key);
        systemAttribute.setValue(value);
        systemAttribute.setDescription(description);
        systemAttributeDao.update(systemAttribute);
        if(key.equals(SystemAttributeKey.IMAGE_URLS)) {
            systemAttributeDao.evict(key);
        }
    }

    @Override
    public String get(SystemAttributeKey key) {
        return systemAttributeDao.get(key);
    }

    @Override
    public int getInt(SystemAttributeKey key) {
        return systemAttributeDao.getInt(key);
    }

    @Override
    public Date getDate(SystemAttributeKey key) {
        return systemAttributeDao.getDate(key);
    }

    @Override
    public List<SystemAttribute> getAll() {
        return systemAttributeDao.getAll();
    }

    @Override
    @Cacheable(value = "systemCache")
    public List<String> getList(SystemAttributeKey imageUrls) {
        String line = systemAttributeDao.get(SystemAttributeKey.IMAGE_URLS);
        if (StringUtils.isNotBlank(line)) {
            return Arrays.asList(line.split(","));
        }
        return new ArrayList<>();
    }
}
