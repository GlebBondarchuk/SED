package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public interface StudentDao extends GenericDao<Student, Long> {
    List<Student> getAll();
}
