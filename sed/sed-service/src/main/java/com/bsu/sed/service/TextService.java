package com.bsu.sed.service;

import com.bsu.sed.model.TextKey;
import com.bsu.sed.model.persistent.Text;

/**
 * @author gbondarchuk
 */
public interface TextService extends BrowsableService {
    Text get(TextKey key);

    Text get(Long id);

    void update(Long id, String text);
}
