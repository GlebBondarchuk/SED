package com.bsu.sed.service;

import com.bsu.sed.dao.StatisticsDao;
import com.bsu.sed.dto.LineChartStatisticsDto;
import com.bsu.sed.dto.PieChartStatisticsDto;
import com.bsu.sed.model.persistent.Statistics;
import com.bsu.sed.service.geocode.IpLookupService;
import com.bsu.sed.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    private static final String DEFAULT_COUNTY = "Others";

    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private IpLookupService ipLookupService;

    @Override
    public String getLineChartJsonStatistics() {
        List<LineChartStatisticsDto> values = statisticsDao.getLineChartStatistics();
        return JsonUtils.toLineChartStatisticsJson(values);
    }

    @Override
    public String getPieChartJsonStatistics() {
        List<PieChartStatisticsDto> values = statisticsDao.getPieChartStatistics();
        return JsonUtils.toPieChartStatisticsJson(values);
    }

    @Override
    public void create(Statistics statistics) {
        statisticsDao.create(statistics);
    }

    @Override
    public void deleteOld() {
        statisticsDao.deleteOld();
    }

    @Override
    public void recalculate() {
        List<Statistics> values = statisticsDao.getWithEmptyCountry();
        for (Statistics value : values) {
            String ip = value.getHost();
            if (!StringUtils.isEmpty(ip)) {
                String county = ipLookupService.getCountry(ip);
                if (StringUtils.isEmpty(county)) {
                    county = DEFAULT_COUNTY;
                }
                value.setCountry(county);
                statisticsDao.update(value);
            }
        }
    }
}
