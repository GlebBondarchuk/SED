package com.bsu.sed.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract Integration Test Configurations.
 *
 * @author gbondarchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/test-service-context.xml"})
public abstract class AbstractIntegrationTest {

}
