package tests;

import org.testng.annotations.BeforeClass;

public class UserFixture extends DriverFixture {

    public static final String URL = "http://localhost/litecart/en/";

    @BeforeClass
    public void openLitecartApp() {
        driver.get(URL);
    }
}
