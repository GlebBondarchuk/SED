package com.bsu.sed.service;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.persistent.Navigation;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NavigationService {
    List<Navigation> getParents();

    List<Navigation> getAll();

    void save(String text, String relativeUrl, Role role, Long parentId);

    void delete(Long id);

    void updateParent(Long parentId);

    List<Navigation> getParentCandidates();

    void update(Long id, String text, String relativeUrl, Role role, int listNumber, Long parentId);

    Navigation get(Long id);
}
