package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import tests.DriverFixture;

public class LoginAdminFixture extends DriverFixture {

    public static final String URL = "http://localhost/litecart/admin/";

    @BeforeClass
    public void loginAdmin() {
        driver.get(URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("box-apps-menu")));
    }
}
