package com.bsu.sed.handler;

import com.bsu.sed.model.Role;
import com.bsu.sed.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Then user authenticated successfully.
 *
 * @author gbondarchuk
 */
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private static final String LOGIN_URL = "/login";

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        com.bsu.sed.model.persistent.User authenticated = userService.getByUsername(username);
        String redirectURL = request.getContextPath();
        if (authenticated == null) {
            response.sendRedirect(redirectURL);
            return;
        }
        session.setAttribute("userId", authenticated.getId());

        String referer = request.getHeader(HttpHeaders.REFERER);

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(Role.ADMIN.name())) {
                if (referer.contains(LOGIN_URL)) {
                    response.sendRedirect(request.getContextPath() + "/admin/system");
                } else {
                    response.sendRedirect(referer);
                }
                return;
            }
        }

        if (referer.contains(LOGIN_URL)) {
            Long id = authenticated.getId();
            redirectURL += "/user/" + id;
            response.sendRedirect(redirectURL);
        } else {
            response.sendRedirect(referer);
        }
    }
}
