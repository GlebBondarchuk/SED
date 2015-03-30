package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.model.persistent.News;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsDao extends GenericDao<News, Long> {
   Long getNewsIdFromContentId(Long contentId);

   List<News> getSortedNews(int limit, int offset, String query);

   long count(String query);

   void deleteNewsBeforeDate(Date date);
}
