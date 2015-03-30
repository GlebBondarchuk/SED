package com.bsu.sed.model.dto;

import java.util.Map;

/**
 * @author gbondarchuk
 */
public class NewsUrlDto {
    private Map<Long, String> url;
    private Map<Long, String> searchWords;
    private Map<Long, Boolean> disabled;

    public Map<Long, String> getUrl() {
        return url;
    }

    public void setUrl(Map<Long, String> url) {
        this.url = url;
    }

    public Map<Long, String> getSearchWords() {
        return searchWords;
    }

    public void setSearchWords(Map<Long, String> searchWords) {
        this.searchWords = searchWords;
    }

    public Map<Long, Boolean> getDisabled() {
        return disabled;
    }

    public void setDisabled(Map<Long, Boolean> disabled) {
        this.disabled = disabled;
    }

    public boolean isNull() {
        return url == null || searchWords == null;
    }
}
