package com.bsu.sed.dao;

import com.bsu.sed.dto.SearchDto;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> search(String search, int limit, int offset) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT content.id AS id FROM sed_content content " +
                "WHERE CONVERT(content.content USING utf8) LIKE :search OR content.name LIKE :search ORDER BY content.update_date DESC")
                .addScalar("id", LongType.INSTANCE)
                .setParameter("search", "%" + search + "%")
                .setMaxResults(limit)
                .setFirstResult(offset);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> searchInPeopleProfiles(List<Long> contents) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT content.id AS id, content.name AS title, concat('/people/', us.login) AS url, us.photo AS photo " +
                "FROM sed_people people " +
                "LEFT JOIN sed_people_content people_content ON people_content.people_id = people.id " +
                "LEFT JOIN sed_content content ON content.id= people_content.content_id " +
                "LEFT JOIN sed_user us ON us.id = people.user_id " +
                "WHERE people_content.content_id " +
                "IN(:contents)")
                .addScalar("id", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("photo", StringType.INSTANCE)
                .setParameterList("contents", contents)
                .setResultTransformer(Transformers.aliasToBean(SearchDto.class));
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> searchInNews(List<Long> contents) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT content.id AS id, content.name AS title, concat('/news/', CAST(news.id AS CHAR)) AS url, news.photo AS photo " +
                "FROM sed_news news " +
                "LEFT JOIN sed_content content ON content.id= news.content " +
                "WHERE content.id " +
                "IN(:contents)")
                .addScalar("id", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .addScalar("photo", StringType.INSTANCE)
                .setParameterList("contents", contents)
                .setResultTransformer(Transformers.aliasToBean(SearchDto.class));
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> searchInPrimary(List<Long> contents) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT content.id AS id, content.name AS title, concat('/content/', CAST(content.id AS CHAR)) AS url " +
                "FROM sed_primary prim " +
                "LEFT JOIN sed_content content ON content.id= prim.content_id " +
                "WHERE content.id " +
                "IN(:contents)")
                .addScalar("id", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .setParameterList("contents", contents)
                .setResultTransformer(Transformers.aliasToBean(SearchDto.class));
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> searchInContents(List<Long> contents) {
        Session session = em.unwrap(Session.class);
        Query query = session.createSQLQuery("SELECT content.id AS id, content.name AS title, concat('/content/', CAST(content.id AS CHAR)) AS url " +
                "FROM sed_content content " +
                "WHERE content.id " +
                "IN(:contents)")
                .addScalar("id", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("url", StringType.INSTANCE)
                .setParameterList("contents", contents)
                .setResultTransformer(Transformers.aliasToBean(SearchDto.class));
        return query.list();
    }
}
