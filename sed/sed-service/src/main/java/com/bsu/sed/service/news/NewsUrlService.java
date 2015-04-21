package com.bsu.sed.service.news;

import com.bsu.sed.model.dto.NewsUrlDto;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.service.BrowsableService;
import com.sun.syndication.io.FeedException;

import java.io.IOException;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsUrlService extends BrowsableService {
    List<NewsUrl> find();

    void save(NewsUrlDto dto) throws IOException, FeedException;

    void update(NewsUrl newsUrl);

    void delete(Long id);

    List<NewsUrl> getEnabled();

    NewsUrl get(Long id);
}
