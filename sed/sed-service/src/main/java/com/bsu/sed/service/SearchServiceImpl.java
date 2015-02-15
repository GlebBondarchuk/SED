package com.bsu.sed.service;

import com.bsu.sed.dao.ContentDao;
import com.bsu.sed.dao.PeopleDao;
import com.bsu.sed.dao.SearchDao;
import com.bsu.sed.dao.UserDao;
import com.bsu.sed.model.dto.SearchDto;
import com.bsu.sed.model.persistent.Content;
import com.bsu.sed.model.persistent.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ContentDao contentDao;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<SearchDto> search(String search) {
        List<SearchDto> results = searchInPeopleProfiles(search);
        return results;
    }

    private List<SearchDto> searchInPeopleProfiles(String search) {
        List<Long> ids = searchDao.find(search);
        List<SearchDto> results = new ArrayList<>();
        for (Long id : ids) {
            SearchDto searchDto = new SearchDto();
            Content content = contentDao.load(id);
            Long userId = peopleDao.getUserIdByContentId(id);
            User user = userDao.load(userId);

            String url = "/user/" + userId;

            searchDto.setTitle(content.getName());
            searchDto.setUrl(url);
            searchDto.setPhoto(user.getPhoto());
            results.add(searchDto);
        }
        return results;
    }

    private List<SearchDto> searchByContentNames(String search) {
         return null;
    }
}
