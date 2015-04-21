package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.AbstractDao;
import com.bsu.sed.model.BackgroundProcessKey;
import com.bsu.sed.model.persistent.BackgroundProcess;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author gbondarchuk
 */
@Repository
public class BackgroundProcessDaoImpl extends AbstractDao<BackgroundProcess> implements BackgroundProcessDao {

    @Override
    public void disableProcess(BackgroundProcessKey key) {
        changeProcessStatus(key, false);
    }

    @Override
    public void enableProcess(BackgroundProcessKey key) {
        changeProcessStatus(key, true);
    }

    @Override
    public void rescheduleProcess(BackgroundProcessKey key, String cron) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery("UPDATE sed_process SET cron=:cron WHERE `process`=:process");
        query.setParameter("process", key.name());
        query.setParameter("cron", cron);
        query.executeUpdate();
    }

    @Override
    public boolean isDisabled(BackgroundProcessKey backgroundProcessKey) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT job.disabled FROM sed_process job WHERE job.process = :process");
        query.setParameter("process", backgroundProcessKey.name());
        return (boolean) query.uniqueResult();
    }

    private void changeProcessStatus(BackgroundProcessKey key, boolean enabled) {
        Session session = em.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery("UPDATE sed_process SET disabled=:disabled WHERE `process`=:process");
        query.setParameter("process", key.name());
        query.setParameter("disabled", !enabled);
        query.executeUpdate();
    }
}
