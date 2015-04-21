package com.bsu.sed.service.news;

import com.bsu.sed.dao.NewsUrlDao;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.dto.NewsUrlDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.parser.RssParser;
import com.bsu.sed.utils.JsonUtils;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NewsUrlServiceImpl implements NewsUrlService {
    @Autowired
    private NewsUrlDao newsUrlDao;

    @Override
    public List<NewsUrl> find() {
        return newsUrlDao.getAll();
    }

    @Override
    public void save(NewsUrlDto dto) throws IOException, FeedException {
        Long id = dto.getId();
        String url = dto.getUrl();
        String searchWords = dto.getSearchWords();
        boolean disabled = dto.isDisabled();
        if (!RssParser.isValid(dto.getUrl())) {
            throw new RuntimeException(url + " is not RSS. Please, use only RSS URL.");
        }

        String imageUrl = RssParser.getImageUrl(url);

        NewsUrl newsUrl = new NewsUrl(id, url, searchWords, imageUrl, disabled);
        if (id == null) {
            newsUrlDao.create(newsUrl);
        } else {
            newsUrlDao.update(newsUrl);
        }
    }

    @Override
    public void update(NewsUrl newsUrl) {
        newsUrlDao.update(newsUrl);
    }

    @Override
    public void delete(Long id) {
        newsUrlDao.delete(id);
    }

    @Override
    public List<NewsUrl> getEnabled() {
        return newsUrlDao.findEnabled();
    }

    @Override
    public NewsUrl get(Long id) {
        return newsUrlDao.load(id);
    }

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("url", "searchWords");
        List<NewsUrl> contents = newsUrlDao.find(searchFields, limit, offset, search, sort, order);
        long total = newsUrlDao.count(searchFields, search);
        return JsonUtils.newsUrlToJson(contents, total);
    }

    @Override
    public void delete(List<Long> ids) {
        newsUrlDao.delete(ids);
    }
}
