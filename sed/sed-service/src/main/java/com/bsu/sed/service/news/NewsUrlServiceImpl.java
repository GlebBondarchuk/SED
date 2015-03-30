package com.bsu.sed.service.news;

import com.bsu.sed.dao.NewsUrlDao;
import com.bsu.sed.model.dto.NewsUrlDto;
import com.bsu.sed.model.persistent.NewsUrl;
import com.bsu.sed.parser.RssParser;
import com.sun.syndication.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class NewsUrlServiceImpl implements NewsUrlService {
    @Autowired
    private NewsUrlDao newsUrlDao;

    @Override
    public List<NewsUrl> find() {
        return newsUrlDao.getAll();
    }

    @Override
    public void save(NewsUrlDto dto) throws IOException, FeedException {
        Map<Long, String> urls = dto.getUrl();
        Map<Long, Boolean> disableds = dto.getDisabled();
        for (Map.Entry<Long, String> entry : dto.getSearchWords().entrySet()) {
            Long id = entry.getKey();
            String url = urls.get(id);
            String searchWords = entry.getValue();
            Boolean disabled = false;
            if (disableds != null) {
                disabled = disableds.get(id);
            }

            if (!RssParser.isValid(url)) {
                throw new RuntimeException(url + " is not RSS. Please, use only RSS URL.");
            }

            String imageUrl = RssParser.getImageUrl(url);

            NewsUrl newsUrl = new NewsUrl(id, url, searchWords, imageUrl, (disabled == null ? false : disabled));
            if (id == null) {
                newsUrlDao.create(newsUrl);
            } else {
                newsUrlDao.update(newsUrl);
            }
        }
    }

    @Override
    public void update(NewsUrl newsUrl) {
        newsUrlDao.update(newsUrl);
    }

    @Override
    public void delete(Long id) {
        newsUrlDao.delete(id);
    }

    @Override
    public List<NewsUrl> getEnabled() {
        return newsUrlDao.findEnabled();
    }
}
