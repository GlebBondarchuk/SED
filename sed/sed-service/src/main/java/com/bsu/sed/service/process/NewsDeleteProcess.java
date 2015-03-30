package com.bsu.sed.service.process;

import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gbondarchuk
 */

@Component
public class NewsDeleteProcess extends ProcessExecutor {
    @Autowired
    private SystemAttributeService systemAttributeService;
    @Autowired
    private NewsService newsService;

    @Override
    protected void execute() throws InterruptedException {
        Date date = systemAttributeService.getDate(SystemAttributeKey.NEWS_DELETE_AFTER);
        newsService.deleteNewsBeforeDate(date);
    }
}
