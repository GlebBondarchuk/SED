package com.bsu.sed.service;

import com.bsu.sed.dto.SearchDto;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SearchService {
    List<SearchDto> search(String search, int limit, int offset);
}
