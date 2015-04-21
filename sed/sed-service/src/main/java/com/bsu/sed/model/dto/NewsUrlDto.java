package com.bsu.sed.model.dto;

import java.util.Map;

/**
 * @author gbondarchuk
 */
public class NewsUrlDto {
    private Long id;
    private String url;
    private String searchWords;
    private boolean disabled;

    public NewsUrlDto(Long id, String url, String searchWords, boolean disabled) {
        this.id = id;
        this.url = url;
        this.searchWords = searchWords;
        this.disabled = disabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(String searchWords) {
        this.searchWords = searchWords;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
