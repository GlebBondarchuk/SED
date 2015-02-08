package com.bsu.sed.service;

import com.bsu.sed.dao.ContentDao;
import com.bsu.sed.dao.PeopleDao;
import com.bsu.sed.model.dto.UserDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.People;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    @Autowired
    private ContentDao contentDao;

    @Override
    public People update(UserDto dto, String username) {
        People people = peopleDao.getByUsername(username);
        people.setPosition(dto.getPosition());
        people.setAddress(dto.getAddress());
        people.getUser().setName(dto.getName());
        people.getUser().setPhone(dto.getPhone());
        people.getUser().setPhoto(dto.getPhoto());
        people.getUser().setLogin(dto.getLogin());
        people = peopleDao.update(people);
        for (Content content : people.getContents()) {
            Hibernate.initialize(content);
            content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return people;
    }

    @Override
    public People addContent(String contentName, String html, String username) {
        People people = peopleDao.getByUsername(username);
        Content content = new Content();
        content.setName(contentName);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));
        content.setContentType("text/html");
        contentDao.create(content);
        people.getContents().add(content);
        return peopleDao.update(people);
    }

    @Override
    public People getByUsername(String username) {
        People people = peopleDao.getByUsername(username);
        if (people == null) {
            return null;
        }
        for (Content content : people.getContents()) {
            Hibernate.initialize(content);
            content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return people;
    }

    @Override
    public List<People> find() {
        return peopleDao.getAll();
    }
}
