package com.bsu.sed.security;

import com.bsu.sed.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author gbondarchuk
 */
@Service("securityService")
public class SecurityService {
    public boolean hasAccessToUserPage(UserDetails principal, String username) {
        if (principal.getUsername().equals(username)) {
            return true;
        }
        for (GrantedAuthority grantedAuthority : principal.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(Role.STUDENT.name())) {
                return false;
            }
        }
        return true;
    }
}
