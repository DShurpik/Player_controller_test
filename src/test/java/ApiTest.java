import io.qameta.allure.*;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.*;
import propertyReader.PropertyReader;

import java.util.Properties;

@Slf4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})
public class ApiTest {

    private Properties properties;

    @BeforeTest
    public void setProperties() {
        properties = PropertyReader.getProperties();
    }

    @BeforeMethod
    public void setUp(ITestContext context) {
        properties = PropertyReader.getProperties();
    }

    @Description("Test 1")
    @Severity(SeverityLevel.MINOR)
    @Feature("Какой функционал проверяю")
    @Story("User story example: Вход по логину и паролю")
    @Issue("Bug # 1")
    @TmsLink("TMS-456")
    @Test
    public void test1() {
        log.debug("FIrst test");
    }

    @Test
    public void test2() {
        log.debug("2");
    }

    @Test
    public void test3() {
        log.debug("3");
    }

    @Test
    public void test4() {
        log.debug("4");
    }

    @Test
    public void test5() {
        log.debug("5");
    }

    @Test
    public void test6() {
        log.debug("6");
    }

    @Test
    public void test7() {
        log.debug("7");
    }

    @Test
    public void test8() {
        log.debug("8");
    }

    @Test
    public void test9() {
        log.debug("9");
    }

    @Test
    public void test10() {
        log.debug("10");
    }


}
