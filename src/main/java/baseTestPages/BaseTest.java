package baseTestPages;

import io.restassured.RestAssured;
import listeners.ListenerForAPI;
import listeners.ListenerProperty;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import propertyReader.PropertyReader;

import java.util.Properties;

@Log4j
@Listeners({ListenerProperty.class, ListenerForAPI.class})

public class BaseTest {

    protected Properties properties;
    protected static int userId;

    @BeforeClass
    public void setUp() {
        properties = PropertyReader.getProperties();
        RestAssured.baseURI = properties.getProperty("api.base.uri");
    }

}
