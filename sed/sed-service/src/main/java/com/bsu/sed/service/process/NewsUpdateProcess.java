package com.bsu.sed.service.process;

import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.parser.RssParser;
import com.bsu.sed.service.UserService;
import com.bsu.sed.service.news.NewsService;
import com.bsu.sed.service.news.NewsUrlService;
import com.bsu.sed.utils.ParseUtils;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Component
public class NewsUpdateProcess extends ProcessExecutor {
    @Autowired
    private NewsUrlService newsUrlService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    private static final Log log = LogFactory.getLog(NewsUpdateProcess.class);

    @Override
    protected void execute() throws InterruptedException {
        try {
            log.info("New Update Process started.");
            List<NewsUrl> urls = newsUrlService.getEnabled();
            for (NewsUrl url : urls) {
                SyndFeed feed = RssParser.parse(url.getUrl());
                List<News> news = findNews(feed, ParseUtils.split(url.getSearchWords()), null);
                newsService.create(news);
            }
            log.info("New Update Process finished.");
        } catch (IOException | FeedException e) {
            log.debug(e.getMessage(), e);
        }
    }

    private List<News> findNews(SyndFeed feed, List<String> searchWords, Date from) {
        if (feed == null) {
            return new ArrayList<>();
        }
        List<News> newsList = new ArrayList<>();
        for (Object object : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) object;
            News news = new News();
            news.setCreator(userService.getByLogin(UserService.SYSTEM_USER));
            String text = entry.getTitle();
            if (!meets(text, searchWords)) {
                continue;
            }
            news.setSimpleText(entry.getTitle());
            Content content = new Content();
            content.setName(entry.getTitle());
            content.setContent(entry.getLink().getBytes(Charset.forName("UTF-8")));
            List enclosures = entry.getEnclosures();
            if (CollectionUtils.isNotEmpty(enclosures)) {
                for (Object value : enclosures) {
                    SyndEnclosure enclosure = (SyndEnclosure) value;
                    news.setPhoto(enclosure.getUrl());
                }
            }
            news.setContent(content);
            newsList.add(news);
        }
        return newsList;
    }

    private boolean meets(String text, List<String> searchWords) {
        for (String word : searchWords) {
            if (text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
