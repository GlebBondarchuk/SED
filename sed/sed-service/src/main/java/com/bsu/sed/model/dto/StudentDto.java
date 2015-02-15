package com.bsu.sed.model.dto;

import com.bsu.sed.model.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.bsu.sed.model.constraint.ConstraintConstants.USER_LOGIN_MAX_LENGTH;
import static com.bsu.sed.model.constraint.ConstraintConstants.USER_NAME_MAX_LENGTH;
import static com.bsu.sed.model.constraint.ConstraintConstants.USER_PASSWORD_MAX_LENGTH;

/**
 * @author gbondarchuk
 */
public class StudentDto {
    private String name;
    private String login;
    private String course;
    private String group;
    private Role role;
    private String phone;
    private String photo;
    private String password;
    private String confirmPassword;

    @Size(max = USER_NAME_MAX_LENGTH)
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Size(max = USER_PASSWORD_MAX_LENGTH)
    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Size(max = USER_LOGIN_MAX_LENGTH)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Size(max = USER_PASSWORD_MAX_LENGTH)
    @NotEmpty
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @NotEmpty
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
