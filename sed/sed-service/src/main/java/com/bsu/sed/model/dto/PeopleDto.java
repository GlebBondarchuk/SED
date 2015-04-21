package com.bsu.sed.model.dto;

import com.bsu.sed.model.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

import static com.bsu.sed.model.constraint.ConstraintConstants.*;

/**
 * @author gbondarchuk
 */
public class PeopleDto {
    private String name;
    private String login;
    private Role role;
    private String phone;
    private String photo;
    private String position;
    private String address;
    private boolean head;
    private boolean newsSubscriber;
    private String password;
    private String confirmPassword;

    public String getAddress() {
        return address;
    }

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
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

    @Size(max = USER_LOGIN_MAX_LENGTH)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

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

    @Size(max = USER_PHONE_MAX_LENGTH)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotEmpty
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @NotEmpty
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isNewsSubscriber() {
        return newsSubscriber;
    }

    public void setNewsSubscriber(boolean newsSubscriber) {
        this.newsSubscriber = newsSubscriber;
    }
}
