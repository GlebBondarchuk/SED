package com.bsu.sed.service.process;

import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.service.SystemAttributeService;
import com.bsu.sed.service.news.NewsService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gbondarchuk
 */

@Component
public class NewsDeleteProcess extends ProcessExecutor {

    private static final Log log = LogFactory.getLog(NewsDeleteProcess.class);

    @Autowired
    private SystemAttributeService systemAttributeService;
    @Autowired
    private NewsService newsService;

    @Override
    protected void execute() throws InterruptedException {
        log.info("New Delete Process started.");
        int days = systemAttributeService.getInt(SystemAttributeKey.NEWS_SHOWING_PERIOD);
        Date to = DateUtils.addDays(new Date(), -days);
        int deleted = newsService.deleteNewsToDate(to);
        log.info("New Delete Process finished. " + deleted + " news were deleted before " + to);
    }
}
