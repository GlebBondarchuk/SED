package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.persistent.Student;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {
}
