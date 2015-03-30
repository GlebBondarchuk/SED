package com.bsu.sed.service;

import com.bsu.sed.model.SystemAttributeKey;
import com.bsu.sed.model.persistent.SystemAttribute;

import java.util.Date;
import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SystemAttributeService {

    List<SystemAttribute> getAttributes();

    void update(SystemAttribute attribute);

    void update(SystemAttributeKey key, String value, String description);

    String get(SystemAttributeKey key);

    int getInt(SystemAttributeKey key);

    Date getDate(SystemAttributeKey key);

    List<SystemAttribute> getAll();
}
