package com.bsu.sed.service;

import com.bsu.sed.dao.NavigationDao;
import com.bsu.sed.model.persistent.Navigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NavigationServiceImpl implements NavigationService {
    @Autowired
    private NavigationDao navigationDao;

    @Override
    @Cacheable(value = "siteMapCache")
    public List<Navigation> getParents() {
        return navigationDao.getParents();
    }

    @Override
    public List<Navigation> getAll() {
        return navigationDao.getAll();
    }

    @Override
    public void save(String text, String relativeUrl, boolean authorizedOnly, Long parentId) {
        Navigation navigation = new Navigation();
        navigation.setText(text);
        navigation.setUrl(relativeUrl);
        navigation.setAuthorizedOnly(authorizedOnly);
        if (parentId != null) {
            Navigation parent = navigationDao.load(parentId);
            navigation.setParent(parent);
        }
        navigationDao.create(navigation);
        navigationDao.evict();
    }

    @Override
    public void updateParent(Long parentId) {
        if (parentId != null) {
            Navigation parent = navigationDao.load(parentId);
            if (CollectionUtils.isEmpty(parent.getChildren())) {
                Navigation child = new Navigation(parent);
                navigationDao.create(child);
            }
        }
        navigationDao.evict();
    }

    @Override
    public List<Navigation> getParentCandidates() {
        return navigationDao.getParentCandidates();
    }

    @Override
    public void update(Long id, String text, String relativeUrl, boolean authorizedOnly, int listNumber, Long parentId) {
        Navigation navigation = navigationDao.load(id);
        navigation.setText(text);
        navigation.setUrl(relativeUrl);
        navigation.setAuthorizedOnly(authorizedOnly);
        navigation.setListNumber(listNumber);
        if (parentId != null) {
            Navigation parent = navigationDao.load(parentId);
            navigation.setParent(parent);
        } else {
            navigation.setParent(null);
        }
        navigationDao.update(navigation);
        navigationDao.evict();
    }

    @Override
    public Navigation get(Long id) {
        return navigationDao.load(id);
    }

    @Override
    public void delete(Long id) {
        Navigation navigation = navigationDao.load(id);
        Navigation parent = navigation.getParent();
        for (Navigation child : navigation.getChildren()) {
            if (parent == null) {
                child.setParent(null);
            } else {
                child.setParent(parent);
            }
            navigationDao.update(child);
        }
        navigationDao.delete(id);
        navigationDao.evict();
    }
}
