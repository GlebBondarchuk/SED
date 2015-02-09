package com.bsu.sed.service;

import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface PeopleService {
    People update(UserDto dto, Long userId);

    People addContent(String contentName, String html, Long userId);

    People getByUserId(Long id);

    List<People> find();
}
