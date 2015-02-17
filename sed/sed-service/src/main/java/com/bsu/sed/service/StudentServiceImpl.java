package com.bsu.sed.service;

import com.bsu.sed.dao.StudentDao;
import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.dto.StudentDto;
import com.bsu.sed.model.persistent.Student;
import com.bsu.sed.model.persistent.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MailService mailService;
    @Autowired
    private SystemAttributeDao systemAttributeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Override
    public Student createStudent(StudentDto dto) {
        String emailMask = systemAttributeDao.get(SystemAttributeKey.EMAIL_MASK);
        boolean checkStudentRegistration = systemAttributeDao.getBoolean(SystemAttributeKey.CHECK_STUDENT_REGISTRATION);
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getLogin() + emailMask);
        user.setName(dto.getName());
        String encoded = passwordEncoder.encodePassword(dto.getPassword(), null);
        user.setPassword(encoded);
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setPhoto(dto.getPhoto());
        user.setDisabled(checkStudentRegistration);

        Student student = new Student();
        student.setUser(user);
        student.setCourse(dto.getCourse());
        student.setGroup(dto.getGroup());
        studentDao.create(student);
        if (checkStudentRegistration) {
            mailService.sendRegistrationMessage(user);
        }
        return student;
    }

    @Override
//    @Cacheable(value = "studentsCache")
    public List<Student> find() {
        return studentDao.getAll();
    }
}
