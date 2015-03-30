package com.bsu.sed.controller.exception;

import com.bsu.sed.exception.UserAcceptingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

import static com.bsu.sed.model.Tiles.*;

/**
 * Global controller for handling exceptions.
 *
 * @author gbondarchuk
 */
@ControllerAdvice
public class ExceptionHandlerController {

    public static final String EXCEPTION = "exception";


    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);
    public static final String REFERER = "referer";

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(NoHandlerFoundException e, HttpServletRequest request) {
        log.warn(e.getMessage());
        ModelAndView modelAndView = new ModelAndView(EXCEPTION_PAGE.getTileName());
        modelAndView.addObject(EXCEPTION, e.getMessage());
        modelAndView.addObject(REFERER, request.getHeader(HttpHeaders.REFERER));
        return modelAndView;
    }

    @ExceptionHandler(UserAcceptingException.class)
    public ModelAndView handleUserAcceptingException(UserAcceptingException e, HttpServletRequest request) {
        log.warn(e.getMessage());
        ModelAndView modelAndView = new ModelAndView(EXCEPTION_PAGE.getTileName());
        modelAndView.addObject(EXCEPTION, e.getMessage());
        modelAndView.addObject(REFERER, request.getHeader(HttpHeaders.REFERER));
        return modelAndView;
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedExceptions(RuntimeException e, HttpServletRequest request) {
        log.warn(e.getMessage());
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            ModelAndView modelAndView = new ModelAndView(EXCEPTION_PAGE.getTileName());
            modelAndView.addObject(EXCEPTION, e.getMessage());
            modelAndView.addObject(REFERER, request.getHeader(HttpHeaders.REFERER));
            return modelAndView;
        }
        return new ModelAndView(LOGIN_PAGE.getTileName());
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleOthersExceptions(RuntimeException e, HttpServletRequest request) {
        log.warn(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView(EXCEPTION_PAGE.getTileName());
        modelAndView.addObject(EXCEPTION, getExceptionMessage(e));
        modelAndView.addObject(REFERER, request.getHeader(HttpHeaders.REFERER));
        return modelAndView;
    }

    private String getExceptionMessage(Throwable e) {
        if (e.getCause() != null) {
            String message = e.getMessage() + "\n" + getExceptionMessage(e.getCause());
            System.out.println(message);
            return message;
        } else {
            return e.getMessage();
        }
    }
}
