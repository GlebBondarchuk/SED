package com.bsu.sed.model.persistent;

import com.bsu.sed.model.Role;
import com.bsu.sed.model.constraint.ConstraintConstants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * User entity.
 *
 * @author gbondarchuk
 */
@Entity(name = "User")
@Table(name = "sed_user")
public class User extends BaseEntity {

    private Long id;
    private String login;
    private String name;
    private String password;
    private Role role;
    private boolean disabled;

    public enum Fields {
        login, password, name
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "disabled")
    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @NotEmpty
    @com.bsu.sed.model.constraint.Email
    @Column(name = "login", length = ConstraintConstants.USER_LOGIN_MAX_LENGTH)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotEmpty
    @Column(name = "name", length = ConstraintConstants.USER_NAME_MAX_LENGTH)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    @Column(name = "password", length = ConstraintConstants.USER_PASSWORD_MAX_LENGTH)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "role", length = ConstraintConstants.USER_ROLE_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
