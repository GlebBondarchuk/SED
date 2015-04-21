package com.bsu.sed.service.news;

import com.bsu.sed.model.persistent.News;
import com.bsu.sed.service.BrowsableService;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsService extends BrowsableService {
    List<News> find(int limit, int offset, String query, String category);

    News create(String header, String html, String photo, String simpleText);

    News get(Long id);

    void delete(Long id);

    News update(Long id, String header, String html, String photo, String simpleText);

    void create(List<News> news);

    long count(String query, String category);

    int deleteNewsToDate(Date to);

    boolean exists(String name);

    List<List<String>> getNewsCategories();

    void fix(boolean fix, Long id);
}
