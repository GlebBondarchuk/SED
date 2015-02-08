package com.bsu.sed.model.dto;


import com.bsu.sed.model.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.bsu.sed.model.constraint.ConstraintConstants.*;

/**
* User details data.
*
* @author gbondarchuk
*/
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2469204671833460257L;

    private String name;
    private String login;
    private Role role;
    private String phone;
    private String photo;
    private String position;
    private String address;
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

    @Size(max = USER_LOGIN_MAX_LENGTH)
    @com.bsu.sed.model.constraint.Email
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Size(max = USER_PASSWORD_MAX_LENGTH)
    @NotEmpty
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Size(max = USER_PHONE_MAX_LENGTH)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Size(max = USER_PASSWORD_MAX_LENGTH)
    @NotEmpty
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
