package com.bsu.sed.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Global controller for handling exceptions.
 *
 * @author gbondarchuk
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(NoHandlerFoundException e) {
        log.warn(e.getMessage());
        return new ModelAndView("error.404");
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleOthersExceptions(RuntimeException e) {
        log.warn(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView("error.exception");
        modelAndView.addObject("exception", e.getMessage());
        return modelAndView;
    }
}
