package com.bsu.sed.service;

import com.bsu.sed.model.persistent.News;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsService {
    List<News> find();

    News create(String header, String html, String photo, String simpleText);

    News get(Long id);

    void delete(Long id);

    News update(Long id, String header, String html, String photo, String simpleText);
}
