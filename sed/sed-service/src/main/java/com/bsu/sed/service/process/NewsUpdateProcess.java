package com.bsu.sed.service.process;

import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.parser.RssParser;
import com.bsu.sed.service.MailService;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.UserService;
import com.bsu.sed.service.builder.MailBuilder;
import com.bsu.sed.service.news.NewsService;
import com.bsu.sed.service.news.NewsUrlService;
import com.bsu.sed.utils.ParseUtils;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
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

    private static final Log log = LogFactory.getLog(NewsUpdateProcess.class);

    @Autowired
    private NewsUrlService newsUrlService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemAttributeService systemAttributeService;
    @Autowired
    private MailService mailService;

    @Override
    protected void execute() throws InterruptedException {
        log.info("New Update Process started.");
        int count = 0;
        List<News> allNews = new ArrayList<>();
        int newShowingDays = systemAttributeService.getInt(SystemAttributeKey.NEWS_SHOWING_PERIOD);
        Date from = DateUtils.addDays(new Date(), -newShowingDays);
        List<NewsUrl> urls = newsUrlService.getEnabled();
        for (NewsUrl url : urls) {
            try {
                log.info("Retrieving news from " + url.getUrl());
                SyndFeed feed = RssParser.parse(url.getUrl());
                List<News> news = findNews(feed, ParseUtils.split(url.getSearchWords()), from);
                allNews.addAll(news);

                newsService.create(news);

                int size = news.size();
                count += size;
                log.info("Retrieved " + size + " news from " + url.getUrl());
                url.setLastSearch(new Date());
                newsUrlService.update(url);
            } catch (RuntimeException | IOException | FeedException e) {
                log.error(e.getMessage(), e);
            }
        }
        log.info("New Update Process finished. " + count + " news were uploaded from " + from);

        if (CollectionUtils.isNotEmpty(allNews)) {
            List<String> subscribers = userService.getNewsSubscribers();

            mailService.sendNewsToSubscribers(allNews, subscribers);
        }
    }

    private List<News> findNews(SyndFeed feed, List<String> searchWords, Date from) {
        if (feed == null) {
            return new ArrayList<>();
        }
        List<News> newsList = new ArrayList<>();
        for (Object object : feed.getEntries()) {
            SyndEntry entry = (SyndEntry) object;
            if (isSuitable(entry, searchWords, from)) {
                News news = convert(entry);
                newsList.add(news);
            }
        }
        return newsList;
    }

    private boolean isSuitable(SyndEntry entry, List<String> searchWords, Date from) {
        Date publishedDate = entry.getPublishedDate();
        if (publishedDate == null) {
            return false;
        }
        if (publishedDate.before(from)) {
            return false;
        }

        String text = entry.getTitle();
        if (newsService.exists(text)) {
            return false;
        }

        SyndContent syndContent = entry.getDescription();
        String description = syndContent.getValue();

        return meets(text, searchWords) || meets(description, searchWords);
    }

    private News convert(SyndEntry entry) {
        News news = new News();
        news.setCreator(userService.getSystem());

        Content content = new Content();
        content.setName(entry.getTitle());
        String title = entry.getTitle();
        content.setName(title);

        SyndContent syndContent = entry.getDescription();
        String description = syndContent.getValue();
        news.setSimpleText(description);

        Date publishedDate = entry.getPublishedDate();
        news.setCreatedDate(publishedDate);

        List enclosures = entry.getEnclosures();
        List<String> imageUrls = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(enclosures)) {
            for (Object value : enclosures) {
                SyndEnclosure enclosure = (SyndEnclosure) value;
                imageUrls.add(enclosure.getUrl());
            }
            news.setPhoto(StringUtils.join(imageUrls, ","));
        }

        List syndCategories = entry.getCategories();
        if (CollectionUtils.isNotEmpty(syndCategories)) {
            SyndCategory syndCategory = (SyndCategory) syndCategories.get(0);
            String category = syndCategory.getName();
            news.setCategory(category);
        }

        String link = "";
        if (StringUtils.isNotBlank(entry.getLink())) {
            link = createHtmlLink(entry.getLink());
        }

        if (StringUtils.isNotBlank(news.getPhoto())) {
            content.setContent(link.getBytes(Charset.forName("UTF-8")));
        } else {
            content.setContent((description + " " + link).getBytes(Charset.forName("UTF-8")));
        }

        news.setContent(content);
        return news;
    }

    private boolean meets(String text, List<String> searchWords) {
        for (String word : searchWords) {
            if (text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String createHtmlLink(String url) {
        return "<a href=" + url + ">" + url + "</a>";
    }
}
