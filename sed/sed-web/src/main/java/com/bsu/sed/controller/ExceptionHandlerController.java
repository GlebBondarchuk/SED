package com.bsu.sed.controller;

import com.bsu.sed.exception.ResourceNotFoundException;
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
    public ModelAndView handleNotFound(NoHandlerFoundException exception) {
       return new ModelAndView("error.404");
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleOthersExceptions(RuntimeException exception) {
        ModelAndView modelAndView = new ModelAndView("error.exception");
        modelAndView.addObject("exception", exception.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFoungd() {
        return new ModelAndView("error.404");
    }
}
