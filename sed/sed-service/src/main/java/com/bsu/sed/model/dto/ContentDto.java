package com.bsu.sed.model.dto;

import com.bsu.sed.model.Role;

import java.util.Date;

/**
 * @author gbondarchuk
 */
public class ContentDto {
    private static final long serialVersionUID = -2469204873901854757L;

    private Long id;
    private String html;
    private String name;
    private Date updateDate;
    private Role role;
    private boolean isStatic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
