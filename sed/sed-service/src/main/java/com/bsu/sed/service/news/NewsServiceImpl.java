package com.bsu.sed.service.news;

import com.bsu.sed.dao.NewsDao;
import com.bsu.sed.dao.NewsDaoImpl;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.service.UserService;
import com.bsu.sed.utils.JsonUtils;
import com.bsu.sed.utils.SecurityUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    private static final int COUNT_CATEGORY_COLUMNS = 2;
    private static final String DEFAULT_CATEGORY = "From Department";

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserService userService;

    @Override
    public List<News> find(int limit, int offset, String query, String category) {
        return newsDao.find(limit, offset, query, category);
    }

    @Override
    public News create(String header, String html, String photo, String simpleText) {
        Content content = new Content();
        content.setName(header);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));

        User creator = userService.getByUsername(SecurityUtils.getAuthenticatedUserName());

        News news = new News();
        news.setContent(content);
        news.setPhoto(photo);
        news.setSimpleText(simpleText);
        news.setCreator(creator);
        news.setCategory(DEFAULT_CATEGORY);
        newsDao.create(news);
        return news;
    }

    @Override
    public News get(Long id) {
        News news = newsDao.load(id);
        if (news != null) {
            Content content = news.getContent();
            content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return news;
    }

    @Override
    public void delete(Long id) {
        newsDao.delete(id);
    }

    @Override
    public News update(Long id, String header, String html, String photo, String simpleText) {
        News news = newsDao.load(id);
        Content content = news.getContent();
        content.setName(header);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));
        news.setSimpleText(simpleText);
        news.setPhoto(photo);
        return newsDao.update(news);
    }

    @Override
    public void create(List<News> news) {
        for (News value : news) {
            try {
                newsDao.create(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long count(String query, String category) {
        return newsDao.count(query, category);
    }

    @Override
    public int deleteNewsToDate(Date to) {
        return newsDao.deleteNewsToDate(to);
    }

    @Override
    public boolean exists(String name) {
        return newsDao.exists(name);
    }

    @Override
    public List<List<String>> getNewsCategories() {
        List<String> values = newsDao.getNewsCategories();
        List<List<String>> categories = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(values) && values.size() > NewsDaoImpl.CATEGORIES_COUNT / COUNT_CATEGORY_COLUMNS) {
            categories = Lists.partition(values, NewsDaoImpl.CATEGORIES_COUNT / COUNT_CATEGORY_COLUMNS);
        } else {
            categories.add(values);
        }
        return categories;
    }

    @Override
    public void fix(boolean fix, Long id) {
        newsDao.fix(fix, id);
    }

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("creator.name", "simpleText", "category");
        List<News> contents = newsDao.find(searchFields, limit, offset, search, sort, order);
        long total = newsDao.count(searchFields, search);
        return JsonUtils.newsToJson(contents, total);
    }

    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            newsDao.delete(id);
        }
    }
}
