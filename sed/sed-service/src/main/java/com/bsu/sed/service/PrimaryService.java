package com.bsu.sed.service;

import com.bsu.sed.model.ContentKey;
import com.bsu.sed.model.persistent.Primary;

/**
 * @author gbondarchuk
 */
public interface PrimaryService {
    Primary get(ContentKey key);

    Primary getHint(ContentKey key);
}
