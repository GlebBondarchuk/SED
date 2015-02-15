package com.bsu.sed.service;

import com.bsu.sed.model.dto.PeopleDto;
import com.bsu.sed.model.persistent.People;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface PeopleService {
    People createPeople(PeopleDto dto);

    People getByLogin(String login);

    People update(PeopleDto dto, String login);

    People addContent(String contentName, String html, String login);

    People get(Long id);

    List<People> find();

    People getHead();
}
