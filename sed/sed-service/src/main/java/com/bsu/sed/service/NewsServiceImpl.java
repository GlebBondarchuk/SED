package com.bsu.sed.service;

import com.bsu.sed.dao.NewsDao;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.User;
import com.bsu.sed.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserService userService;

    @Override
    public List<News> find() {
        return newsDao.getAll();
    }

    @Override
    public News create(String header, String html) {
        Content content = new Content();
        content.setName(header);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));

        User creator = userService.getByUsername(SecurityUtils.getAuthenticatedUserName());

        News news = new News();
        news.setContent(content);
        news.setCreator(creator);
        newsDao.create(news);
        return news;
    }
}
