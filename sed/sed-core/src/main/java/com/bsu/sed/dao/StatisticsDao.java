package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dto.LineChartStatisticsDto;
import com.bsu.sed.dto.PieChartStatisticsDto;
import com.bsu.sed.model.persistent.Statistics;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface StatisticsDao extends GenericDao<Statistics, Long> {
    List<LineChartStatisticsDto> getLineChartStatistics();

    List<PieChartStatisticsDto> getPieChartStatistics();

    void deleteOld();

    List<Statistics> getWithEmptyCountry();
}
