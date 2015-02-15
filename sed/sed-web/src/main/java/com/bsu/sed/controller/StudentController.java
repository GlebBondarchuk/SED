package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.persistent.Student;
import com.bsu.sed.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/students")
    public ModelAndView getStudentsPage() {
        List<Student> students = studentService.find();
        ModelAndView modelAndView = new ModelAndView(Tiles.STUDENTS_PAGE.getTileName());
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
