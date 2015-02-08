package com.bsu.sed.service;

import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.People;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface PeopleService {
    People update(UserDto dto, String username);

    People addContent(String contentName, String html, String username);

    People getByUsername(String username);

    List<People> find();
}
