package com.bsu.sed.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Global controller for handling exceptions.
 *
 * @author gbondarchuk
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound() {
       return new ModelAndView("error.404");
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleOthersExceptions(Throwable throwable) {
        ModelAndView modelAndView = new ModelAndView("error.exception");
        modelAndView.addObject("exception", throwable.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(StackOverflowError.class)
    public ModelAndView handleStackOverFlowError(StackOverflowError e) {
        ModelAndView modelAndView = new ModelAndView("error.exception");
        modelAndView.addObject("exception", e.getMessage());
        return modelAndView;
    }
}
