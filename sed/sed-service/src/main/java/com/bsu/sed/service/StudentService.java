package com.bsu.sed.service;

import com.bsu.sed.model.dto.StudentDto;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.Student;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface StudentService {
    Student createStudent(StudentDto dto);

    List<Student> find();

    Student getByLogin(String login);

    Student update(StudentDto dto, String login);
}
