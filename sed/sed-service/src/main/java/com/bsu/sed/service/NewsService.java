package com.bsu.sed.service;

import com.bsu.sed.model.persistent.News;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface NewsService {
    List<News> find();

    News create(String header, String html);
}
