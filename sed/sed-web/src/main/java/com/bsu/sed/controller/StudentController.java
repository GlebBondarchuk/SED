package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.model.dto.PeopleDto;
import com.bsu.sed.model.dto.StudentDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.Student;
import com.bsu.sed.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/student/{login:.+}")
    public ModelAndView getStudentPage(@PathVariable("login") String login) {
        Student student = studentService.getByLogin(login);
        ModelAndView modelAndView = new ModelAndView(Tiles.STUDENT_PAGE.getTileName());
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/student/{login}/edit", method = RequestMethod.GET)
    public ModelAndView getEditUserPage(@PathVariable("login") String login) {
        ModelAndView modelAndView = getStudentPage(login);
        modelAndView.addObject("edit", true);
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/student/{userLogin}/edit", method = RequestMethod.POST)
    public ModelAndView saveChanges(@PathVariable("userLogin") String login,
                                    @ModelAttribute StudentDto dto, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView(Tiles.STUDENT_PAGE.getTileName());
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            return modelAndView;
        }
        Student student = studentService.update(dto, login);
        modelAndView.addObject("edit", true);
        modelAndView.addObject("student", student);
        modelAndView.addObject("success", "Successfully updated.");
        return modelAndView;
    }
}
