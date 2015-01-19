//package com.bsu.sed.controller.exception;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Exception handler for HTTP Error Statuses.
// *
// * @author gbondarchuk
// */
//@Controller
//public class HttpStatusExceptionHandler {
//
//    /**
//     * Handle HTTP Status 404.
//     *
//     * @return ModelAndView object.
//     */
//    @RequestMapping(value = "/404")
//    public ModelAndView handler404(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView("error.exception");
//        modelAndView.addObject("exception", "404");
//        return modelAndView;
//    }
//}
