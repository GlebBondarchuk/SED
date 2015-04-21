package com.bsu.sed.service;

import com.bsu.sed.dao.ContentDao;
import com.bsu.sed.dao.PeopleDao;
import com.bsu.sed.dao.SystemAttributeDao;
import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.dto.PeopleDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.People;
import com.bsu.sed.model.persistent.User;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    private MailService mailService;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private SystemAttributeDao systemAttributeDao;
    @Autowired
    private Md5PasswordEncoder passwordEncoder;

    @Override
    public People createPeople(PeopleDto dto) {
        String emailMask = systemAttributeDao.get(SystemAttributeKey.EMAIL_MASK);
        boolean checkPeopleRegistration = systemAttributeDao.getBoolean(SystemAttributeKey.CHECK_PEOPLE_REGISTRATION);
        User user = new User();
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getLogin() + emailMask);
        user.setName(dto.getName());
        String encoded = passwordEncoder.encodePassword(dto.getPassword(), null);
        user.setPassword(encoded);
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setPhoto(dto.getPhoto());
        user.setDisabled(checkPeopleRegistration);

        People people = new People();
        people.setUser(user);
        people.setAddress(dto.getAddress());
        people.setPosition(dto.getPosition());

        peopleDao.create(people);
        if (checkPeopleRegistration) {
            mailService.sendRegistrationMessage(user);
        }
        return people;
    }

    @Override
    public People getByLogin(String login) {
        People people = peopleDao.getByLogin(login);
        if (CollectionUtils.isNotEmpty(people.getContents())) {
            for (Content content : people.getContents()) {
                Hibernate.initialize(content);
                content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
            }
        }
        return people;
    }

    @Override
    public People update(PeopleDto dto, String login) {
        People people = peopleDao.getByLogin(login);
        people.setPosition(dto.getPosition());
        people.setAddress(dto.getAddress());
        people.getUser().setName(dto.getName());
        people.getUser().setPhone(dto.getPhone());
        people.getUser().setPhoto(dto.getPhoto());
        people.getUser().setEmail(dto.getLogin());
        people.getUser().setNewsSubscriber(dto.isNewsSubscriber());
        people = peopleDao.update(people);
        for (Content content : people.getContents()) {
            Hibernate.initialize(content);
            content.setHtml(new String(content.getContent(), Charset.forName("UTF-8")));
        }
        return people;
    }

    @Override
    public People addContent(String contentName, String html, String login) {
        People people = peopleDao.getByLogin(login);
        Content content = new Content();
        content.setName(contentName);
        content.setContent(html.getBytes(Charset.forName("UTF-8")));
        content.setContentType("text/html");
        contentDao.create(content);
        people.getContents().add(content);
        return peopleDao.update(people);
    }

    @Override
    public People get(Long id) {
        People people = peopleDao.load(id);
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
