package com.bsu.sed.controller;

import com.bsu.sed.model.Tiles;
import com.bsu.sed.service.StatisticsService;
import com.bsu.sed.service.geocode.IpLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gbondarchuk
 */
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping
    public ModelAndView getStatisticsPage() {
        return new ModelAndView(Tiles.STATISTICS_PAGE.getTileName());
    }

    @RequestMapping("/data/line")
    @ResponseBody
    public String getLineChartStatisticsData() {
        return statisticsService.getLineChartJsonStatistics();
    }

    @RequestMapping("/data/pie")
    @ResponseBody
    public String getPieChartStatisticsData() {
        statisticsService.recalculate();
        return statisticsService.getPieChartJsonStatistics();
    }
}
