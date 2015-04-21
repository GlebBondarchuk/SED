package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.dto.LineChartStatisticsDto;
import com.bsu.sed.dto.PieChartStatisticsDto;
import com.bsu.sed.model.persistent.Statistics;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class StatisticsDaoImpl extends AbstractDao<Statistics> implements StatisticsDao {
    private static final int STATISTICS_SHOWING_PERIOD = 30; //30 days;

    @Override
    @SuppressWarnings("unchecked")
    public List<LineChartStatisticsDto> getLineChartStatistics() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT count(statistics.id) as amount, statistics.date as date " +
                "FROM Statistics statistics GROUP BY DATE(statistics.date)").setResultTransformer(Transformers.aliasToBean(LineChartStatisticsDto.class));
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PieChartStatisticsDto> getPieChartStatistics() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT count(statistics.id) as amount, statistics.country as country " +
                "FROM Statistics statistics GROUP BY statistics.country").setResultTransformer(Transformers.aliasToBean(PieChartStatisticsDto.class));
        return query.list();
    }

    @Override
    public void deleteOld() {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("DELETE FROM sed_statistics WHERE date < DATE_SUB(NOW() , INTERVAL :showingPeriod DAY)");
        query.setParameter("showingPeriod", STATISTICS_SHOWING_PERIOD);
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Statistics> getWithEmptyCountry() {
        Session session = em.unwrap(Session.class);
        Query query = session.createQuery("SELECT statistics FROM Statistics statistics WHERE statistics.country is null");
        return query.list();
    }
}
