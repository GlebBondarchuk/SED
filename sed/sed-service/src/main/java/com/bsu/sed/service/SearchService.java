package com.bsu.sed.service;

import com.bsu.sed.model.dto.SearchDto;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SearchService {
    List<SearchDto> search(String search);

    List<SearchDto> searchInNews(String search);
}
