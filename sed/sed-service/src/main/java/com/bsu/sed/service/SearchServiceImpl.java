package com.bsu.sed.service;

import com.bsu.sed.dao.*;
import com.bsu.sed.model.dto.SearchDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.News;
import com.bsu.sed.model.persistent.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private NewsDao newsDao;

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> search(String search) {
        List<Long> contentIds = searchDao.find(search);
        List<SearchDto> peopleResults = searchInPeopleProfiles(contentIds);
        List<SearchDto> newsResults = searchInNews(contentIds);
        return (List<SearchDto>) CollectionUtils.union(peopleResults, newsResults);
    }

    private List<SearchDto> searchInPeopleProfiles(List<Long> ids) {
        List<SearchDto> results = new ArrayList<>();
        for (Long id : ids) {
            SearchDto searchDto = new SearchDto();
            Content content = contentDao.load(id);
            Long userId = peopleDao.getUserIdByContentId(id);
            if (userId != null) {
                User user = userDao.load(userId);

                String url = "/people/" + user.getLogin();

                searchDto.setTitle(content.getName());
                searchDto.setUrl(url);
                searchDto.setPhoto(user.getPhoto());
                results.add(searchDto);
            }
        }
        return results;
    }

    private List<SearchDto> searchInNews(List<Long> ids) {
        List<SearchDto> results = new ArrayList<>();
        for (Long id : ids) {
            SearchDto searchDto = new SearchDto();
            Long newsId = newsDao.getNewsIdFromContentId(id);
            if (newsId != null) {
                News news = newsDao.load(newsId);

                String url = "/news/" + newsId;

                searchDto.setTitle(news.getContent().getName());
                searchDto.setUrl(url);
                searchDto.setPhoto(news.getPhoto());
                results.add(searchDto);
            }
        }
        return results;
    }
}
