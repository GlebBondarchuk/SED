package com.bsu.sed.service;

import com.bsu.sed.common.AbstractTransactionalIntegrationTest;
import com.bsu.sed.model.persistent.SystemAttribute;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gbondarchuk
 */
public class SystemAttributeServiceTest extends AbstractTransactionalIntegrationTest{
    @Autowired
    private SystemAttributeService systemAttributeService;

    @Test
    public void updateSystemAttributesTest() {
        List<SystemAttribute> systemAttributes = systemAttributeService.getAttributes();
        for(SystemAttribute systemAttribute : systemAttributes) {
            systemAttribute.setValue("New Value");
            systemAttributeService.update(systemAttribute);
        }
    }
}
