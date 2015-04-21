package com.bsu.sed.service;

import com.bsu.sed.dao.TextDao;
import com.bsu.sed.model.SortOrder;
import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.persistent.Text;
import com.bsu.sed.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @author gbondarchuk
 */
@Service
@Transactional
public class TextServiceImpl implements TextService {
    @Autowired
    private TextDao textDao;

    @Override
    public String getJson(int limit, int offset, String search, String sort, SortOrder order) {
        List<String> searchFields = Arrays.asList("text");
        List<Text> values = textDao.find(searchFields, limit, offset, search, sort, order);
        long total = textDao.count(searchFields, search);
        return JsonUtils.textToJson(values, total);
    }

    @Override
    public void delete(List<Long> ids) {
        textDao.delete(ids);
    }

    @Override
    @Cacheable(value = "textCache")
    public Text get(TextKey key) {
        return textDao.get(key);
    }

    @Override
    public Text get(Long id) {
        return textDao.load(id);
    }

    @Override
    public void update(Long id, String text) {
        Text value = textDao.load(id);
        value.setText(text);
        textDao.update(value);
        textDao.evict(value.getKey());
    }
}
