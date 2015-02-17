package com.bsu.sed.utils;

import com.bsu.sed.model.Role;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

/**
 * @author gbondarchuk
 */
public class SecurityUtils {
    public static String getAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    public static List<Role> getAuthenticatedRoles() {
        List<Role> roles = new ArrayList<>();
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            roles.add(Role.valueOf(authority.getAuthority()));
        }
        return roles;
    }

    public static Set<Role> getAuthenticatedUserRoles() {
        return new HashSet<>(getAuthenticatedRoles());
    }

    public static boolean hasAnyRole(Role... roles) {
        return CollectionUtils.containsAny(getAuthenticatedRoles(), Arrays.asList(roles));
    }
}