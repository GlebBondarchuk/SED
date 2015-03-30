package com.bsu.sed.model.dto;

import com.bsu.sed.model.SystemAttributeKey;

import java.util.Map;

/**
 * @author gbondarchuk
 */
public class SystemAttributeDto {
    private Map<SystemAttributeKey, String> values;
    private Map<SystemAttributeKey, String> descriptions;

    public Map<SystemAttributeKey, String> getValues() {
        return values;
    }

    public void setValues(Map<SystemAttributeKey, String> values) {
        this.values = values;
    }

    public Map<SystemAttributeKey, String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<SystemAttributeKey, String> descriptions) {
        this.descriptions = descriptions;
    }
}
