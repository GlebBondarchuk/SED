package sed.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author gbondarchuk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "WEB",
        "classpath:dispatcher-servlet-test.xml",
        "classpath:spring-security-test.xml",
        "classpath:ReportsErrorsThresholds-test.xml"})
public abstract class AbstractMockMvcTest {
}
