package com.bsu.sed.service;

import com.bsu.sed.model.persistent.Statistics;

/**
 * @author gbondarchuk
 */
public interface StatisticsService {
    String getLineChartJsonStatistics();

    String getPieChartJsonStatistics();

    void create(Statistics statistics);

    void deleteOld();

    void recalculate();
}
