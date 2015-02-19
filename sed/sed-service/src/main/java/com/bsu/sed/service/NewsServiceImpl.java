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
}
