package com.bsu.sed.controller;

import com.bsu.sed.handler.AuthenticationSuccessHandler;
import com.bsu.sed.model.Role;
import com.bsu.sed.model.Tiles;
import com.bsu.sed.service.MailService;
import com.bsu.sed.service.NotificationService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping
    public ModelAndView getNotificationPage(HttpSession session) {
        Long authenticatedUserId = (Long) session.getAttribute(AuthenticationSuccessHandler.AUTHENTICATED_USER_ID);
        ModelAndView modelAndView = new ModelAndView(Tiles.NOTIFICATIONS_PAGE.getTileName());
        modelAndView.addObject("notifications", notificationService.getNotifications(authenticatedUserId));
        if (SecurityUtils.hasAnyRole(Role.ADMIN, Role.TEACHER)) {
            modelAndView.addObject("users", userService.getEnabled());
        }
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/count")
    @ResponseBody
    public long getCount(HttpSession session) {
        Long authenticatedUserId = (Long) session.getAttribute(AuthenticationSuccessHandler.AUTHENTICATED_USER_ID);
        return notificationService.getCount(authenticatedUserId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/remove/{id}")
    public RedirectView removeNotification(@PathVariable("id") Long id, HttpSession session) {
        Long authenticatedUserId = (Long) session.getAttribute(AuthenticationSuccessHandler.AUTHENTICATED_USER_ID);
        notificationService.remove(id, authenticatedUserId);
        return new RedirectView("/notification", true);
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String sendNotification(@RequestParam("to") Long[] toUserIds,
                                   @RequestParam("message") final String message,
                                   HttpSession session) {
        final List<Long> userIds = Arrays.asList(toUserIds);
        final Long authenticatedUserId = (Long) session.getAttribute(AuthenticationSuccessHandler.AUTHENTICATED_USER_ID);
        notificationService.sendNotification(authenticatedUserId, userIds, message);


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            public void run() {
                mailService.sendNotifications(userIds, message, authenticatedUserId);
            }
        });

        return "success";
    }
}
