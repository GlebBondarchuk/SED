package com.bsu.sed.service;

import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return systemAttributeDao.getAll();
    }

    @Override
    public void update(SystemAttribute attribute) {
        systemAttributeDao.update(attribute);
    }

    @Override
    public void update(Long id, String value, String description) {
        SystemAttribute systemAttribute = systemAttributeDao.load(id);
        systemAttribute.setValue(value);
        systemAttribute.setDescription(description);
        systemAttributeDao.update(systemAttribute);
    }

    @Override
    public String get(SystemAttributeKey key) {
        return systemAttributeDao.get(key);
    }

    @Override
    public List<SystemAttribute> getAll() {
        return systemAttributeDao.getAll();
    }
}
