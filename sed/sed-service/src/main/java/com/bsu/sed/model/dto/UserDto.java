package com.bsu.sed.model.dto;


import com.bsu.sed.model.Role;
import com.bsu.sed.model.constraint.ConstraintConstants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
    private String password;
    private String confirmPassword;


    @Size(max = ConstraintConstants.USER_NAME_MAX_LENGTH)
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = ConstraintConstants.USER_LOGIN_MAX_LENGTH)
    @Pattern(regexp = ConstraintConstants.EMAIL_TEMPLATE)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Size(max = ConstraintConstants.USER_PASSWORD_MAX_LENGTH)
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

    @Size(max = ConstraintConstants.USER_PASSWORD_MAX_LENGTH)
    @NotEmpty
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
