package gov.nasa.jpl.cneos.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://cneos.jpl.nasa.gov";
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
    }
}
