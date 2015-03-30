package com.bsu.sed.service.news;

import com.bsu.sed.model.persistent.News;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsService {
    List<News> find(int limit, int offset, String query);

    News create(String header, String html, String photo, String simpleText);

    News get(Long id);

    void delete(Long id);

    News update(Long id, String header, String html, String photo, String simpleText);

    void create(List<News> news);

    long count(String query);

    void deleteNewsBeforeDate(Date date);
}
