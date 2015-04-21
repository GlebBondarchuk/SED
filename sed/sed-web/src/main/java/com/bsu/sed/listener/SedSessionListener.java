package com.bsu.sed.listener;

import com.bsu.sed.model.persistent.Statistics;
import com.bsu.sed.service.StatisticsService;
import com.bsu.sed.service.SystemAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

/**
 * @author gbondarchuk
 */
public class SedSessionListener implements HttpSessionListener {
    @Autowired
    private StatisticsService statisticsService;

    private void initStatisticsService(HttpSessionEvent httpSessionEvent) {
        if (statisticsService == null) {
            ApplicationContext context =
                    WebApplicationContextUtils.getWebApplicationContext(
                            httpSessionEvent.getSession().getServletContext()
                    );
            statisticsService = context.getBean(StatisticsService.class);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        try {
            initStatisticsService(httpSessionEvent);

            Statistics statistics = new Statistics();
            statistics.setDate(new Date());
            statistics.setHost(getCurrentHost());

            statisticsService.create(statistics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCurrentHost() {
        try {
            URL ipURL = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipURL.openStream()));
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        statisticsService.deleteOld();
    }
}
