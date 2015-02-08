package com.bsu.sed.model.persistent;

import com.bsu.sed.model.Role;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.bsu.sed.model.constraint.ConstraintConstants.*;

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
    private String phone;
    private String password;
    private Role role;
    private String photo;
    private UserDetails details;
    private boolean disabled;
//    private List<People> peoples;

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
    @Length(max = USER_LOGIN_MAX_LENGTH)
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotEmpty
    @Length(max = USER_NAME_MAX_LENGTH)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(max = USER_PHONE_MAX_LENGTH)
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty
    @Length(max = USER_PASSWORD_MAX_LENGTH)
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "details", nullable = true)
    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    public List<People> getPeoples() {
//        return peoples;
//    }
//
//    public void setPeoples(List<People> peoples) {
//        this.peoples = peoples;
//    }
}
