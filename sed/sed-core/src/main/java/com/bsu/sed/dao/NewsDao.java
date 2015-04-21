package com.bsu.sed.dao;

import com.bsu.sed.dao.generic.GenericDao;
import com.bsu.sed.dao.generic.browsable.BrowsableDao;
import com.bsu.sed.model.persistent.News;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsDao extends BrowsableDao<News> {
   Long getNewsIdFromContentId(Long contentId);

   List<News> find(int limit, int offset, String query, String category);

   long count(String query, String category);

   int deleteNewsToDate(Date to);

   boolean exists(String name);

   List<String> getNewsCategories();

   void fix(boolean fix, Long id);
}
