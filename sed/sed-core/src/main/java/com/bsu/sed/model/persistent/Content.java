package com.bsu.sed.model.persistent;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Entity(name = "Content")
@Table(name = "sed_content")
public class Content extends BaseEntity {
    private Long id;
    private String name;
    private byte[] content;
    private Date updateDate;
    private String contentType;
    private User creator;
    private List<News> news;
    private String html;

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "content")
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @OneToMany(mappedBy = "content", orphanRemoval = true)
    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Transient
    public String getHtml() {
        return html;
    }
}
