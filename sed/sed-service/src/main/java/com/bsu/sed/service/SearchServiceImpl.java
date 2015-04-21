package com.bsu.sed.service;

import com.bsu.sed.dao.*;
import com.bsu.sed.dto.SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<SearchDto> search(String search, int limit, int offset) {
        if (StringUtils.isEmpty(search)) {
            return new ArrayList<>();
        }
        List<Long> contents = searchDao.search(search, limit, offset);
        if(CollectionUtils.isEmpty(contents)) {
            return new ArrayList<>();
        }
        List<SearchDto> peopleResults = searchDao.searchInPeopleProfiles(contents);
        List<SearchDto> newsResults = searchDao.searchInNews(contents);
        List<SearchDto> primaryResults = searchDao.searchInPrimary(contents);
        List<SearchDto> contentResults = getContentResults(contents, peopleResults, newsResults, primaryResults);

        List<SearchDto> results = new ArrayList<>();
        results.addAll(peopleResults);
        results.addAll(newsResults);
        results.addAll(primaryResults);
        results.addAll(contentResults);

        return results;
    }

    @SafeVarargs
    private final List<SearchDto> getContentResults(List<Long> contents, List<SearchDto>... listResults) {
        List<Long> contentIds = new ArrayList<>();
        for (Long contentId : contents) {
            boolean contains = false;
            for (List<SearchDto> results : listResults) {
                for (SearchDto result : results) {
                    if (result.getId().equals(contentId)) {
                        contains = true;
                        break;
                    }
                }
            }
            if (!contains) {
                contentIds.add(contentId);
            }
        }
        if (CollectionUtils.isEmpty(contentIds)) {
            return new ArrayList<>();
        }
        return searchDao.searchInContents(contentIds);
    }
}
