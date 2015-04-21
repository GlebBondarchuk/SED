package com.bsu.sed.handler;

import com.bsu.sed.model.Role;
import com.bsu.sed.service.ContentService;
import com.bsu.sed.service.DocumentService;
import com.bsu.sed.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Component
public class PermissionEvaluator implements org.springframework.security.access.PermissionEvaluator {
    @Autowired
    private ContentService contentService;
    @Autowired
    private DocumentService documentService;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        Role role = null;
        if (o instanceof Long) {
            role = contentService.getRole((Long) o);
        } else if (o instanceof String) {
            role = documentService.getRole((String) o);
        }

        return role == null || SecurityUtils.isAccepted(role);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
