package com.bsu.sed.model.dto;

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
}
