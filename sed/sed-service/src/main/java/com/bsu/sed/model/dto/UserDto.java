package com.bsu.sed.model.dto;

import com.bsu.sed.model.metadata.UserDtoMetadata;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.utils.EntityUtils;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User details data.
 *
 * @author gbondarchuk
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2469204671833460257L;

    private static final UserDtoMetadata metadata = new UserDtoMetadata();

    private String name;
    private String login;
    private String role;
    private String password;
    private String confirmPassword;

    public UserDto() {
        metadata.setLoginLength(EntityUtils.getFieldMaxLength(User.class, User.Fields.login.name()));
        metadata.setNameLength(EntityUtils.getFieldMaxLength(User.class, User.Fields.name.name()));
        metadata.setPasswordLength(EntityUtils.getFieldMaxLength(User.class, User.Fields.password.name()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserDtoMetadata getMetadata() {
        return metadata;
    }
}
