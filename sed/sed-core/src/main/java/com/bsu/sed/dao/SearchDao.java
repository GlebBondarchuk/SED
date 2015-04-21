package com.bsu.sed.dao;

import com.bsu.sed.dto.SearchDto;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SearchDao {
    List<Long> search(String search, int limit, int offset);

    List<SearchDto> searchInPeopleProfiles(List<Long> contents);

    List<SearchDto> searchInNews(List<Long> contents);

    List<SearchDto> searchInPrimary(List<Long> contents);

    List<SearchDto> searchInContents(List<Long> contents);
}
