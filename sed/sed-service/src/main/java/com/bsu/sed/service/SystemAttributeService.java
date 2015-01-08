package com.bsu.sed.service;

import com.bsu.sed.model.persistent.SystemAttribute;

import java.util.List;

/**
 * @author gbondarchuk
 */
public interface SystemAttributeService {

    List<SystemAttribute> getAttributes();

    void update(SystemAttribute attribute);
}
