package com.bsu.sed.model.persistent;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gbondarchuk
 */
@Entity(name = "NewsUrl")
@Table(name = "sed_news_url")
public class NewsUrl extends BaseEntity {
    private Long id;
    private String url;
    private String searchWords;
    private String imageUrl;
    private Date lastSearch;
    private boolean disabled;

    public NewsUrl(Long id, String url, String searchWords, String imageUrl, boolean disabled) {
        this.id = id;
        this.url = url;
        this.searchWords = searchWords;
        this.imageUrl = imageUrl;
        this.disabled = disabled;
    }

    public NewsUrl() {
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

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "search_words")
    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    @Column(name = "imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "last_search")
    public Date getLastSearch() {
        return lastSearch;
    }

    public void setLastSearch(Date lastSearch) {
        this.lastSearch = lastSearch;
    }

    @Column(name = "disabled")
    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
